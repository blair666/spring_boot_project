package com.blair.projectmgmtapp.controller;

import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.service.QuoteService;
import com.blair.projectmgmtapp.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class QuoteControllerTest {

    @Mock
    private QuoteService quoteService;

    @Mock
    private VendorService vendorService;

    @Mock
    private Model model;

    @InjectMocks
    private QuoteController quoteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createQuoteForm() {
        // Arrange
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Vendor1");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Vendor2");

        List<Vendor> vendors = Arrays.asList(vendor1, vendor2);
        when(vendorService.getAllVendors()).thenReturn(vendors);


        String viewName = quoteController.createQuoteForm(model);


        verify(model).addAttribute("quote", new Quote());
        verify(model).addAttribute("vendors", vendors);
        assertEquals("create_quote", viewName);
    }
}
