
package com.shopsphere.shopsphere;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        double total = 0;

        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(HttpSession session, Model model) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        double total = 0;

        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        model.addAttribute("total", total);

        session.removeAttribute("cart");

        return "order-success";
    }
}
