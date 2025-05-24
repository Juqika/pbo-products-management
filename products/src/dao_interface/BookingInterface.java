/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao_interface;
import java.util.List;
import model.Booking;
/**
 *
 * @author indrovert
 */
public interface BookingInterface {
    void insert (Booking b);
    void update (Booking b);
    void delete (int id);
    List<Booking> getAll();
    Booking getByid (int id);
}
