/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author windows 10
 */
public class Store {
    private int idStore;
    private String name;
    private String address;
    
    public Store(){}
    
    public Store(int idStore, String name, String address){
        this.address = address;
        this.idStore = idStore;
        this.name = name;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
