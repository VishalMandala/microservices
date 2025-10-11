package com.vishal.model;

import com.vishal.domain.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @ElementCollection
    private Set<Long> serviceIds;

    @Column(nullable = false)
    private Long salonId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private BookingStatus Status = BookingStatus.PENDING;

    private int totalPrice;

}
