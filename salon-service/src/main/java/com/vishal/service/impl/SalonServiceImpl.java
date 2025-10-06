package com.vishal.service.impl;

import com.vishal.model.Salon;
import com.vishal.payload.dto.SalonDTO;
import com.vishal.payload.dto.UserDTO;
import com.vishal.repository.SalonRepository;
import com.vishal.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setOwnerId(user.getId());
        salon.setOpenedTime(req.getOpenedTime());
        salon.setClosedTime(req.getClosedTime());
        salon.setMobile(req.getMobile());
        salon.setImages(req.getImages());
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long SalonId) throws Exception {

        Salon existingSalon = salonRepository.findById(SalonId).orElse(null);
        if(existingSalon != null && salon.getOwnerId().equals(user.getId())){
            existingSalon.setName(salon.getName());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setCity(salon.getCity());
            existingSalon.setOwnerId(user.getId());
            existingSalon.setOpenedTime(salon.getOpenedTime());
            existingSalon.setClosedTime(salon.getClosedTime());
            existingSalon.setMobile(salon.getMobile());
            return salonRepository.save(existingSalon);
        }
        throw new Exception("Salon does not exist");
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long id) throws Exception {
        Salon salon = salonRepository.findById(id).orElse(null);
        if(salon == null){
            throw new Exception("Salon not found");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(String ownerId) {
        return salonRepository.findByOwnerId(Long.valueOf(ownerId));
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        return salonRepository.searchSalons(city);
    }

    @Override
    public void deleteSalonById(Long id) {
    }
}
