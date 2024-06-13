package com.blair.projectmgmtapp.repository;

import com.blair.projectmgmtapp.model.Vendor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VendorRepositoryTest {

    @Autowired
    VendorRepository vendorRepository;

    @Test
    public void findVendorByZipCodeTest(){

        List<Vendor> vendorList = vendorRepository.findByZipCodeOrderByRatingDesc("91010");
        Assertions.assertTrue(vendorList.size() > 0);
    }

}
