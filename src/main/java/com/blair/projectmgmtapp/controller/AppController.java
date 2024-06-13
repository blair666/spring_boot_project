package com.blair.projectmgmtapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class AppController {

//    @GetMapping
//    public String getHomePage() {
//        return "home_page";
//}

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String loginPage(Model model) {

        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        log.info("user registered");
        return "register";
    }

}
