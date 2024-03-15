package cz.pojisteniApp.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyProfileController {
    // přeměrování na Můj profil
    @GetMapping("/myProfile")
    public String myProfile() {
        return "myProfile";
    }

}
