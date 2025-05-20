/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.connect;
import dao_interface.VendorInterface;
import model.Vendor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author windows 10
 */
public class VendorDAO implements VendorInterface {

    Connection conn;

    public VendorDAO() {
        conn = connect.getcC();
    }

    @Override
    public void insert(Vendor v) {
        String sql = "INSERT INTO vendors (name, address, is_deleted) VALUES (?, ?, false)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, v.getName());
            st.setString(2, v.getAddress());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }

    @Override
    public void update(Vendor v) {
        String sql = "UPDATE vendors SET name = ?, address = ? WHERE id_vendor = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, v.getName());
            st.setString(2, v.getAddress());
            st.setInt(3, v.getIdVendor());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "UPDATE vendors SET is_deleted = true WHERE id_vendor = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete (Soft) Error: " + e.getMessage());
        }
    }

    @Override
    public List<Vendor> getAll() {
        List<Vendor> list = new ArrayList<>();
        String sql = "SELECT * FROM vendors WHERE is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Vendor v = new Vendor();
                v.setIdVendor(rs.getInt("id_vendor"));
                v.setName(rs.getString("name"));
                v.setAddress(rs.getString("address"));
                v.setDeleted(false);
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Get All Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Vendor getById(int id) {
        Vendor v = null;
        String sql = "SELECT * FROM vendors WHERE id_vendor = ? AND is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                v = new Vendor();
                v.setIdVendor(rs.getInt("id_vendor"));
                v.setName(rs.getString("name"));
                v.setAddress(rs.getString("address"));
                v.setDeleted(false);
            }
        } catch (SQLException e) {
            System.out.println("Get By ID Error: " + e.getMessage());
        }
        return v;
    }
}