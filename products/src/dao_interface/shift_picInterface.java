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
public interface shift_picInterface {
    List<shift_pic> getProduct(int idStore);
    boolean addProduct(int idStore, int idProduct);
    boolean deleteProduct(int idStore, int idProduct);
    boolean getById(int idStore, int idProduct);
}
