package cz.pojisteniApp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsuranceController {
    @GetMapping("/pojisteni")
    public String pojisteni() {
        // Odkáže na stránku pojištění
        return "pojisteni";
    }

}
