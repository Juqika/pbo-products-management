/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.connect;
import model.Employee;
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
    public void insert(shift_pic s) {
        String sql = "INSERT INTO ShiftPic (id_employee, start, end, note, is_deleted) VALUES (?, ?, ?, ?, false)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, s.getIdEmployee());
            st.setTimestamp(2, s.getStart_check_time() != null ? Timestamp.valueOf(s.getStart_check_time()) : null);
            st.setTimestamp(3, s.getEnd_check_time() != null ? Timestamp.valueOf(s.getEnd_check_time()) : null);
            st.setString(4, s.getNote());
            
            st.executeUpdate();
            
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setIdShift(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting shift: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void update(shift_pic s) {
        String sql = "UPDATE ShiftPic SET id_employee = ?, start = ?, end = ?, note = ? WHERE id_shift = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, s.getIdEmployee());
            st.setTimestamp(2, s.getStart_check_time() != null ? Timestamp.valueOf(s.getStart_check_time()) : null);
            st.setTimestamp(3, s.getEnd_check_time() != null ? Timestamp.valueOf(s.getEnd_check_time()) : null);
            st.setString(4, s.getNote());
            st.setInt(5, s.getIdShift());
            
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating shift: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int idShift){
        String sql = "UPDATE ShiftPic SET is_deleted = true WHERE id_shift = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, idShift);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting shift: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public List<shift_pic> getAll(){
        List<shift_pic> list = new ArrayList<>();
        String sql = "SELECT s.id_shift, s.id_employee, e.name, s.start, s.end, s.note, s.is_deleted " +
                     "FROM ShiftPic s " +
                     "JOIN employees e ON s.id_employee = e.id_employee " +
                     "WHERE s.is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                shift_pic s = new shift_pic();
                s.setIdShift(rs.getInt("id_shift"));
                s.setIdEmployee(rs.getInt("id_employee"));
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
                s.setIs_deleted(rs.getBoolean("is_deleted"));
                list.add(s);
            }
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        // Hanya ambil id_employee dan name
        String sql = "SELECT id_employee, name FROM employees WHERE is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId_employee(rs.getInt("id_employee"));
                emp.setName(rs.getString("name"));
                // Tidak perlu mengambil gender dan is_deleted
                list.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println("Error getting employees: " + ex.getMessage());
            throw new RuntimeException("Failed to get employees", ex);
        }
        return list;
    }

    @Override
    public shift_pic getById(int idShift){
        shift_pic s = null;
        String sql = "SELECT s.id_shift, s.id_employee, e.name, s.start, s.end, s.note, s.is_deleted " +
                     "FROM ShiftPic s " +
                     "JOIN employees e ON s.id_employee = e.id_employee " +
                     "WHERE s.id_shift = ? AND s.is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, idShift);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                s = new shift_pic();
                s.setIdShift(rs.getInt("id_shift"));
                s.setIdEmployee(rs.getInt("id_employee"));
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
                s.setIs_deleted(rs.getBoolean("is_deleted"));
            }
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
            e.printStackTrace();
        }
        return s;
    }
}
