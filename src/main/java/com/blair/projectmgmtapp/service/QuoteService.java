package com.blair.projectmgmtapp.service;

import java.util.List;
import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.model.Vendor;

public interface QuoteService {
    List<Quote> getAllQuotes();
    Quote saveQuote(Quote quote);
    Quote getQuoteById(Long id);
    Quote updateQuote(Quote quote);
    void deleteQuoteById(Long id);

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
    Vendor getVendorById(Long id);

//    Quote createQuoteForVendor(Long vendorId);
}
