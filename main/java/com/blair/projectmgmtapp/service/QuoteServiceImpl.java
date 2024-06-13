package com.blair.projectmgmtapp.service;

import java.util.List;

//import com.blair.projectmgmtapp.model.Vendor;
//import com.blair.projectmgmtapp.repository.VendorRepository;
import org.springframework.stereotype.Service;
import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.repository.QuoteRepository;

@Service
public class QuoteServiceImpl implements QuoteService {
    private QuoteRepository quoteRepository;

    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }
//    private VendorRepository vendorRepository;


//    public QuoteServiceImpl(QuoteRepository quoteRepository, VendorRepository vendorRepository) {
//        this.quoteRepository = quoteRepository;
//        this.vendorRepository = vendorRepository;
//    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote saveQuote(Quote quote) {
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


}
