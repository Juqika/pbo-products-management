/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao_interface;

import java.util.List;
import model.Vendor;

/**
 *
 * @author indrovert
 */
public interface VendorInterface {
    void insert(Vendor v);
    void update(Vendor v);
    void delete(int id);
    List<Vendor> getAll();
    Vendor getById(int id); // << Tambahkan ini
}