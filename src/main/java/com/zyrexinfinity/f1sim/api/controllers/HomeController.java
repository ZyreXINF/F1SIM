package com.zyrexinfinity.f1sim.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
    @RequestMapping("/home")
    public String home(){
        return "index.html";
    }
}
