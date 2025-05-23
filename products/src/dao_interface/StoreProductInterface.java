/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao_interface;

import java.util.List;
import model.StoreProduct;
/**
 *
 * @author indrovert
 */
public interface StoreProductInterface {
    List<StoreProduct> getProduct(int idStore);
    boolean addProduct(int idStore, int idProduct);
    boolean deleteProduct(int idStore, int idProduct);
    boolean getById(int idStore, int idProduct);
}
