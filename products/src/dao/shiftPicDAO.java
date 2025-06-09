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
 * Kelas DAO (Data Access Object) untuk entitas shift_pic
 * Menangani operasi database untuk tabel ShiftPic
 */
public class shiftPicDAO implements shiftPicInterface {
    Connection conn;
    
    /**
     * Constructor untuk shiftPicDAO
     * Menginisialisasi koneksi database
     */
    public shiftPicDAO(){
        conn = connect.getcC();
    }
    
    /**
     * Menyimpan shift baru ke database
     * 
     * @param s Objek shift_pic yang akan disimpan
     */
    @Override
    public void insert(shift_pic s) {
        String sql = "INSERT INTO ShiftPic (id_employee, start, end, note, is_deleted) VALUES (?, ?, ?, ?, false)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Mengisi parameter query
            st.setInt(1, s.getIdEmployee());
            st.setTimestamp(2, s.getStart_check_time() != null ? Timestamp.valueOf(s.getStart_check_time()) : null);
            st.setTimestamp(3, s.getEnd_check_time() != null ? Timestamp.valueOf(s.getEnd_check_time()) : null);
            st.setString(4, s.getNote());
            
            // Mengeksekusi query
            st.executeUpdate();
            
            // Mendapatkan ID yang dihasilkan oleh database
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
    
    /**
     * Memperbarui shift yang ada di database
     * 
     * @param s Objek shift_pic dengan data yang diperbarui
     */
    @Override
    public void update(shift_pic s) {
        String sql = "UPDATE ShiftPic SET id_employee = ?, start = ?, end = ?, note = ? WHERE id_shift = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            // Mengisi parameter query  Mengonversi LocalDateTime ke Timestamp untuk database
            st.setInt(1, s.getIdEmployee());
            st.setTimestamp(2, s.getStart_check_time() != null ? Timestamp.valueOf(s.getStart_check_time()) : null);
            st.setTimestamp(3, s.getEnd_check_time() != null ? Timestamp.valueOf(s.getEnd_check_time()) : null);
            st.setString(4, s.getNote());
            st.setInt(5, s.getIdShift());
            
            // Mengeksekusi query
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating shift: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Menghapus shift dari database (soft delete)
     * 
     * @param idShift ID shift yang akan dihapus
     */
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
    
    /**
     * Mendapatkan semua shift yang tidak dihapus
     * 
     * @return List berisi objek shift_pic
     */
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
                // Membuat objek shift_pic baru untuk setiap baris hasil query
                shift_pic s = new shift_pic();
                s.setIdShift(rs.getInt("id_shift"));
                s.setIdEmployee(rs.getInt("id_employee"));
                s.setName(rs.getString("name"));
                
                // Mengonversi Timestamp dari database ke LocalDateTime
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

    /**
     * Mendapatkan daftar semua karyawan yang tidak dihapus
     * 
     * @return List berisi objek Employee
     */
    public List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        // Hanya ambil id_employee dan name
        String sql = "SELECT id_employee, name FROM employees WHERE is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                // Membuat objek Employee baru untuk setiap baris hasil query
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

    /**
     * Mendapatkan shift berdasarkan ID
     * 
     * @param idShift ID shift yang dicari
     * @return Objek shift_pic, atau null jika tidak ditemukan
     */
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
                // Membuat objek shift_pic baru jika ditemukan
                s = new shift_pic();
                s.setIdShift(rs.getInt("id_shift"));
                s.setIdEmployee(rs.getInt("id_employee"));
                s.setName(rs.getString("name"));
                
                // Mengonversi Timestamp dari database ke LocalDateTime
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
