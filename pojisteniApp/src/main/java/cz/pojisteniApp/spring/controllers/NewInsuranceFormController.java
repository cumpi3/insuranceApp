package cz.pojisteniApp.spring.controllers;

import cz.pojisteniApp.spring.models.InsuranceDB;
import cz.pojisteniApp.spring.models.UserDB;
import cz.pojisteniApp.spring.repositories.InsuranceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
@Controller
public class NewInsuranceFormController {
    @Autowired
    InsuranceRepository insuranceRepository;
    //get požadavek
    @GetMapping("/sjednat-pojisteni")
    public String newInsurance() {

        return "sjednat-pojisteni";
    }

    //post požadavek
    @PostMapping("/sjednat-pojisteni")
    public String confirmNewInsurance(@ModelAttribute InsuranceDB insuranceDB, HttpSession session, Model model) {
        boolean hasError = false;

        // Kontrola, zda js hodnoty null nebo prázdné
        if (insuranceDB.getInsuranceType() == null || insuranceDB.getInsuranceType().trim().isEmpty()) {
            model.addAttribute("insuranceTypeError", "Vyberte typ pojištění");
            hasError = true;
        }

        if (insuranceDB.getInsuranceValue() == 0) {
            model.addAttribute("insuranceValueError", "Vyberte hodnotu pojištění");
            hasError = true;
        }
        // vyhodí chybové hlášky
        if (hasError) {
            return "sjednat-pojisteni";
            // úspěšné odeslání formuláře
        } else {
            UserDB loggedInUser = (UserDB) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
                insuranceDB.setUser(loggedInUser);
                insuranceRepository.save(insuranceDB);
                return "redirect:/pojisteni";
            } else {
                return "prihlaseni";
            }
        }
    }
}
