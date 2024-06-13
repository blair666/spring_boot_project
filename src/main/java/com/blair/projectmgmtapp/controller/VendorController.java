//package com.blair.projectmgmtapp.controller;
//
//import com.blair.projectmgmtapp.model.Vendor;
//import com.blair.projectmgmtapp.service.VendorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/vendors")
//public class VendorController {
//
//    @Autowired
//    private VendorService vendorService;
//
//    @GetMapping
//    public List<Vendor> getAllVendors() {
//        return vendorService.getAllVendors();
//    }
//
//    @PostMapping("/add")
//    public List<Long> saveVendors(@RequestBody List<Vendor> vendors) {
//        List<Long> savedIds = new ArrayList<>();
//        for (Vendor vendor : vendors) {
//            savedIds.add(vendorService.saveVendor(vendor));
//        }
//        return savedIds;
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteVendorById(@PathVariable("id") long id) {
//        vendorService.deleteVendorById(id);
//    }
//
//    @DeleteMapping
//    public void deleteAllVendors() {
//        vendorService.deleteAllVendors();
//    }
//
//    @PutMapping("/{id}")
//    public void updateVendor(@PathVariable("id") long vendorId, @RequestBody Vendor vendorObj) {
//        vendorService.updateVendor(vendorId, vendorObj);
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Vendor>> searchVendors(@RequestParam String zipCode) {
//        List<Vendor> vendors = vendorService.findByZipCode(zipCode);
//        return ResponseEntity.ok(vendors);
//    }
//}

package com.blair.projectmgmtapp.controller;

import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String filterVendorsByCategory(@RequestParam("category") String category, Model model) {
        List<Vendor> vendors = vendorService.findByCategory(category);
        model.addAttribute("vendors", vendors);
        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
    }

    @GetMapping("/search")
    public String searchVendorsByZipCode(@RequestParam("zipCode") String zipCode, Model model) {
        List<Vendor> vendors = vendorService.findByZipCode(zipCode);
        model.addAttribute("vendors", vendors);
        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
    }

    @GetMapping("/new")
    public String createVendorForm(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "create_vendor"; 
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
}



// lastest version
//package com.blair.projectmgmtapp.controller;
//
//import com.blair.projectmgmtapp.model.Vendor;
//import com.blair.projectmgmtapp.service.VendorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/vendors")
//public class VendorController {
//
//    private final VendorService vendorService;
//
//    @Autowired
//    public VendorController(VendorService vendorService) {
//        this.vendorService = vendorService;
//    }
//
//    @GetMapping
//    public String listVendors(Model model) {
//        List<Vendor> vendors = vendorService.getAllVendors();
//        model.addAttribute("vendors", vendors);
//        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
//    }
//
//    @GetMapping("/filter")
//    public String filterVendorsByCategory(@RequestParam("category") String category, Model model) {
//        List<Vendor> vendors = vendorService.findByCategory(category);
//        model.addAttribute("vendors", vendors);
//        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
//    }
//
//    @GetMapping("/search")
//    public String searchVendorsByZipCode(@RequestParam("zipCode") String zipCode, Model model) {
//        List<Vendor> vendors = vendorService.findByZipCode(zipCode);
//        model.addAttribute("vendors", vendors);
//        return "vendors"; // Assuming "vendors" is a Thymeleaf template name
//    }
//
//    @GetMapping("/new")
//    public String createVendorForm(Model model) {
//        model.addAttribute("vendor", new Vendor());
//        return "create_vendor"; // Assuming "create_vendor" is a Thymeleaf template name
//    }
//
//    @PostMapping("/save")
//    public String saveVendor(@ModelAttribute("vendor") Vendor vendor) {
//        vendorService.saveVendor(vendor);
//        return "redirect:/vendors";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editVendorForm(@PathVariable Long id, Model model) {
//        Vendor vendor = vendorService.getVendorById(id);
//        if (vendor == null) {
//            model.addAttribute("errorMessage", "Vendor not found");
//            return "error"; // Assuming "error" is a Thymeleaf template name
//        }
//        model.addAttribute("vendor", vendor);
//        return "edit_vendor"; // Assuming "edit_vendor" is a Thymeleaf template name
//    }
//
//    @PostMapping("/{id}")
//    public String updateVendor(@PathVariable Long id, @ModelAttribute Vendor vendor) {
//        vendor.setId(id); // Ensure the vendor ID is set
//        vendorService.updateVendor(vendor);
//        return "redirect:/vendors";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteVendor(@PathVariable Long id) {
//        vendorService.deleteVendorById(id);
//        return "redirect:/vendors";
//    }
//
//    @GetMapping("/compare")
//    public String compareVendors(@RequestParam("vendorIds") List<Long> vendorIds, Model model) {
//        List<Vendor> vendors = vendorIds.stream()
//                .map(vendorService::getVendorById)
//                .collect(Collectors.toList());
//        model.addAttribute("vendors", vendors);
//        return "compare_vendors"; // Assuming "compare_vendors" is a Thymeleaf template name
//    }
//
//
//}
