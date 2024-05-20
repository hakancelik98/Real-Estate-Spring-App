package com.fonet.realestate.service;
 
import com.fonet.realestate.model.EstateAgent;
import com.fonet.realestate.model.Customer;

import java.util.List;


public interface CustomerServices {
    void save(Customer customer);
    Customer getById(Long id);
    void deleteViaId(long id);
    List<Customer> getCustomersByEstateAgentId(Long estateAgentId);
}