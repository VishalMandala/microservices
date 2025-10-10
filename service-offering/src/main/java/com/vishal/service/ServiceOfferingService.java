package com.vishal.service;

import com.vishal.dto.CategoryDTO;
import com.vishal.dto.SalonDTO;
import com.vishal.dto.ServiceDTO;
import com.vishal.model.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId);

    Set<ServiceOffering> getAllServicesByIds(Set<Long> ids);

    ServiceOffering getServiceById(Long id) throws Exception;
}
