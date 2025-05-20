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
    private int id_vendor;
    private String name, address;
    private boolean is_delete;
    
    public Vendor(){}
    public Vendor(int id_vendor, String name, String address, boolean is_delete){
        this.id_vendor = id_vendor;
        this.name = name;
        this.address = address;
        this.is_delete = is_delete;
    }

    public int getId_vendor() {
        return id_vendor;
    }

    public void setId_vendor(int id_vendor) {
        this.id_vendor = id_vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }
    
    public String toString(){
        // Berguna untuk debugging dan mungkin untuk tampilan di ComboBox
        return name; // or idVendor
    }
    
}
