package com.vishal.mapper;

import com.vishal.dto.BookingDTO;
import com.vishal.model.Booking;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setServiceIds(booking.getServiceIds());

        return bookingDTO;
    }
}
