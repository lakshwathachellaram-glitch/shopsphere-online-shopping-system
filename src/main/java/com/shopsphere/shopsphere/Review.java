
package com.shopsphere.shopsphere;

public class Review {

    private int productId;
    private String customerName;
    private int rating;
    private String comment;

    public Review(int productId, String customerName, int rating, String comment) {
        this.productId = productId;
        this.customerName = customerName;
        this.rating = rating;
        this.comment = comment;
    }

    public int getProductId() {
        return productId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
