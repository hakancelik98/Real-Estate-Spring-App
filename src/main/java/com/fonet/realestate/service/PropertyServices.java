package com.fonet.realestate.service;
 
import com.fonet.realestate.model.Customer;
import com.fonet.realestate.model.Property;
import com.fonet.realestate.model.PropertyType;

import java.util.List;


public interface PropertyServices {
    List<Property> getAllProperties();
    void save(Property customer);
    Property getById(Long id);
    void deleteViaId(long id);

    List<Property> getPropertiesByCustomerId(Long customerId);

    List<Property> searchProperties(String kelime,
                                    String address,
                                    Integer roomCount,
                                    Integer squareMeterLowest,
                                    Integer squareMeterHighest,
                                    Integer floorLowest,
                                    Integer floorHighest,
                                    PropertyType propertyType);
}