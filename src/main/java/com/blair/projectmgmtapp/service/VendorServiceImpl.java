//package com.blair.projectmgmtapp.service;
//
//import com.blair.projectmgmtapp.model.Vendor;
//import com.blair.projectmgmtapp.repository.VendorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class VendorServiceImpl implements VendorService {
//
//    private VendorRepository vendorRepository;
//
//    @Autowired
//    public VendorServiceImpl(VendorRepository vendorRepository) {
//        this.vendorRepository = vendorRepository;
//    }
//
//    @Override
//    public List<Vendor> findByZipCode(String zipcode) {
//        return vendorRepository.findByZipcodeOrderByRatingDesc(zipcode);
//    }
//
//    @Override
//    public List<Vendor> getAllVendors() {
//        return vendorRepository.findAll();
//    }
//
//    @Override
//    public long saveVendor(Vendor vendor) {
//        vendorRepository.save(vendor);
//        return vendor.getId();
//    }
//
//    @Override
//    public void deleteVendorById(long id) {
//        vendorRepository.deleteById(id);
//    }
//
//    @Override
//    public void deleteAllVendors() {
//        vendorRepository.deleteAll();
//    }
//
//    @Override
//    public void updateVendor(long vendorId, Vendor vendorObj) {
//        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
//        if (vendor != null) {
//            vendor.setName(vendorObj.getName());
//            vendor.setCategory(vendorObj.getCategory());
//            vendor.setRating(vendorObj.getRating());
//            vendor.setZipcode(vendorObj.getZipcode());
//            vendor.setReviews(vendorObj.getReviews());
//            vendor.setPrice(vendorObj.getPrice());
//            vendor.setAddress(vendorObj.getAddress());
//            vendor.setContactInfo(vendorObj.getContactInfo());
//            vendorRepository.save(vendor);
//        }
//    }
//
//    @Override
//    public Vendor getVendorById(Long id) {
//        return vendorRepository.findById(id).orElse(null);
//    }
//}
package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.repository.VendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<Vendor> findByZipCode(String zipCode) {
        return vendorRepository.findByZipCodeOrderByRatingDesc(zipCode);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public List<Vendor> findByCategory(String category) {
        return vendorRepository.findByCategory(category);
    }

    @Override
    public Long saveVendor(Vendor vendor) {
//        if (vendor.getReviews() == null) {
//            vendor.setReviews(""); // Set a default empty string if reviews are null
//        }
        Vendor savedVendor = vendorRepository.save(vendor);
        return savedVendor.getId();
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public void deleteAllVendors() {
        vendorRepository.deleteAll();
    }

    @Override
    public void updateVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

//    @Override
//    public Vendor getVendorById(Long id) {
//        return vendorRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Vendor not found with id: " + id));
//    }
}
