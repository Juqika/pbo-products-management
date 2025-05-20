/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
/**
 *
 * @author windows 10
 */
public class Booking {
    private int idBooking;
    private Product product; // foreign key object
    private Vendor vendor;   // foreign key object
    private LocalDateTime requestDate;
    private LocalDateTime arriveDate;
    private int status;
    private String comment;
    private boolean isDeleted;
    
    // Constructor
    public Booking(int idBooking, Product product, Vendor vendor, LocalDateTime requestDate, LocalDateTime arriveDate, int status, String comment, boolean isDeleted) {
        this.idBooking = idBooking;
        this.product = product;
        this.vendor = vendor;
        this.requestDate = requestDate;
        this.arriveDate = arriveDate;
        this.status = status;
        this.comment = comment;
        this.isDeleted = isDeleted;
    }

    public Booking() {
        
    }

    /**
     * @return the idBooking
     */
    public int getIdBooking() {
        return idBooking;
    }

    /**
     * @param idBooking the idBooking to set
     */
    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the vendor
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the requestDate
     */
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the arriveDate
     */
    public LocalDateTime getArriveDate() {
        return arriveDate;
    }

    /**
     * @param arriveDate the arriveDate to set
     */
    public void setArriveDate(LocalDateTime arriveDate) {
        this.arriveDate = arriveDate;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
}
