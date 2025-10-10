package com.vishal.dto;

import jakarta.persistence.*;
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

    private LocalTime openedTime;

    private LocalTime closedTime;
}
