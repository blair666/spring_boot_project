package com.blair.projectmgmtapp.controller;

import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.model.Vendor;
import com.blair.projectmgmtapp.service.QuoteService;
import com.blair.projectmgmtapp.service.VendorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quotes")
@Slf4j
public class QuoteController {

    @Autowired
    private QuoteService quoteService;
    private VendorService vendorService;

    public QuoteController(QuoteService quoteService,VendorService vendorService) {
        this.quoteService = quoteService;
        this.vendorService = vendorService;
    }

    @GetMapping
    public String listQuotes(@RequestParam(value = "sort", required = false) String sort, Model model) {
        List<Quote> quotes = quoteService.getAllQuotes();
        if ("price_asc".equals(sort)) {
            quotes = quotes.stream()
                    .sorted((q1, q2) -> Double.compare(q1.getPrice(), q2.getPrice()))
                    .collect(Collectors.toList());
        } else if ("price_desc".equals(sort)) {
            quotes = quotes.stream()
                    .sorted((q1, q2) -> Double.compare(q2.getPrice(), q1.getPrice()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("quotes", quotes);
        return "quotes"; // Assuming "quotes" is a Thymeleaf template name
    }

//    @GetMapping("/new")
//    public String createQuoteForm(Model model) {
//        model.addAttribute("quote", new Quote());
//        return "create_quote"; // Assuming "create_quote" is a Thymeleaf template name
//    }
    @GetMapping("/new")
    public String createQuoteForm(Model model) {
        model.addAttribute("quote", new Quote());
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "create_quote"; // Assuming "create_quote" is a Thymeleaf template name
    }


    //    @PostMapping
//    public String saveQuote(@ModelAttribute("quote") Quote quote) {
//        quoteService.saveQuote(quote);
//        return "redirect:/quotes";
//    }
//@PostMapping
//public String saveQuote(@ModelAttribute("quote") Quote quote, @RequestParam("vendorId") Long vendorId, Model model) {
//    try {
//        Vendor vendor = vendorService.getVendorById(vendorId); // Fetch the vendor
//        quote.setVendor(vendor); // Set the vendor in the quote
//        quoteService.saveQuote(quote); // Save the quote with the associated vendor
//        return "redirect:/quotes";
//    } catch (EntityNotFoundException ex) {
//        model.addAttribute("error", "Vendor not found");
//        return "error";
//    }
//}
    @PostMapping
    public ResponseEntity<Quote> saveQuote(@RequestBody Quote quote) {
        Quote savedQuote = quoteService.saveQuote(quote);
        return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
    }


    @GetMapping("/edit/{id}")
    public String editQuoteForm(@PathVariable Long id, Model model) {
        model.addAttribute("quote", quoteService.getQuoteById(id));
        return "edit_quote"; // Assuming "edit_quote" is a Thymeleaf template name
    }

    @PostMapping("/{id}")
    public String updateQuote(@PathVariable Long id, @ModelAttribute("quote") Quote quote) {
        Quote existingQuote = quoteService.getQuoteById(id);
        existingQuote.setVendorName(quote.getVendorName());
        existingQuote.setProjectDescription(quote.getProjectDescription());
        existingQuote.setPrice(quote.getPrice());
        quoteService.updateQuote(existingQuote);
        return "redirect:/quotes";
    }

    @GetMapping("/{id}/delete")
    public String deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuoteById(id);
        return "redirect:/quotes";
    }

    @GetMapping("/compare")
    public String compareQuotes(@RequestParam("quoteIds") List<Long> quoteIds, Model model) {
        List<Quote> quotes = quoteIds.stream()
                .map(quoteService::getQuoteById)
                .collect(Collectors.toList());
        model.addAttribute("quotes", quotes);
        return "compare_quotes"; // Assuming "compare_quotes" is a Thymeleaf template name
    }

    @GetMapping("/list")
    public ResponseEntity<List<Quote>> getQuotes() {
        try {
            List<Quote> quotes = quoteService.getAllQuotes();
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/create")
//    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
//        if (quote.getVendor() == null || quote.getVendor().getId() == 0) {
//            throw new IllegalArgumentException("Invalid Vendor ID");
//        }
//        return new ResponseEntity<>(quoteService.saveQuote(quote), HttpStatus.CREATED);
//    }
@PostMapping("/create")
public ResponseEntity<?> createQuote(@ModelAttribute Quote quote) {
    // Validate vendor ID
    if (quote.getVendor() == null || quote.getVendor().getId() == null) {
        return new ResponseEntity<>("Vendor ID is required", HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(quoteService.saveQuote(quote), HttpStatus.CREATED);
}


}
