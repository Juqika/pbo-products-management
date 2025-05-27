/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao_interface;

import java.util.List;
import model.shift_pic;

/**
 *
 * @author indrovert
 */

public interface shiftPicInterface {
    void insert(shift_pic s);
    void update(shift_pic s);
    void delete(int idShift);
    List<shift_pic> getAll();
    shift_pic getById(int id); 
}
