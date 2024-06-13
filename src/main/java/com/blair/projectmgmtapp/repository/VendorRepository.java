package com.blair.projectmgmtapp.repository;

import com.blair.projectmgmtapp.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByCategory(String category);

    //later need to update for rating sorting but currently doesn't have it
    @Query(value = "SELECT * FROM testing.vendors WHERE zip_code =:zip",nativeQuery = true)
    List<Vendor> findByZipCodeOrderByRatingDesc(@Param("zip") String zip);
    List<Vendor> findByZipCodeAndCategoryOrderByRatingDesc(String zipCode, String category);

}
