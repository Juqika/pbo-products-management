/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao_interface;

/**
 *
 * @author indrovert
 */
public interface BookingInterface {
    void insert(Booking b);
    void update(Booking b);
    void delete(int id);  
    void restore(int id);     
    
    List<Booking> getAll();
    Booking getById(int id);
    
    List<Booking> getByVendor(int vendorId);
    List<Booking> getByStatus(int status);
    List<Booking> getByRequestDate(LocalDateTime date);
    List<Booking> getByArrivalDate(LocalDateTime date);
    
    void updateStatus(int bookingId, int newStatus);
    void updateArrivalDate(int bookingId, LocalDateTime newArrivalDate);
}
