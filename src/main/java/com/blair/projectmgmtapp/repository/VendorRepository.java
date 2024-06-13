//package com.blair.projectmgmtapp.repository;
//
//
//
//import com.blair.projectmgmtapp.model.Vendor;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface VendorRepository extends JpaRepository<Vendor, Long> {
//
//    List<Vendor> findByZipcodeOrderByRatingDesc(String zipcode);
//}
package com.blair.projectmgmtapp.repository;

import com.blair.projectmgmtapp.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    List<Vendor> findByCategory(String category);

    List<Vendor> findByZipCodeOrderByRatingDesc(String zipCode);
}
