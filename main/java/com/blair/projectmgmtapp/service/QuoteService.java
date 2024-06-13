package com.blair.projectmgmtapp.service;

import java.util.List;
import com.blair.projectmgmtapp.model.Quote;

public interface QuoteService {
    List<Quote> getAllQuotes();
    Quote saveQuote(Quote quote);
    Quote getQuoteById(Long id);
    Quote updateQuote(Quote quote);
    void deleteQuoteById(Long id);

//    Quote createQuoteForVendor(Long vendorId);
}
