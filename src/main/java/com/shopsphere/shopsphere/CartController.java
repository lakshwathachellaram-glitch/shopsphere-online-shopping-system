
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
public class CartController {

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        double total = 0;

        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam int id,
                           @RequestParam String name,
                           @RequestParam double price,
                           @RequestParam String image,
                           HttpSession session) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;

        for (CartItem item : cart) {
            if (item.getProduct().getId() == id) {
                item.increaseQuantity();
                found = true;
                break;
            }
        }

        if (!found) {
            Product product = new Product(id, name, "", price, image);
            cart.add(new CartItem(product, 1));
        }

        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam int id,
                               HttpSession session) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> item.getProduct().getId() == id);
            session.setAttribute("cart", cart);
        }

        return "redirect:/cart";
    }
}
