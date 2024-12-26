//package com.example.dajewelry_cuoiky.models;
//
//import jakarta.persistence.*;
//
//@Entity
//public class CartItem {
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int id;
//    @ManyToOne
//    private PageProduct product;
//
//    private int quantity;
//
//    @ManyToOne
//    private Cart cart;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public PageProduct getProduct() {
//        return product;
//    }
//
//    public void setProduct(PageProduct product) {
//        this.product = product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//}
