package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Vendor;

import java.util.List;

public interface VendorService {
    List<Vendor> getAllVendors();
    List<Vendor> findByCategory(String category);
    List<Vendor> findByZipCode(String zipCode);
    List<Vendor> findByZipCodeAndCategory(String zipCode, String category);
    Long saveVendor(Vendor vendor);
    Vendor getVendorById(Long id);
    void updateVendor(Vendor vendor);
    void deleteVendorById(Long id);
    void deleteAllVendors();
}
