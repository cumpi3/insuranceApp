    package cz.pojisteniApp.spring.controllers;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;
    import cz.pojisteniApp.spring.repositories.UserRepository;
    import cz.pojisteniApp.spring.models.UserDB;

    import java.util.Optional;
    import org.springframework.ui.Model;
    import jakarta.servlet.http.HttpSession;
    @Controller
    public class LogInController {
        @Autowired
        private UserRepository userRepository; // Slouží pro přístup k uživatelským datům.

        @GetMapping("/prihlaseni")
        public String LogInPage() {
            // zobrazení přihlašovací stránky.
            return "prihlaseni"; // přihlašovací stránka.
        }

        @PostMapping("/prihlaseni")
        public String login(@RequestParam String Email, @RequestParam String Password, Model model, HttpSession session) {
            // Metoda pro zpracování přihlášení uživatele.
            Optional<UserDB> userOptional = userRepository.findByEmail(Email); // Hledá uživatele podle emailu.
            if (userOptional.isPresent()) {
                UserDB user = userOptional.get();
                if (user.getPassword().equals(Password)) {
                    // Kontrola, zda heslo odpovídá heslu uloženému v databázi.
                    session.setAttribute("loggedInUser", user); // Přidání uživatele do session jako přihlášeného uživatele.
                    return "index"; // Přesměrování na hlavní stránku po úspěšném přihlášení.
                }
            }
            // V případě, že email není nalezen nebo heslo nesouhlasí, zobrazí se chybová zpráva.
            model.addAttribute("loginError", "Zadali jste nesprávné přihlašovací údaje.");
            return "prihlaseni"; // Vrácení uživatele zpět stránku přihlášení
        }
    }