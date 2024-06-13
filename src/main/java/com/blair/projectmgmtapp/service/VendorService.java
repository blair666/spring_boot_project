//package com.blair.projectmgmtapp.service;
//
//import com.blair.projectmgmtapp.model.Vendor;
//import java.util.List;
//
//public interface VendorService {
//    List<Vendor> findByZipCode(String zipcode);
//    List<Vendor> getAllVendors();
//    long saveVendor(Vendor vendor);
//    void deleteVendorById(long id);
//    void deleteAllVendors();
//    void updateVendor(long vendorId, Vendor vendorObj);
//    Vendor getVendorById(Long id);
//}
package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Vendor;

import java.util.List;

public interface VendorService {

    List<Vendor> getAllVendors();

    List<Vendor> findByCategory(String category);

    List<Vendor> findByZipCode(String zipCode);

    Long saveVendor(Vendor vendor);

    Vendor getVendorById(Long id);

    void updateVendor(Vendor vendor);

    void deleteVendorById(Long id);

    void deleteAllVendors();
}
