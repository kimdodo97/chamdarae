package com.chamdarae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/login")
    public String loginForm() {
        System.out.println("login");
        return "view/login";
    }

    @GetMapping("/join")
    public String joinForm(){
        System.out.println("join");
        return "view/join";
    }

}
