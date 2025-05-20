/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author windows 10
 */
public class Vendor {
    private int idVendor;
    private String name;
    private String address;
    private boolean isDeleted;
    
    public Vendor(){}
    public Vendor(int idVendor, String name, String address){
        this.idVendor = idVendor;
        this.name = name;
        this.address = address;
        this.isDeleted = isDeleted;
    }

    /**
     * @return the idVendor
     */
    public int getIdVendor() {
        return idVendor;
    }

    /**
     * @param idVendor the idVendor to set
     */
    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
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
    
    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
