    package cz.pojisteniApp.spring.controllers;

    import jakarta.servlet.http.HttpSession;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class LogOutController {
        // přesměrování na index
        @GetMapping("/logout")
        // odhlášení uživatele
        public String logout(HttpSession session) {
            session.invalidate();
            return "redirect:/index";
        }
    }
