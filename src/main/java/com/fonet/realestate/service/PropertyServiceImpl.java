package com.fonet.realestate.service;

import com.fonet.realestate.model.Property;
import com.fonet.realestate.model.PropertyType;
import com.fonet.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyServices {
   
    @Autowired private PropertyRepository propertyRepository;

    @Override public List<Property> getAllProperties()
    {
        return propertyRepository.findAll();
    }
 
    @Override public void save(Property customer)
    {
        propertyRepository.save(customer);
    }
 
    @Override public Property getById(Long id)
    {
        Optional<Property> optional = propertyRepository.findById(id);
        Property customer = null;
        if (optional.isPresent())
            customer = optional.get();
        else
            throw new RuntimeException(
                "Customer not found for id : " + id);
        return customer;
    }
 
    @Override public void deleteViaId(long id)
    {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<Property> getPropertiesByCustomerId(Long customerId) {
        return propertyRepository.findByOwnerIdOrderByIdDesc(customerId);
    }

    @Override
    public List<Property> searchProperties(
            String kelime,
            String address,
            Integer roomCount,
            Integer squareMeterLowest,
            Integer squareMeterHighest,
            Integer floorLowest,
            Integer floorHighest,
            PropertyType propertyType) {
        return propertyRepository.searchProperties( kelime,address, roomCount, squareMeterLowest, squareMeterHighest, floorLowest, floorHighest, propertyType);
    }
}