package com.fonet.realestate.controller;
 
import com.fonet.realestate.model.EstateAgent;
import com.fonet.realestate.service.EstateAgentServiceImpl;
import com.fonet.realestate.service.EstateAgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EstateAgentController {
 
    @Autowired
    private EstateAgentServices estateAgentServiceImpl;
 
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allAgentList", estateAgentServiceImpl.getAllAgents());
        return "index";
    }

    @GetMapping("/gotoAgentPage/{id}")
    public String gotoAgentPage(@PathVariable(value = "id") long agentId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("agentId", agentId);
        return "redirect:/musterileriGoruntule";
    }

    @PostMapping("/save")
    public String saveAgent(@ModelAttribute("estateAgent") EstateAgent estateAgent) {
        estateAgentServiceImpl.save(estateAgent);
        return "redirect:/";
    }

    @GetMapping("/gotoAddNewPage")
    public String addNewAgent(Model model) {
        EstateAgent estateAgent = new EstateAgent();
        model.addAttribute("estateAgent", estateAgent);
        return "yeni_emlakci";
    }
 

 
    @GetMapping("/showAgentFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        EstateAgent estateAgent = estateAgentServiceImpl.getById(id);
        model.addAttribute("estateAgent", estateAgent);
        return "emlakci_guncelle";
    }
 
    @GetMapping("/deleteAgent/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id) {
        estateAgentServiceImpl.deleteViaId(id);
        return "redirect:/";
 
    }


}