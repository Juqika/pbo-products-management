/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import model.Booking;
/**
 *
 * @author windows 10
 */
public class BookingDAO {
    void insert (Booking b);
    void update (Booking b);
    void delete (int id);
    List<Booking> getAll();
    Booking getByid (int id);
}
