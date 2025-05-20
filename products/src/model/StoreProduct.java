/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author windows 10
 */
public class StoreProduct {
    private int id_store_products, id_store, id_products;
    private boolean is_delete;
    
    public StoreProduct(){}
    public StoreProduct(int id_store_products, int id_store, int id_products, boolean is_delete){
        this.id_products = id_products;
        this.id_store_products = id_store_products;
        this.id_store = id_store;
        this.is_delete = is_delete;
    }

    public int getId_store_products() {
        return id_store_products;
    }

    public void setId_store_products(int id_store_products) {
        this.id_store_products = id_store_products;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public int getId_products() {
        return id_products;
    }

    public void setId_products(int id_products) {
        this.id_products = id_products;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
    
}
