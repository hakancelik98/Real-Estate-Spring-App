package com.fonet.realestate.model;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
public class EstateAgent {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String agentName;
    private String authorizedPerson;
    private String address;
    private String phone;
    private String fax;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Customer> customers;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String businessName) {
        this.agentName = businessName;
    }

    public String getAuthorizedPerson() {
        return authorizedPerson;
    }

    public void setAuthorizedPerson(String authorizedPerson) {
        this.authorizedPerson = authorizedPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}