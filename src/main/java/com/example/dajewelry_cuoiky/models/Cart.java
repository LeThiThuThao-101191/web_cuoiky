//package com.example.dajewelry_cuoiky.models;
//
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Entity
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CartItem> items = new ArrayList<>();
//
//    private double total;
//
//    // Phương thức cập nhật tổng giá trị
//    public void updateTotal() {
//        this.total = items.stream()
//                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
//                .sum();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public List<CartItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<CartItem> items) {
//        this.items = items;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
//}
