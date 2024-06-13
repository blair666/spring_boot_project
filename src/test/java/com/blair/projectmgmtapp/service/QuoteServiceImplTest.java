package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.repository.QuoteRepository;
import com.blair.projectmgmtapp.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class QuoteServiceImplTest {

    private QuoteServiceImpl quoteService;
    private QuoteRepository quoteRepository;
    private VendorRepository vendorRepository;

    @BeforeEach
    void setUp() {
        quoteRepository = mock(QuoteRepository.class);
        vendorRepository = mock(VendorRepository.class);
        quoteService = new QuoteServiceImpl(quoteRepository, vendorRepository);
    }

    @Test
    void getAllQuotes() {
        List<Quote> quotes = quoteService.getAllQuotes();
        assertEquals(5, quotes.size(), "The number of quotes should be 5");

        // Verify the details of the first quote
        Quote firstQuote = quotes.get(0);
        assertEquals(1L, firstQuote.getId(), "The ID of the first quote should be 1");
        assertEquals("ABC Plumbing", firstQuote.getVendorName(), "The vendor name of the first quote should be 'ABC Plumbing'");
        assertEquals("Fix leaking pipe", firstQuote.getProjectDescription(), "The project description of the first quote should be 'Fix leaking pipe'");
        assertEquals(150.0, firstQuote.getPrice(), "The price of the first quote should be 150.0");
    }
}
