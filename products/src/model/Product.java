/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author windows 10
 */
public class Product {
    private int id_product, id_vendor, qty;
    private String name, type;
    private boolean id_deleted;
    
    public Product(){}
    public Product(int id_product, int id_vendor, String name, String type, int qty, boolean id_delete){
        this.id_product = id_product;
        this.id_vendor = id_vendor;
        this.name = name;
        this.qty = qty;
        this.type = type;
        this.id_deleted = id_delete;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_vendor() {
        return id_vendor;
    }

    public void setId_vendor(int id_vendor) {
        this.id_vendor = id_vendor;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isId_deleted() {
        return id_deleted;
    }

    public void setId_deleted(boolean id_deleted) {
        this.id_deleted = id_deleted;
    }
    
}
