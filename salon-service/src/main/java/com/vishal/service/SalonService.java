package com.vishal.service;

import com.vishal.model.Salon;
import com.vishal.payload.dto.SalonDTO;
import com.vishal.payload.dto.UserDTO;

public interface SalonService {

    Salon createSalon(SalonDTO salon, UserDTO user);

}
