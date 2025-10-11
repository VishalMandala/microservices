package com.vishal.controller;

import com.vishal.domain.BookingStatus;
import com.vishal.dto.*;
import com.vishal.mapper.BookingMapper;
import com.vishal.model.Booking;
import com.vishal.model.SalonReport;
import com.vishal.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("/api/bookings")
@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId,
                                                 @RequestBody BookingRequest bookingRequest) throws Exception {

        UserDTO user = new UserDTO();
        user.setId(1L);

        SalonDTO salon = new SalonDTO();
        salon.setId(salonId);
        salon.setOpenTime(LocalTime.now());
        salon.setClosedTime(LocalTime.now().plusHours(12));

        Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Hair cut for men");

        serviceDTOSet.add(serviceDTO);

        Booking booking = bookingService.createBooking(bookingRequest, user, salon, serviceDTOSet);

        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomer() throws Exception{

         List<Booking> bookings = bookingService.getBookingByCustomer(1L);
         return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySalon() throws Exception{
        List<Booking> bookings = bookingService.getBookingBySalon(1L);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings){
        return bookings.stream().map(booking -> {
            return BookingMapper.toDTO(booking);
        }).collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception{
        Booking bookings = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(bookings));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId, @RequestParam BookingStatus status
    ) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId, status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date
    ) throws Exception{
        List<Booking> bookings = bookingService.getBookingsByDate(date, salonId);

        List<BookingSlotDTO> slotDTOs = bookings.stream()
                .map(booking -> {BookingSlotDTO slotDTO = new BookingSlotDTO();
                    slotDTO.setStartTime(booking.getStartTime());
                    slotDTO.setEndTime(booking.getEndTime());
                    return slotDTO;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(slotDTOs);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() throws Exception{
        SalonReport report = bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);
    }

}
