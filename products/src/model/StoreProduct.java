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
    private int idStoreProduct;
    private int idStore;
    private int idProduct;
    
    public StoreProduct(){}
    public StoreProduct(int idStoreProduct, int idStore, int idProduct){
        this.idProduct = idProduct;
        this.idStoreProduct = idStoreProduct;
        this.idStore = idStore;
    }

    public int getIdStoreProduct() {
        return idStoreProduct;
    }

    public void setIdStoreProduct(int idStoreProduct) {
        this.idStoreProduct = idStoreProduct;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
}
