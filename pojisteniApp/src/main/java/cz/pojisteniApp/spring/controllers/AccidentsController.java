package cz.pojisteniApp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccidentsController {
    // Get požadavek
    @GetMapping("udalosti")
    public String udalosti() {
        return "udalosti";
    }
}