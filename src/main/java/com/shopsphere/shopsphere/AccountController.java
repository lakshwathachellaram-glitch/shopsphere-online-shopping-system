
package com.shopsphere.shopsphere;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    public static List<Customer> customers = new ArrayList<>();

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)) {
                model.addAttribute("message", "Email already registered!");
                return "register";
            }
        }

        customers.add(new Customer(name, email, password));

        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(email)
                    && customer.getPassword().equals(password)) {

                session.setAttribute("loggedInUser", customer.getName());
                session.setAttribute("loggedInEmail", customer.getEmail());

                return "redirect:/";
            }
        }

        model.addAttribute("message", "Invalid email or password!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser");
        session.removeAttribute("loggedInEmail");
        return "redirect:/";
    }
}
