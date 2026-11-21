
package com.shopsphere.shopsphere;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "") String keyword,
                      @RequestParam(defaultValue = "") String category,
                      Model model) {

        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "Casual Sneakers",
                "Comfortable everyday footwear", 1499,
                "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=500&q=80",
                "Fashion"));

        products.add(new Product(2, "Classic Watch",
                "Simple and elegant design", 1999,
                "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=500&q=80",
                "Accessories"));

        products.add(new Product(3, "Everyday Backpack",
                "Perfect for work and travel", 999,
                "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?auto=format&fit=crop&w=500&q=80",
                "Accessories"));

        products.add(new Product(4, "Wireless Headphones",
                "Enjoy music wherever you go", 2499,
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=500&q=80",
                "Electronics"));

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {

            boolean matchesKeyword =
                    product.getName().toLowerCase().contains(keyword.toLowerCase())
                    || product.getDescription().toLowerCase().contains(keyword.toLowerCase());

            boolean matchesCategory =
                    category.isEmpty()
                    || product.getCategory().equalsIgnoreCase(category);

            if (matchesKeyword && matchesCategory) {
                filteredProducts.add(product);
            }
        }

        model.addAttribute("products", filteredProducts);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);

        return "index";
    }
}
