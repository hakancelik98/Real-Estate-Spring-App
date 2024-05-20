package com.fonet.realestate.controller;

import com.fonet.realestate.model.Property;
import com.fonet.realestate.model.PropertyType;
import com.fonet.realestate.service.CustomerServiceImpl;
import com.fonet.realestate.service.PropertyServiceImpl;
import com.fonet.realestate.service.PropertyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class PropertyController {

    @Autowired
    private PropertyServices propertyService;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/emlaklariGoruntule")
    public String viewHomePage(Model model) {
        Long customerId = (Long) model.asMap().get("customerId");
        List<Property> propertiesByCustomerId = propertyService.getPropertiesByCustomerId(customerId);

        model.addAttribute("customerId", customerId);
        model.addAttribute("allPropertiesList", propertiesByCustomerId);
        model.addAttribute("property", new Property());
        return "emlak_listesi";
    }

    @PostMapping("/saveProperty")
    public String saveProperty(@ModelAttribute("property") Property property, @RequestParam("customerId") Long customerId, Model model) {
        property.setOwner(customerServiceImpl.getById(customerId));
        propertyService.save(property);
        List<Property> propertyByCustomerId = propertyService.getPropertiesByCustomerId(customerId);
        model.addAttribute("allPropertiesList", propertyByCustomerId);
        model.addAttribute("property", new Property());
        model.addAttribute("customerId", customerId);
        return "emlak_listesi";
    }

    @GetMapping("/showEmlakUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id ,Model model) {
        Property property = propertyService.getById(id);
        model.addAttribute("property", property);
        return "emlak_guncelle";
    }

    @GetMapping("/showForm")
    public String showForm(Model model) {
        Property property = new Property();
        model.addAttribute("property", property);
        return "emlak_listesi";
    }

    @GetMapping("/deleteEmlak/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        Property property = propertyService.getById(id);
        propertyService.deleteViaId(id);
        redirectAttributes.addFlashAttribute("customerId", property.getOwner().getId());
        return "redirect:/emlaklariGoruntule";
    }

    @GetMapping("/musterininEmlaklarinaGit/{id}")
    public String gotoPropertyListPage(@PathVariable(value = "id") long customerId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("customerId", customerId);
        return "redirect:/emlaklariGoruntule";
    }

    @GetMapping("/searchProperties")
    public String showSearchPage(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("allPropertiesList", properties);
        return "emlak_arama";
    }

    @GetMapping("/searchPropertiesFiltered")
    public String searchProperties(
                                   @RequestParam(required = false) String kelime,
                                   @RequestParam(required = false) String address,
                                   @RequestParam(required = false) Integer roomCount,
                                   @RequestParam(required = false) Integer squareMeterHighest,
                                   @RequestParam(required = false) Integer squareMeterLowest,
                                   @RequestParam(required = false) Integer floorLowest,
                                   @RequestParam(required = false) Integer floorHighest,
                                   @RequestParam(required = false) String propertyType,
                                   Model model) {
        PropertyType type = null;
        if (!propertyType.equals("")) {
            type = PropertyType.valueOf(propertyType);
        }
        List<Property> properties = propertyService.searchProperties(kelime, address,roomCount, squareMeterLowest,squareMeterHighest, floorLowest,floorHighest, type);
        model.addAttribute("allPropertiesList", properties);
        return "emlak_arama";
    }

    @GetMapping("/gotoPropertyDetailPage/{id}")
    public String viewPropertyDetail(@PathVariable("id") Long id, Model model) {
        Property property = propertyService.getById(id);
        model.addAttribute("property", property);
        return "secilen_emlak_detaylari";
    }

}