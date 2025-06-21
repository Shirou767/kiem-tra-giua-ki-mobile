package com.example.myfood_nnbinh.model;

public class Restaurant_NNBinh {
    private int id;
    private String name;
    private String address;
    private String image;

    public Restaurant_NNBinh(int id, String name, String address, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getImage() { return image; }
}
