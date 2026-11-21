
package com.shopsphere.shopsphere;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishlistController {

    @GetMapping("/wishlist")
    public String viewWishlist(HttpSession session, Model model) {

        List<Product> wishlist = (List<Product>) session.getAttribute("wishlist");

        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }

        model.addAttribute("wishlist", wishlist);

        return "wishlist";
    }

    @PostMapping("/add-to-wishlist")
    public String addToWishlist(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam String image,
            @RequestParam String category,
            HttpSession session) {

        List<Product> wishlist = (List<Product>) session.getAttribute("wishlist");

        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }

        boolean exists = false;

        for (Product product : wishlist) {
            if (product.getId() == id) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            wishlist.add(new Product(id, name, "", price, image, category));
        }

        session.setAttribute("wishlist", wishlist);

        return "redirect:/wishlist";
    }

    @PostMapping("/remove-from-wishlist")
    public String removeFromWishlist(@RequestParam int id, HttpSession session) {

        List<Product> wishlist = (List<Product>) session.getAttribute("wishlist");

        if (wishlist != null) {
            wishlist.removeIf(product -> product.getId() == id);
        }

        return "redirect:/wishlist";
    }
}
