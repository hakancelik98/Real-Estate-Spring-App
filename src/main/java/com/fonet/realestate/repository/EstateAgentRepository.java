package com.fonet.realestate.repository;
 
import com.fonet.realestate.model.Customer;
import com.fonet.realestate.model.EstateAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EstateAgentRepository extends JpaRepository<EstateAgent,Long> {

}