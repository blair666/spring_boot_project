package com.blair.projectmgmtapp.service;

import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.repository.QuoteRepository;
import com.blair.projectmgmtapp.repository.VendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final VendorRepository vendorRepository;
    private final List<Quote> quotes = new ArrayList<>(); // Initialize dummy quotes list
    private final List<Vendor> vendors = new ArrayList<>(); // Initialize dummy vendors list

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, VendorRepository vendorRepository) {
        this.quoteRepository = quoteRepository;
        this.vendorRepository = vendorRepository;
        initializeDummyVendors();
        initializeDummyQuotes(); // Populate dummy quotes list when service is instantiated
    }

    // Method to initialize dummy vendors
    private void initializeDummyVendors() {
        vendors.add(new Vendor("ABC Plumbing", "Plumbing", "12345"));
        vendors.add(new Vendor("XYZ Landscaping", "Landscaping", "67890"));
        vendors.add(new Vendor("HandyTech", "Handyperson", "54321"));
        vendors.add(new Vendor("ElectroPlus", "Electrical", "98765"));
        vendors.add(new Vendor("RoofMaster", "Roofing", "13579"));
    }

    // Method to initialize dummy quotes
    private void initializeDummyQuotes() {
        quotes.add(new Quote(1L, "ABC Plumbing", "Fix leaking pipe", 150.0, vendors.get(0)));
        quotes.add(new Quote(2L, "XYZ Landscaping", "Garden makeover", 1200.0, vendors.get(1)));
        quotes.add(new Quote(3L, "HandyTech", "Install shelves", 75.0, vendors.get(2)));
        quotes.add(new Quote(4L, "ElectroPlus", "Electrical wiring", 450.0, vendors.get(3)));
        quotes.add(new Quote(5L, "RoofMaster", "Roof repair", 800.0, vendors.get(4)));
    }

    @Override
    public List<Quote> getAllQuotes() {
        return new ArrayList<>(quotes); // Return a copy of the dummy quotes list
    }

    @Override
    public Quote saveQuote(Quote quote) {
        if (quote.getVendorName() == null || quote.getVendorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Vendor name must not be null or empty");
        }
        quotes.add(quote); // Add the quote to the dummy list
        return quote;
    }

    @Override
    public Quote getQuoteById(Long id) {
        return quotes.stream()
                .filter(quote -> quote.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Quote updateQuote(Quote quote) {
        Quote existingQuote = getQuoteById(quote.getId());
        if (existingQuote != null) {
            existingQuote.setVendorName(quote.getVendorName());
            existingQuote.setProjectDescription(quote.getProjectDescription());
            existingQuote.setPrice(quote.getPrice());
        }
        return existingQuote;
    }

    @Override
    public void deleteQuoteById(Long id) {
        quotes.removeIf(quote -> quote.getId().equals(id));
    }

    @Override
    public Vendor getVendorById(Long id) {
        return vendors.stream()
                .filter(vendor -> vendor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Vendor with id " + id + " not found"));
    }
}
