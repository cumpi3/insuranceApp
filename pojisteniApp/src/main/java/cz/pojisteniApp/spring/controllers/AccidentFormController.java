package cz.pojisteniApp.spring.controllers;

import cz.pojisteniApp.spring.models.AccidentDB;
import cz.pojisteniApp.spring.repositories.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.time.Period;
@Controller
public class AccidentFormController {

    @Autowired
    private AccidentRepository accidentRepository;

    // Metoda pro zpracování GET požadavku na zobrazení formuláře pro událost
    @GetMapping("/formularUdalost")
    public String incidentForm() {
        // Vrací název HTML souboru
        return "formularUdalost";
    }

    // Metoda pro zpracování POST požadavku po odeslání formuláře
    @PostMapping ("/formularUdalost")
    public String confirmAccident(@ModelAttribute AccidentDB accidentDB, Model model) {
        boolean hasError = false;
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        if (accidentDB.getDateOfIncident() == null ||
                accidentDB.getDateOfIncident().isAfter(LocalDate.now()) ||
                accidentDB.getDateOfIncident().isBefore(oneMonthAgo)) {
            model.addAttribute("dateOfIncidentError", "Datum události musí být mezi " + oneMonthAgo + " a dneškem.");
            hasError = true;
        }
        // Chybové hlášky při špatném vyplnění
        if (accidentDB.getPlaceOfIncident().trim().isEmpty()) {
            model.addAttribute("placeOfIncidentError", "Vyplňte místo události");
            hasError = true;
        }

        if (accidentDB.getDescriptionIncident().trim().isEmpty()) {
            model.addAttribute("descriptionError", "Vyplňte popis události");
            hasError = true;
        }

        if (accidentDB.getInsuranceType() == null || accidentDB.getInsuranceType().trim().isEmpty()) {
            model.addAttribute("insuranceTypeError", "Vyberte typ pojištění");
            hasError = true;
        }
        if (hasError) {
            // Pokud dojde k chybě, vrátí se na formulář s chybami
            return "formularUdalost";
        } else {
            // Uložení objektu do databáze a přesměrování
            accidentRepository.save(accidentDB);
            return "redirect:/udalosti";
        }
    }
}