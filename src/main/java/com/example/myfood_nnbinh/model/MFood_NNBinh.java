
package com.example.myfood_nnbinh.model;

public class MFood_NNBinh {
    private int id;
    private String name;
    private String description;
    private double price;
    private String size;
    private int restaurantId;
    private String image;

    public MFood_NNBinh(int id, String name, String description, double price, String size, int restaurantId, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.restaurantId = restaurantId;
        this.image = image;
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

    public String getSize() {
        return size;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getImage() {
        return image;
    }
}
