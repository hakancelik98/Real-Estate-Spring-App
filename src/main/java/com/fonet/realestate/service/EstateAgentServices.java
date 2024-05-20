package com.fonet.realestate.service;
 
import com.fonet.realestate.model.EstateAgent;

import java.util.List;

 
public interface EstateAgentServices {
    List<EstateAgent> getAllAgents();
    void save(EstateAgent estateAgent);
    EstateAgent getById(Long id);
    void deleteViaId(long id);
}