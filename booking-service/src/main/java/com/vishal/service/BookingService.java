package com.vishal.service;

import com.vishal.domain.BookingStatus;
import com.vishal.dto.BookingRequest;
import com.vishal.dto.SalonDTO;
import com.vishal.dto.ServiceDTO;
import com.vishal.dto.UserDTO;
import com.vishal.model.Booking;
import com.vishal.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking,
                          UserDTO userDTO,
                          SalonDTO salonDTO,
                          Set<ServiceDTO> serviceDTO) throws Exception;

    List<Booking> getBookingByCustomer(Long customerId);

    List<Booking> getBookingBySalon(Long salonId);

    Booking getBookingById(Long id) throws Exception;

    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;

    List<Booking> getBookingsByDate(LocalDate date, long salonId);

    SalonReport getSalonReport(Long salonId);


}
