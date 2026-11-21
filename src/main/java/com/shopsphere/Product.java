
package com.shopsphere.shopsphere;

public class Product {

    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product(int id, String name, String description,
                  double price, String image, String category) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }
}
