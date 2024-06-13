package com.blair.projectmgmtapp.service;

import java.util.ArrayList;
import java.util.List;

import com.blair.projectmgmtapp.model.Category;
import com.blair.projectmgmtapp.model.Project;
import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.repository.VendorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.repository.QuoteRepository;


@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final VendorRepository vendorRepository;

    private final List<Vendor> vendors = new ArrayList<>(); // Initialize dummy vendors list
    //dummy data
    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, VendorRepository vendorRepository) {
        this.quoteRepository = quoteRepository;
        this.vendorRepository = vendorRepository;
        initializeDummyVendors(); // Populate dummy vendors list when service is instantiated
    }

    // Method to initialize dummy vendors
    private void initializeDummyVendors() {
        vendors.add(new Vendor("ABC Plumbing", "Plumbing", "12345"));
        vendors.add(new Vendor("XYZ Landscaping", "Landscaping", "67890"));
        vendors.add(new Vendor("HandyTech", "Handyperson", "54321"));
        vendors.add(new Vendor("ElectroPlus", "Electrical", "98765"));
        vendors.add(new Vendor("RoofMaster", "Roofing", "13579"));
    }


//    @Autowired
//    public QuoteServiceImpl(QuoteRepository quoteRepository, VendorRepository vendorRepository) {
//        this.quoteRepository = quoteRepository;
//        this.vendorRepository = vendorRepository;
//    }



//    public QuoteServiceImpl(QuoteRepository quoteRepository, VendorRepository vendorRepository) {
//        this.quoteRepository = quoteRepository;
//        this.vendorRepository = vendorRepository;
//    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

//    @Override
//    public Quote saveQuote(Quote quote) {
//        return quoteRepository.save(quote);
//    }
    @Override
    public Quote saveQuote(Quote quote) {
        if (quote.getVendorName() == null || quote.getVendorName().trim().isEmpty()) {
            throw new IllegalArgumentException("Vendor name must not be null or empty");
        }
        return quoteRepository.save(quote);
}

    @Override
    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public void deleteQuoteById(Long id) {
        quoteRepository.deleteById(id);
    }

//    @Override
//    public Quote createQuoteForVendor(Long vendorId) {
//        if (vendorId == null || vendorId == 0) {
//            throw new IllegalArgumentException("Invalid Vendor ID");
//        }
//
//        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
//        if (vendor == null) {
//            throw new IllegalArgumentException("Vendor not found");
//        }
//
//        Quote quote = new Quote();
//        quote.setVendor(vendor);
//        quote.setVendorName(vendor.getName());
//        quote.setPrice(vendor.getPrice());
//
//        return quoteRepository.save(quote);
//    }
@Override
public Vendor getVendorById(Long id) {

    return vendorRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Vendor with id " + id + " not found"));
}



}
