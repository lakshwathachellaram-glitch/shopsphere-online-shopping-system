
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

        double subtotal = calculateSubtotal(cart);
        double discount = calculateDiscount(session, subtotal);
        double total = subtotal - discount;

        model.addAttribute("cart", cart);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("discount", discount);
        model.addAttribute("total", total);

        model.addAttribute("couponCode", session.getAttribute("couponCode"));
        model.addAttribute("couponMessage", session.getAttribute("couponMessage"));

        return "checkout";
    }

    @PostMapping("/place-order")
    public String placeOrder(HttpSession session, Model model) {

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        double subtotal = calculateSubtotal(cart);
        double discount = calculateDiscount(session, subtotal);
        double total = subtotal - discount;

        String customerName = (String) session.getAttribute("loggedInUser");
        String email = (String) session.getAttribute("loggedInEmail");

        if (customerName == null) {
            customerName = "Guest";
        }

        if (email == null) {
            email = "Guest";
        }

        Order order = new Order(
                OrderHistoryController.nextOrderId++,
                customerName,
                email,
                total
        );

        OrderHistoryController.orders.add(order);

        model.addAttribute("orderId", order.getOrderId());
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("discount", discount);
        model.addAttribute("total", total);

        session.removeAttribute("cart");
        session.removeAttribute("couponCode");
        session.removeAttribute("discountType");
        session.removeAttribute("discountValue");
        session.removeAttribute("couponMessage");

        return "order-success";
    }

    private double calculateSubtotal(List<CartItem> cart) {

        double subtotal = 0;

        for (CartItem item : cart) {
            subtotal += item.getSubtotal();
        }

        return subtotal;
    }

    private double calculateDiscount(HttpSession session, double subtotal) {

        String discountType = (String) session.getAttribute("discountType");
        Object value = session.getAttribute("discountValue");

        if (discountType == null || !(value instanceof Number)) {
            return 0;
        }

        double discountValue = ((Number) value).doubleValue();

        if (discountType.equals("PERCENT")) {
            return subtotal * discountValue / 100;
        }

        if (discountType.equals("FIXED")) {
            return Math.min(discountValue, subtotal);
        }

        return 0;
    }
}
