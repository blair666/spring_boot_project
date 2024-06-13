package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {


    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorServiceImpl vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByZipCode() {
        String zipCode = "12345";
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setZipCode(zipCode);

        when(vendorRepository.findByZipCodeOrderByRatingDesc(zipCode)).thenReturn(List.of(vendor));

        List<Vendor> result = vendorService.findByZipCode(zipCode);

        assertEquals(1, result.size());
        assertEquals(vendor, result.get(0));
        verify(vendorRepository, times(1)).findByZipCodeOrderByRatingDesc(zipCode);
    }



}