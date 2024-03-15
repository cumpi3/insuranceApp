package cz.pojisteniApp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String Index() {
        // Vrátí na hlavní stránku
        return "index"; // Vrátí název view pro hlavní stránku.
    }
}