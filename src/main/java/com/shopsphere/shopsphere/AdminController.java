
package com.shopsphere.shopsphere;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        List<Order> orders = OrderHistoryController.orders;
        List<Customer> customers = AccountController.customers;

        double totalSales = 0;

        for (Order order : orders) {
            totalSales += order.getTotal();
        }

        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        model.addAttribute("totalOrders", orders.size());
        model.addAttribute("totalCustomers", customers.size());
        model.addAttribute("totalSales", totalSales);

        return "admin";
    }
}
