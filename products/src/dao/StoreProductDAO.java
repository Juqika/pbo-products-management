/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import connection.connect;
import dao_interface.StoreProductInterface;
import dao_interface.VendorInterface;
import model.StoreProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author windows 10
 */
public class StoreProductDAO implements StoreProductInterface {
    Connection conn;
    
    public StoreProductDAO() {
        conn = connect.getcC();
    }
    
    @Override
    public  List<StoreProduct> getProduct (int idStore) {
        List<StoreProduct> productList = new ArrayList<>();
        String sql = "SELECT * FROM store_product WHERE id_store = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idStore);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                StoreProduct sp = new StoreProduct();
                sp.setIdStoreProduct(rs.getInt("id_store_product"));
                sp.setIdStore(rs.getInt("id_store"));
                sp.setIdProduct(rs.getInt("id_product"));
                productList.add(sp);
            }
            
        } catch (Exception e) {
            System.out.println("Error getting products by store ID: \" + e.getMessage()");
        }
        return productList;
    }
    @Override
    public boolean addProduct (int idStore, int idProduct) {
        String sql = "INSERT INTO store_product (id_store, id_product) VALUES (?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, idStore);
            st.setInt(2, idProduct);
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error adding product to store: " + e.getMessage());
            return false;
        }
    }
    
    
@Override
public boolean deleteProduct(int idStore, int idProduct) {
    String sql = "DELETE FROM store_products WHERE id_store = ? AND id_product = ?";
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, idStore);
        st.setInt(2, idProduct);
        int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error deleting product from store: " + e.getMessage());
        return false;
    }
}
    
 @Override
public boolean getById(int idStore, int idProduct) {
    String sql = "SELECT 1 FROM store_product WHERE id_store = ? AND id_product = ?";

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setInt(1, idStore);
        st.setInt(2, idProduct);
        ResultSet rs = st.executeQuery();
        return rs.next(); // true jika ada data
    } catch (Exception e) {
        System.out.println("Error checking product in store: " + e.getMessage());
        return false;
    }
  }
}
