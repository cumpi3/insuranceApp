    package cz.pojisteniApp.spring.controllers;

    import cz.pojisteniApp.spring.models.InsuranceDB;
    import cz.pojisteniApp.spring.models.UserDB;
    import cz.pojisteniApp.spring.repositories.InsuranceRepository;
    import jakarta.servlet.http.HttpSession;
    import org.apache.catalina.User;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.List;
    import java.util.Optional;

    @Controller
    public class EditInsuranceController {
        @Autowired
        private InsuranceRepository insuranceRepository;

        //get požaavek
        @GetMapping("/upravit-pojisteni")
        public String showUserInsurances(HttpSession session, Model model) {
            // Metoda pro zobrazení pojistných smluv přihlášeného uživatele.
            UserDB user = (UserDB) session.getAttribute("loggedInUser"); // Získá aktuálně přihlášeného uživatele ze session.
            if (user != null) {
                // Pokud je uživatel přihlášen, načtou se jeho pojistné smlouvy.
                List<InsuranceDB> insurances = insuranceRepository.findByUser(user);
                model.addAttribute("insurances", insurances); // Přidá pojistné smlouvy do modelu pro zobrazení ve view.
                return "upravit-pojisteni"; // stránka s možností editace pojistných smluv
            } else {
                // Pokud uživatel není přihlášen, dojde k přesměrování na přihlašovací stránku.
                return "redirect:/prihlaseni";
            }
        }

        // Post požadavek
        @PostMapping("/upravit-pojisteni")
        public String editInsurance(@RequestParam("id") Long insuranceId,
                                    @RequestParam("insuranceValue") int insuranceValue,
                                    HttpSession session, Model model) {
            // Metoda pro úpravu hodnoty pojistné smlouvy.
            UserDB loggedInUser = (UserDB) session.getAttribute("loggedInUser"); // Získání přihlášeného uživatele ze session.
            if (loggedInUser != null) {
                Optional<InsuranceDB> insuranceOptional = insuranceRepository.findById(insuranceId); // Načtení pojistné smlouvy podle ID.
                if (insuranceOptional.isPresent()) {
                    // ověření platnosti smluv
                    InsuranceDB insurance = insuranceOptional.get();
                    insurance.setInsuranceValue(insuranceValue); // nastavení nové hodnoty pojistné smlouvy.
                    insuranceRepository.save(insurance); // Uložení změn do databáze.
                    List<InsuranceDB> insurances = insuranceRepository.findByUser(loggedInUser);
                    model.addAttribute("insurances", insurances); // Aktualizovaný seznam pojistných smluv
                    return "upravit-pojisteni";
                }
                return "upravit-pojisteni";
            } else {
                // Přesměrování na přihlašovací stránku v případě, že uživatel není přihlášen.
                return "redirect:/prihlaseni";
            }
        }
    }