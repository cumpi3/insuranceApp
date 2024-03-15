package cz.pojisteniApp.spring.controllers;

import cz.pojisteniApp.spring.models.UserDB;
import cz.pojisteniApp.spring.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Optional;
//Controller pro změnu osobních údajů
@Controller
public class ChangePersonaInformationController {
    @Autowired
    private UserRepository userRepository;
    //Get požadavek
    @GetMapping("/editovat-osobni-udaje")
    public String ChangePersonalInformation() {
        // Metoda pro zobrazení stránky pro editaci osobních údajů.
        return "editovat-osobni-udaje";
    }
    // Post požadavek
    @PostMapping("/editovat-osobni-udaje")
    public String updatePersonalInformation(@ModelAttribute UserDB userDB, @RequestParam String email, @RequestParam String street,
                                            @RequestParam String city,
                                            HttpSession session,
                                            Model model) {
        // Metoda pro aktualizaci osobních údajů uživatele.
        UserDB user = (UserDB) session.getAttribute("loggedInUser"); // Získá aktuálně přihlášeného uživatele ze session.
        boolean hasError = false;
        Optional<UserDB> existujiciUzivatel = userRepository.findByEmail(userDB.getEmail());
        // Značky pro email
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        //Podmínky pro správné vyplnění a chybové hlášky
        if (existujiciUzivatel.isPresent()) {
            model.addAttribute("emailAlreadyUsedError", "E-mail je již registrován!");
            hasError = true;
        }

        if (userDB.getEmail().isEmpty()) {
            model.addAttribute("emailEmpty", "Vypňte email");
        }

        if (!userDB.getEmail().matches(emailPattern)) {
            model.addAttribute("emailError", "E-mail není ve správném formátu.");
            hasError = true;
        }

        if (userDB.getStreet().trim().isEmpty()) {
            model.addAttribute("streetError", "Zadejte název ulice");
            hasError = true;
        }

        if (userDB.getCity().trim().isEmpty()) {
            model.addAttribute("cityError", "Zadejte název města");
            hasError = true;
        }

        if (user != null) {
            user.setEmail(email);
            user.setStreet(street);
            user.setCity(city);
            userRepository.save(user); // Uložení aktualizovaných údajů uživatele do databáze.
            return "editovat-osobni-udaje"; // Přesměrování zpět na stránku pro editaci osobních údajů.
        } else {
            return "redirect:/prihlaseni";
        }
    }
}