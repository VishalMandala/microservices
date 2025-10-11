package com.vishal.service.impl;

import com.vishal.domain.BookingStatus;
import com.vishal.dto.BookingRequest;
import com.vishal.dto.SalonDTO;
import com.vishal.dto.ServiceDTO;
import com.vishal.dto.UserDTO;
import com.vishal.model.Booking;
import com.vishal.model.SalonReport;
import com.vishal.repository.BookingRepository;
import com.vishal.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking,
                                 UserDTO user,
                                 SalonDTO salon,
                                 Set<ServiceDTO> serviceDTOSet) throws Exception {
        int totalDuration = serviceDTOSet.stream().mapToInt(ServiceDTO::getDuration).sum();

        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(salon, bookingStartTime, bookingEndTime);

        int totalPrice = serviceDTOSet.stream().mapToInt(ServiceDTO::getPrice).sum();

        Set<Long> idList = serviceDTOSet.stream().map(ServiceDTO::getId).collect(java.util.stream.Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {

        List<Booking> existingBookings = getBookingBySalon(salonDTO.getId());
        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonClosedTime = salonDTO.getClosedTime().atDate(bookingEndTime.toLocalDate());

        if(bookingStartTime.isBefore(salonOpenTime) && bookingEndTime.isAfter(salonClosedTime)){
            throw new Exception("Booking time must be within Salon's wokring hours");
        }
        for(Booking existingBooking : existingBookings){
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();
            if(bookingStartTime.isBefore(existingBookingEndTime) && bookingEndTime.isAfter(existingBookingStartTime)){
                throw new Exception("Slot is not available, choose a different slot");
            }
            if(bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)){
                throw new Exception("Slot is not available, choose a different slot");
            }
        }
        return true;
    }

    @Override
    public List<Booking> getBookingByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if(booking == null){
            throw new Exception("Booking not found with the ID: " +id);
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking = getBookingById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, long salonId) {
        List<Booking> allBookings = getBookingBySalon(salonId);

        if(date == null){
            return allBookings;
        }
        return allBookings.stream().filter(booking -> isSameDate(booking.getStartTime(), date) ||
                isSameDate(booking.getEndTime(), date))
                .collect(Collectors.toList());
    }

    public Boolean isSameDate(LocalDateTime dateTime, LocalDate date){
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        List<Booking> bookings = getBookingBySalon(salonId);
        int totalEarning = bookings.stream().mapToInt(Booking::getTotalPrice).sum();

        Integer totalBookings = bookings.size();

        List<Booking> cancelledBookings = bookings.stream().
                filter(booking -> booking.getStatus() == BookingStatus.CANCELLED)
                .collect(Collectors.toList());

        int totalRefund = cancelledBookings.stream().mapToInt(Booking::getTotalPrice).sum();

        SalonReport report = new SalonReport();
        report.setSalonId(salonId);
        report.setCancelledBookings(cancelledBookings.size());
        report.setTotalBookings(totalBookings);
        report.setTotalEarnings(totalEarning);
        report.setTotalRefund(totalRefund);
        report.setTotalBookings(totalBookings);

        return report;
    }
}
