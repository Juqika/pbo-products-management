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

    /**
     * @return the idStoreProduct
     */
    public int getIdStoreProduct() {
        return idStoreProduct;
    }

    /**
     * @param idStoreProduct the idStoreProduct to set
     */
    public void setIdStoreProduct(int idStoreProduct) {
        this.idStoreProduct = idStoreProduct;
    }

    /**
     * @return the idStore
     */
    public int getIdStore() {
        return idStore;
    }

    /**
     * @param idStore the idStore to set
     */
    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    /**
     * @return the idProduct
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
    
}
