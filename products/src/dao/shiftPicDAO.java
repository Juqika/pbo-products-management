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
        String sql = "INSERT INTO ShiftPic (name, start, end, note, is_deleted) VALUES (?, ?, ?, ?, false)";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, s.getName());
            st.setTimestamp(2, Timestamp.valueOf(s.getStart_check_time()));
            st.setTimestamp(3, s.getEnd_check_time() != null ? Timestamp.valueOf(s.getEnd_check_time()) : null);
            st.setString(4, s.getNote());
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("error: " + e.getMessage());
        }
    }
    @Override
    public void update(shift_pic s){
        String sql = "UPDATE ShiftPic SET name = ?, start = ?, end = ?, note = ? WHERE id_shift = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setString(1, s.getName());
            st.setTimestamp(2, Timestamp.valueOf(s.getStart_check_time()));
            st.setTimestamp(3, s.getEnd_check_time() != null ? Timestamp.valueOf(s.getEnd_check_time()) : null);
            st.setString(4, s.getNote());
            st.setInt(5, s.getIdShift());
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("error: "+ e.getMessage());
        }
    }
    @Override
    public void delete(int idShift){
        String sql = "UPDATE ShiftPic SET is_deleted = true WHERE id_shift = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, idShift);
            st.executeUpdate();
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
        }
    }
    @Override
    public List<shift_pic> getAll(){
        List<shift_pic> list = new ArrayList<>();
        String sql = "SELECT * FROM ShiftPic WHERE is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                shift_pic s = new shift_pic();
                s.setIdShift(rs.getInt("id_shift"));
                s.setName(rs.getString("name"));
                Timestamp start = rs.getTimestamp("start");
                if (start != null) {
                    s.setStart_check_time(start.toLocalDateTime());
                }
                Timestamp end = rs.getTimestamp("end");
                if (end != null) {
                    s.setEnd_check_time(end.toLocalDateTime());
                }
                s.setNote(rs.getString("note"));
                s.setIs_deleted(false);
                list.add(s);
            }
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
        }
        return list;
    }
    @Override
    public shift_pic getById(int idShift){
        shift_pic s = null;
        String sql = "SELECT * FROM ShiftPic WHERE id_shift = ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, idShift);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                s = new shift_pic();
                s.setIdShift(rs.getInt("id_shift"));
                s.setName(rs.getString("name"));
                Timestamp start = rs.getTimestamp("start");
                if (start!= null) {
                    s.setStart_check_time(start.toLocalDateTime());
                }
                Timestamp end = rs.getTimestamp("end");
                if (end!= null) {
                    s.setEnd_check_time(end.toLocalDateTime());
                }
                s.setNote(rs.getString("note"));
                s.setIs_deleted(false);
            }
        }catch(SQLException e){
            System.out.println("error: " + e.getMessage());
        }
        return s;
    }
}
