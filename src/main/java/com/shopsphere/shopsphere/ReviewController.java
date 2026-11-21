
package com.shopsphere.shopsphere;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewController {

    public static List<Review> reviews = new ArrayList<>();

    @PostMapping("/add-review")
    public String addReview(
            @RequestParam int productId,
            @RequestParam String customerName,
            @RequestParam int rating,
            @RequestParam String comment,
            RedirectAttributes redirectAttributes) {

        if (rating < 1 || rating > 5) {
            redirectAttributes.addFlashAttribute("message", "Rating must be between 1 and 5.");
            return "redirect:/product/" + productId;
        }

        reviews.add(new Review(productId, customerName, rating, comment));

        redirectAttributes.addFlashAttribute("message", "Review submitted successfully!");

        return "redirect:/product/" + productId;
    }
}
