/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao_interface;

import java.util.List;
import model.Store;

/**
 *
 * @author indrovert
 */

public interface StoreInterface {
    void insert(Store s);
    void update(Store s);
    void delete(int id);
    List<Store> getAll();
    Store getById(int id); 
}
