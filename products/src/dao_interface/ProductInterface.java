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
    List<Product> getAll();
    List<Product> getSearchName(String name);
    Product getById(int id);
}