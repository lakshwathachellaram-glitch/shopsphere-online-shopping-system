
package com.shopsphere.shopsphere;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CouponController {

    @PostMapping("/apply-coupon")
    public String applyCoupon(
            @RequestParam String couponCode,
            HttpSession session) {

        couponCode = couponCode.toUpperCase().trim();

        if (couponCode.equals("SHOP10")) {
            session.setAttribute("couponCode", "SHOP10");
            session.setAttribute("discountType", "PERCENT");
            session.setAttribute("discountValue", 10.0);
            session.setAttribute("couponMessage", "10% discount applied!");
        }

        else if (couponCode.equals("SHOP20")) {
            session.setAttribute("couponCode", "SHOP20");
            session.setAttribute("discountType", "PERCENT");
            session.setAttribute("discountValue", 20.0);
            session.setAttribute("couponMessage", "20% discount applied!");
        }

        else if (couponCode.equals("WELCOME50")) {
            session.setAttribute("couponCode", "WELCOME50");
            session.setAttribute("discountType", "FIXED");
            session.setAttribute("discountValue", 50.0);
            session.setAttribute("couponMessage", "₹50 discount applied!");
        }

        else {
            session.setAttribute("couponMessage", "Invalid coupon code!");
            session.removeAttribute("couponCode");
            session.removeAttribute("discountType");
            session.removeAttribute("discountValue");
        }

        return "redirect:/checkout";
    }

    @PostMapping("/remove-coupon")
    public String removeCoupon(HttpSession session) {

        session.removeAttribute("couponCode");
        session.removeAttribute("discountType");
        session.removeAttribute("discountValue");
        session.removeAttribute("couponMessage");

        return "redirect:/checkout";
    }
}
