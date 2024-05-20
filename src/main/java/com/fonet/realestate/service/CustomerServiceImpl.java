package com.fonet.realestate.service;

import com.fonet.realestate.model.Customer;
import com.fonet.realestate.model.EstateAgent;
import com.fonet.realestate.repository.CustomerRepository;
import com.fonet.realestate.repository.EstateAgentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerServices {
   
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private EstateAgentRepository estateAgentRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EstateAgentServices estateAgentServices;


    @Transactional
    @Override public void save(Customer customer)
    {
        EstateAgent agent= estateAgentServices.getById(customer.getAgent().getId());
        agent.getCustomers().add(customer);
        estateAgentRepository.save(agent);
    }
 
    @Override public Customer getById(Long id)
    {
        Optional<Customer> optional = customerRepo.findById(id);
        Customer customer = null;
        if (optional.isPresent())
            customer = optional.get();
        else
            throw new RuntimeException(
                "Customer not found for id : " + id);
        return customer;
    }
 
    @Override public void deleteViaId(long id)
    {
        Customer customer = getById(id);
        EstateAgent agent = estateAgentServices.getById(customer.getAgent().getId());
        agent.getCustomers().remove(customer);
        estateAgentRepository.save(agent);
        customerRepo.deleteById(id);
    }

    @Override
    public List<Customer> getCustomersByEstateAgentId(Long estateAgentId) {
        return customerRepository.findByAgentIdOrderByIdDesc(estateAgentId);
    }
}