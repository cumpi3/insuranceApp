package cz.pojisteniApp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReferencesController {
    @GetMapping("/reference")
    // přesměrování na stránku reference
    public String pojistenci() {
        return "reference";
    }
}

