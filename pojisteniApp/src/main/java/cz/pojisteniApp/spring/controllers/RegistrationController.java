    package cz.pojisteniApp.spring.controllers;

    import org.springframework.ui.Model;
    import cz.pojisteniApp.spring.models.UserDB;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PostMapping;
    import cz.pojisteniApp.spring.repositories.UserRepository;
    import org.springframework.web.bind.annotation.RequestParam;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.Optional;

    @Controller
    public class RegistrationController {
        @Autowired
        private UserRepository userRepository;

        //get požadavek
        @GetMapping("/registrace")
        public String Registration(Model model) {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //získání aktuálního data
            model.addAttribute("maxDate", today.format(formatter));
            model.addAttribute("defaultDate", today.minusYears(18).format(formatter));
            return "registrace";
        }
        //post požadavek
        @PostMapping("/registrace")
        public String ConfirmForm(@ModelAttribute UserDB userDB, @RequestParam String passwordRepeat, Model model) {
            boolean hasError = false;
            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

            //ověření, zda uživatel již neni registrován na základě mailu
            Optional<UserDB> existujiciUzivatel = userRepository.findByEmail(userDB.getEmail());

            if (existujiciUzivatel.isPresent()) {
                model.addAttribute("emailError", "E-mail je již registrován!");
                hasError = true;
            }
            //další podmínky pro kontrolu
            if (userDB.getFirstName().trim().isEmpty()) {
                model.addAttribute("firstNameError", "Zadejte jméno.");
                hasError = true;
            }

            if (!userDB.getFirstName().matches("^\\D*$")) {
                model.addAttribute("firstNameError", "Jméno nesmí obsahovat číslice.");
                hasError = true;
            }

            if (userDB.getLastName().trim().isEmpty()) {
                model.addAttribute("lastNameError", "Zadejte příjmení");
                hasError = true;
            }

            if (!userDB.getLastName().matches("^\\D*$")) {
                model.addAttribute("lastNameError", "Příjmení nesmí obsahovat číslice.");
                hasError = true;
            }

            if (userDB.getPlaceOfBirth().trim().isEmpty()) {
                model.addAttribute("placeOfBirthError", "Zadejte místo narození");
                hasError = true;
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

            if (userDB.getEmail().trim().isEmpty()) {
                model.addAttribute("emailError", "Zadejte Email");
                hasError = true;
            }

            if (userDB.getPassword().trim().length() < 6) {
                model.addAttribute("passwordError", "Zadejte heslo o délce min 6 znaků");
                hasError = true;
            }

            if (!userDB.getPassword().equals(passwordRepeat)) {
                model.addAttribute("passwordRepeatError", "Hesla se neshodují.");
                hasError = true;
            }
            //vyhodí chybovou hlášku
            if(hasError)
                return "registrace";

            //uloží uživatele a přesměruje na index
            else {
                userRepository.save(userDB);
                return "redirect:/index";
            }
        }
    }