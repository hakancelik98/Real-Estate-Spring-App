package com.fonet.realestate.service;

import java.util.List;
import java.util.Optional;

import com.fonet.realestate.model.Customer;
import com.fonet.realestate.model.EstateAgent;
import com.fonet.realestate.repository.CustomerRepository;
import com.fonet.realestate.repository.EstateAgentRepository;
import org.springframework.stereotype.Service;
 
@Service
public class EstateAgentServiceImpl implements EstateAgentServices {
   
    private final EstateAgentRepository estateAgentRepo;
    private final CustomerRepository customerRepository;

    public EstateAgentServiceImpl(EstateAgentRepository estateAgentRepo, CustomerRepository customerRepository) {
        this.estateAgentRepo = estateAgentRepo;
        this.customerRepository = customerRepository;
    }


    @Override public List<EstateAgent> getAllAgents()
    {
        return estateAgentRepo.findAll();
    }
 
    @Override public void save(EstateAgent estateAgent)
    {
        estateAgentRepo.save(estateAgent);
    }
 
    @Override public EstateAgent getById(Long id)
    {
        Optional<EstateAgent> optional = estateAgentRepo.findById(id);
        EstateAgent estateAgent = null;
        if (optional.isPresent())
            estateAgent = optional.get();
        else
            throw new RuntimeException(
                "Agents not found for id : " + id);
        return estateAgent;
    }
 
    @Override public void deleteViaId(long id)
    {
        EstateAgent isyeri = estateAgentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("EstateAgent not found for id : " + id));
        List<Customer> customers = isyeri.getCustomers();
        estateAgentRepo.save(isyeri);
        for (Customer customer : customers) {
            customerRepository.delete(customer);
        }
        isyeri.getCustomers().clear();
        estateAgentRepo.deleteById(id);
    }
}