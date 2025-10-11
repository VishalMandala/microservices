package com.vishal.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDTO {
    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String mobile;

    private String email;

    private String city;

    private Long ownerId;

    private LocalTime openTime;

    private LocalTime closedTime;
}
