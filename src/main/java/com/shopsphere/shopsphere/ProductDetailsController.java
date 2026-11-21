
package com.shopsphere.shopsphere;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductDetailsController {

    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable int id, Model model) {

        Product product = null;

        if (id == 1) {
            product = new Product(1, "Casual Sneakers",
                    "Comfortable everyday footwear", 1499,
                    "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=500&q=80",
                    "Fashion");
        }

        else if (id == 2) {
            product = new Product(2, "Classic Watch",
                    "Simple and elegant design", 1999,
                    "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=500&q=80",
                    "Accessories");
        }

        else if (id == 3) {
            product = new Product(3, "Everyday Backpack",
                    "Perfect for work and travel", 999,
                    "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?auto=format&fit=crop&w=500&q=80",
                    "Accessories");
        }

        else if (id == 4) {
            product = new Product(4, "Wireless Headphones",
                    "Enjoy music wherever you go", 2499,
                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=500&q=80",
                    "Electronics");
        }

        if (product == null) {
            return "redirect:/";
        }

        model.addAttribute("product", product);

        return "product-details";
    }
}
