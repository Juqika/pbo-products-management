/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import connection.connect;
import dao_interface.ProductInterface;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author windows 10
 */
public class ProductDAO implements ProductInterface {
       Connection conn;
    
    public ProductDAO() {
        conn = connect.getcC();
    }
    
    @Override
    public void insert(Product p) {
        String sql = "INSERT INTO products (name, type, qty, is_deleted) VALUES (?, ?, ?, false)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, p.getName());
            st.setString(2, p.getType());
            st.setInt(3, p.getQty());
            st.executeUpdate();
            
            // Get the auto-generated ID
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setIdProduct(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getMessage());
            throw new RuntimeException("Failed to insert product", e);
        }
    }
    
    @Override
    public void update(Product p) {
        String sql = "UPDATE products SET name = ?, type = ?, qty = ? WHERE id_product = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, p.getName());
            st.setString(2, p.getType());
            st.setInt(3, p.getQty());
            st.setInt(4, p.getIdProduct());
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new RuntimeException("No product found with ID: " + p.getIdProduct());
            }
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            throw new RuntimeException("Failed to update product", e);
        }
    }
    
    @Override
    public void delete(int id) {
        String sql = "UPDATE products SET is_deleted = true WHERE id_product = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new RuntimeException("No product found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            throw new RuntimeException("Failed to delete product", e);
        }
    }
    
    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));
                p.setQty(rs.getInt("qty"));
                p.setIs_deleted(rs.getBoolean("is_deleted"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all products: " + e.getMessage());
            throw new RuntimeException("Failed to get products", e);
        }
        return list;
    }
    
  @Override
public List<Product> searchByIdOrName(int id, String name) {
    List<Product> list = new ArrayList<>();
    // SQL dengan OR untuk mencari baik ID maupun nama
    String sql = "SELECT * FROM products WHERE (id_product = ? OR name LIKE ?) AND is_deleted = false";
    
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        // Set parameter ID (pencarian exact match)
        st.setInt(1, id);
        // Set parameter nama (pencarian partial dengan wildcard)
        st.setString(2, "%" + name + "%");
        
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));
                p.setQty(rs.getInt("qty"));
                p.setIs_deleted(rs.getBoolean("is_deleted"));
                list.add(p);
            }
        }
    } catch (SQLException e) {
        System.out.println("Error searching products by ID or name: " + e.getMessage());
        throw new RuntimeException("Failed to search products", e);
    }
    return list;
}
    
        @Override
        public int getNextProductId() {
        String sql = "SELECT MAX(id_product) AS max_id FROM products";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error getting next product ID: " + e.getMessage());
            throw new RuntimeException("Failed to get next product ID", e);
        }
        return 1; // Jika tidak ada data sama sekali
    }
}
