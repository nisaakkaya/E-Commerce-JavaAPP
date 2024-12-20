package com.example.uygulama;
public class productItem {
    int image,id;
    String title, rank,price;
    private String productPrice;
    private int quantity;
    public productItem(int image, String price, String rank, String title, int id, int quantity) {
        this.image = image;
        this.price = price;
        this.rank = rank;
        this.title = title;
        this.id = id;
        this.quantity=quantity;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getImage() {
        return image;
    }
    public String getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }
    public String getRank() {
        return rank;
    }
    public String getTitle() {
        return title;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
