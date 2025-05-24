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
        String sql = "INSERT INTO product (id_vendor, name, type, qty, is_deleted) VALUES (?, ?, ?, ?, false)";
        try(PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, p.getIdVendor());
            st.setString(2, p.getName());
            st.setString(3, p.getType());
            st.setInt(4, p.getQty());
            st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Eror inserting product : " +e.getMessage());
        }
    }
    
    @Override
    public void update(Product p) {
        String sql = "UPDATE product SET id_vendor = ?, name = ?, type = ?, qty = ? WHERE id_product = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, p.getIdVendor());
            st.setString(2, p.getName());
            st.setString(3, p.getType());
            st.setInt(4, p.getQty());
            st.setInt(5, p.getIdProduct());
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error updating product: " + e.getMessage());
        }
    }
    
    @Override
    public void delete(int id) {
        String sql = "UPDATE product SET is_deleted = true WHERE id_product = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error deleting product: " + e.getMessage());
        }
    }
    
    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setIdVendor(rs.getInt("id_vendor"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));
                p.setQty(rs.getInt("qty"));
                p.setIsDeleted(false);
                list.add(p);
            }
        }catch(SQLException e){
            System.out.println("Error getting all products: " + e.getMessage());
        }
        return list;
    }
    
     @Override
    public List<Product> getSearchName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name LIKE ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setIdVendor(rs.getInt("id_vendor"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));
                p.setQty(rs.getInt("qty"));
                p.setIsDeleted(false);
                list.add(p);
            }
        }catch(SQLException e){
            System.out.println("Error searching products by name: " + e.getMessage());
        }
        return list;
    }
    
        @Override
    public Product getById(int id) {
        Product p = null;
        String sql = "SELECT * FROM product WHERE id_product = ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                p = new Product();
                p.setIdProduct(rs.getInt("id_product"));
                p.setIdVendor(rs.getInt("id_vendor"));
                p.setName(rs.getString("name"));
                p.setType(rs.getString("type"));
                p.setQty(rs.getInt("qty"));
                p.setIsDeleted(false);
            }
        }catch(SQLException e){
            System.out.println("Error getting product by ID: " + e.getMessage());
        }
        return p;
    }
}
