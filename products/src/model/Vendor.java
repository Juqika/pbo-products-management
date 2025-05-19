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
    
    public Vendor(){}
    public Vendor(int idVentor, String name, String address){
        this.idVendor = idVendor;
        this.name = name;
        this.address = address;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
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
    
    public String toString(){
        // Berguna untuk debugging dan mungkin untuk tampilan di ComboBox
        return name; // or idVendor
    }
    
}
