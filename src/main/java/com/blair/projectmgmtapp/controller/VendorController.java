package com.blair.projectmgmtapp.controller;

import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.service.VendorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public String listVendors(Model model) {
        List<Vendor> vendors = vendorService.getAllVendors();
        model.addAttribute("vendors", vendors);
        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
    }

    @GetMapping("/filter")
    public String filterVendors(@RequestParam(value = "zipCode", required = false) String zipCode,
                                @RequestParam(value = "category", required = false) String category,
                                Model model) {
        List<Vendor> vendors;
        if (!zipCode.isEmpty() && !category.isEmpty()) {
            vendors = vendorService.findByZipCodeAndCategory(zipCode, category);
        } else if (!zipCode.isEmpty()) {
            vendors = vendorService.findByZipCode(zipCode);
        } else if (!category.isEmpty()) {
            vendors = vendorService.findByCategory(category);
        } else {
            vendors = vendorService.getAllVendors();
        }
        model.addAttribute("vendors", vendors);
        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
    }

    @GetMapping("/new")
    public String createVendorForm(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "create_vendor";
    }

    @PostMapping
    public String createVendor(@ModelAttribute Vendor vendor) {
        vendorService.saveVendor(vendor);
        return "redirect:/vendors";
    }

    @PostMapping("/save")
    public String saveVendor(@ModelAttribute("vendor") Vendor vendor) {
        vendorService.saveVendor(vendor);
        return "redirect:/vendors";
    }

    @GetMapping("/edit/{id}")
    public String editVendorForm(@PathVariable Long id, Model model) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            model.addAttribute("errorMessage", "Vendor not found");
            return "error"; // Assuming "error" is a Thymeleaf template name
        }
        model.addAttribute("vendor", vendor);
        return "edit_vendor"; // Assuming "edit_vendor" is a Thymeleaf template name
    }

    @PostMapping("/{id}")
    public String updateVendor(@PathVariable Long id, @ModelAttribute Vendor vendor) {
        vendor.setId(id); // Ensure the vendor ID is set
        vendorService.updateVendor(vendor);
        return "redirect:/vendors";
    }

    @GetMapping("/{id}/delete")
    public String deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
        return "redirect:/vendors";
    }

    @GetMapping("/compare")
    public String compareVendors(@RequestParam("vendorIds") List<Long> vendorIds, Model model) {
        List<Vendor> vendors = vendorIds.stream()
                .map(vendorService::getVendorById)
                .collect(Collectors.toList());
        model.addAttribute("vendors", vendors);
        return "compare_vendors"; // Assuming "compare_vendors" is a Thymeleaf template name
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        try {
            Vendor vendor = vendorService.getVendorById(id);
            return new ResponseEntity<>(vendor, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
