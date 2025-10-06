package com.vishal.mapper;

import com.vishal.model.Salon;
import com.vishal.payload.dto.SalonDTO;

public class SalonMapper {

    public static SalonDTO mapToDTO(Salon salon){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setCity(salon.getCity());
        salonDTO.setOpenedTime(salon.getOpenedTime());
        salonDTO.setClosedTime(salon.getClosedTime());
        salonDTO.setOwnerId(salon.getOwnerId());
        salonDTO.setImages(salon.getImages());
        salonDTO.setMobile(salon.getMobile());
        return salonDTO;
    }

}
