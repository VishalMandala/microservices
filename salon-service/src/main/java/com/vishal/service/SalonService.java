package com.vishal.service;

import com.vishal.model.Salon;
import com.vishal.payload.dto.SalonDTO;
import com.vishal.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {

    Salon createSalon(SalonDTO salon, UserDTO user);

    Salon updateSalon(SalonDTO salon, UserDTO user, Long SalonId) throws Exception;

    List<Salon> getAllSalons();

    Salon getSalonById(Long id) throws Exception;

    Salon getSalonByOwnerId(String ownerId);

    List<Salon> getSalonsByCity(String city);

    void deleteSalonById(Long id);

}
