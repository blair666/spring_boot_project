//package com.blair.projectmgmtapp.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.blair.projectmgmtapp.model.Quote;
//import com.blair.projectmgmtapp.service.QuoteService;
//
//@Controller
//public class QuoteController {
//
//    private QuoteService quoteService;
//
//    public QuoteController(QuoteService quoteService) {
//        this.quoteService = quoteService;
//    }
//
//    // handler method to handle list quotes and return model and view
//    @GetMapping("/quotes")
//    public String listQuotes(Model model) {
//        model.addAttribute("quotes", quoteService.getAllQuotes());
//        return "quotes";
//    }
//
//    @GetMapping("/quotes/new")
//    public String createQuoteForm(Model model) {
//        // create quote object to hold quote form data
//        Quote quote = new Quote();
//        model.addAttribute("quote", quote);
//        return "create_quote";
//    }
//
//    @PostMapping("/quotes")
//    public String saveQuote(@ModelAttribute("quote") Quote quote) {
//        quoteService.saveQuote(quote);
//        return "redirect:/quotes";
//    }
//
//    @GetMapping("/quotes/edit/{id}")
//    public String editQuoteForm(@PathVariable Long id, Model model) {
//        model.addAttribute("quote", quoteService.getQuoteById(id));
//        return "edit_quote";
//    }
//
//    @PostMapping("/quotes/{id}")
//    public String updateQuote(@PathVariable Long id,
//                              @ModelAttribute("quote") Quote quote,
//                              Model model) {
//        // get quote from database by id
//        Quote existingQuote = quoteService.getQuoteById(id);
//        existingQuote.setId(id);
//        existingQuote.setVendorName(quote.getVendorName());
//        existingQuote.setProjectDescription(quote.getProjectDescription());
//        existingQuote.setPrice(quote.getPrice());
//
//        // save updated quote object
//        quoteService.updateQuote(existingQuote);
//        return "redirect:/quotes";
//    }
//
//    // handler method to handle delete quote request
//    @GetMapping("/quotes/{id}/delete")
//    public String deleteQuote(@PathVariable Long id) {
//        quoteService.deleteQuoteById(id);
//        return "redirect:/quotes";
//    }
//}
package com.blair.projectmgmtapp.controller;

import com.blair.projectmgmtapp.model.Quote;
import com.blair.projectmgmtapp.service.QuoteService;
import jakarta.persistence.EntityNotFoundException;
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
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
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

    @GetMapping("/new")
    public String createQuoteForm(Model model) {
        model.addAttribute("quote", new Quote());
        return "create_quote"; // Assuming "create_quote" is a Thymeleaf template name
    }

    @PostMapping
    public String saveQuote(@ModelAttribute("quote") Quote quote) {
        quoteService.saveQuote(quote);
        return "redirect:/quotes";
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

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> listQuotes() {
        try {
            List<Quote> quotes = quoteService.getAllQuotes();
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}










//lastest
//package com.blair.projectmgmtapp.controller;
//
//import com.blair.projectmgmtapp.model.Quote;
//import com.blair.projectmgmtapp.service.QuoteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/quotes")
//public class QuoteController {
//
//    @Autowired
//    private QuoteService quoteService;
//
//    public QuoteController(QuoteService quoteService) {
//        this.quoteService = quoteService;
//    }
//
//    @GetMapping
//    public String listQuotes(@RequestParam(value = "sort", required = false) String sort, Model model) {
//        List<Quote> quotes = quoteService.getAllQuotes();
//
//        if ("price_asc".equals(sort)) {
//            quotes = quotes.stream()
//                    .sorted((q1, q2) -> Double.compare(q1.getPrice(), q2.getPrice()))
//                    .collect(Collectors.toList());
//        } else if ("price_desc".equals(sort)) {
//            quotes = quotes.stream()
//                    .sorted((q1, q2) -> Double.compare(q2.getPrice(), q1.getPrice()))
//                    .collect(Collectors.toList());
//        }
//
//        model.addAttribute("quotes", quotes);
//        return "quotes";
//    }
//
//    @GetMapping("/new")
//    public String createQuoteForm(Model model) {
//        model.addAttribute("quote", new Quote());
//        return "create_quote";
//    }
//
//    @PostMapping
//    public String saveQuote(@ModelAttribute("quote") Quote quote) {
//        quoteService.saveQuote(quote);
//        return "redirect:/quotes";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editQuoteForm(@PathVariable Long id, Model model) {
//        model.addAttribute("quote", quoteService.getQuoteById(id));
//        return "edit_quote";
//    }
//
//    @PostMapping("/{id}")
//    public String updateQuote(@PathVariable Long id, @ModelAttribute("quote") Quote quote) {
//        Quote existingQuote = quoteService.getQuoteById(id);
//        existingQuote.setVendorName(quote.getVendorName());
//        existingQuote.setProjectDescription(quote.getProjectDescription());
//        existingQuote.setPrice(quote.getPrice());
//        quoteService.updateQuote(existingQuote);
//        return "redirect:/quotes";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteQuote(@PathVariable Long id) {
//        quoteService.deleteQuoteById(id);
//        return "redirect:/quotes";
//    }
//
//    @GetMapping("/compare")
//    public String compareQuotes(@RequestParam("quoteIds") List<Long> quoteIds, Model model) {
//        List<Quote> quotes = quoteIds.stream()
//                .map(quoteService::getQuoteById)
//                .collect(Collectors.toList());
//        model.addAttribute("quotes", quotes);
//        return "compare_quotes";
//    }
//}
