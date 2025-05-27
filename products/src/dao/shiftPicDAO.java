/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.connect;
import model.shift_pic;
import dao_interface.shiftPicInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author windows 10
 */
public class shiftPicDAO implements shiftPicInterface {
    Connection conn;
    public shiftPicDAO(){
        conn = connect.getcC();
    }
    
    @Override
    public void insert(shift_pic s){
        String sql = "INSERT INTO STORES (name, address ,is_deleted) VALUES (?, ?, false)";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, s.getIdShift());
            st.setString(2, s.getName());
            st.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("error" + e.getMessage());
        }
    }
    @Override
    public void update(Store s){
        String sql = "UPDATE Stores set name = ?, address = ?, WHERE id_store = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, s.getName());
            st.setString(2, s.getAddress());
            st.setInt(3, s.getId_store());
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("error"+ e.getMessage());
        }
    }
    @Override
    public void delete(int id){
        String sql = "UPDATE Stores set is_deleted = true WHERE id_store = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("error"+e.getMessage());
        }
    }
    @Override
    public List<Store> getAll(){
        List<Store> list = new ArrayList<>();
        String sql = "SELECT * FROM stores WHERE is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Store s = new Store();
                s.setId_store(rs.getInt("id_store"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setIs_deleted(false);
                list.add(s);
            }
        }catch(SQLException e){
            System.out.println("error"+e.getMessage());
        }
        return list;
    }
    @Override
    public Store getById(int id){
        Store s = null;
        String sql = "SELECT * FROM stores WHERE id_store = ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                s = new Store();
                s.setId_store(rs.getInt("id_store"));
                s.setName(rs.getString("name"));
                s.setAddress(rs.getString("address"));
                s.setIs_deleted(false);
            }
        }catch(SQLException e){
            System.out.println("error" + e.getMessage());
        }
        return s;
    }
}
