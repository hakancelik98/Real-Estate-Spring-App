package com.fonet.realestate.controller;

import com.fonet.realestate.model.Customer;
import com.fonet.realestate.model.EstateAgent;
import com.fonet.realestate.service.CustomerServices;
import com.fonet.realestate.service.EstateAgentServiceImpl;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class CustomerController {
 
    @Autowired
    private CustomerServices customerServiceImpl;
    @Autowired
    private EstateAgentServiceImpl agentService;

    @GetMapping("/musterileriGoruntule")
    public String viewHomePage(Model model) {
        Long agentId = (Long) model.asMap().get("agentId");
        List<Customer> customersByEstateAgentId = customerServiceImpl.getCustomersByEstateAgentId(agentId);

        model.addAttribute("agentId", agentId);
        model.addAttribute("allCustomersList", customersByEstateAgentId);
        model.addAttribute("customer", new Customer());
        return "musteri_listesi";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, @RequestParam("agentId") Long agentId, Model model) {
        customer.setAgent(agentService.getById(agentId));
        customerServiceImpl.save(customer);
        List<Customer> customersByEstateAgentId = customerServiceImpl.getCustomersByEstateAgentId(agentId);
        model.addAttribute("allCustomersList", customersByEstateAgentId);
        model.addAttribute("customer", new Customer());
        model.addAttribute("agentId", agentId);
        return "musteri_listesi";
    }
 
    @GetMapping("/showCustomerFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        Customer customer = customerServiceImpl.getById(id);
        model.addAttribute("customer", customer);
        return "updateCustomer";
    }
 
    @GetMapping("/deleteCustomer/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        Customer customer = customerServiceImpl.getById(id);
        customerServiceImpl.deleteViaId(id);
        redirectAttributes.addFlashAttribute("agentId", customer.getAgent().getId());
        return "redirect:/musterileriGoruntule";
    }


}