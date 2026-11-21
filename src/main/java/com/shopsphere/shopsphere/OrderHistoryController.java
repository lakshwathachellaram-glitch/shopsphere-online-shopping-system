
package com.shopsphere.shopsphere;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderHistoryController {

    public static List<Order> orders = new ArrayList<>();

    public static int nextOrderId = 1001;

    @GetMapping("/order-history")
    public String orderHistory(HttpSession session, Model model) {

        String email = (String) session.getAttribute("loggedInEmail");

        if (email == null) {
            return "redirect:/login";
        }

        List<Order> customerOrders = new ArrayList<>();

        for (Order order : orders) {
            if (order.getEmail().equalsIgnoreCase(email)) {
                customerOrders.add(order);
            }
        }

        model.addAttribute("orders", customerOrders);

        return "order-history";
    }
}
