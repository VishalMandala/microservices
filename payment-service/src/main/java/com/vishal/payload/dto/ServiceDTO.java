package com.vishal.payload.dto;

import lombok.Data;

@Data
public class ServiceDTO {

    private Long id;

    private String name;

    private String description;

    private int price;

    private Long salonId;

    private int duration;

    private Long category;

    private String image;
}
