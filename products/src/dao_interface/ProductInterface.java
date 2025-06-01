/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao_interface;

import java.util.List;
import model.Product;

/**
 *
 * @author indrovert
 */
public interface ProductInterface {
    void insert(Product p);
    void update(Product p);
    void delete(int id);
    int getNextProductId();
    List<Product> getAll();
    List<Product> searchByIdOrName(int id, String name);
}
