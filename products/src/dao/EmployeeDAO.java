/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.connect;
import dao_interface.EmployeeInterface;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author user
 */
public class EmployeeDAO implements EmployeeInterface {
    Connection conn;
    
    public EmployeeDAO() {
        conn = connect.getcC();
    }
    
    @Override
    public void insert(Employee e) {
        String sql = "INSERT INTO employees (name, gender, is_deleted) VALUES (?, ?, false)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, e.getName());
            st.setString(2, e.getGender());
            st.executeUpdate();
            
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setId_employee(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting employee: " + ex.getMessage());
            throw new RuntimeException("Failed to insert employee", ex);
        }
    }

    @Override
    public void update(Employee e) {
        String sql = "UPDATE employees SET name = ?, gender = ? WHERE id_employee = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, e.getName());
            st.setString(2, e.getGender());
            st.setInt(3, e.getId_employee());
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new RuntimeException("No employee found with ID: " + e.getId_employee());
            }
        } catch (SQLException ex) {
            System.out.println("Error updating employee: " + ex.getMessage());
            throw new RuntimeException("Failed to update employee", ex);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "UPDATE employees SET is_deleted = true WHERE id_employee = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new RuntimeException("No employee found with ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting employee: " + ex.getMessage());
            throw new RuntimeException("Failed to delete employee", ex);
        }
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE is_deleted = false";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId_employee(rs.getInt("id_employee"));
                emp.setName(rs.getString("name"));
                emp.setGender(rs.getString("gender"));
                emp.setIs_deleted(rs.getBoolean("is_deleted"));
                list.add(emp);
            }
        } catch (SQLException ex) {
            System.out.println("Error getting employees: " + ex.getMessage());
            throw new RuntimeException("Failed to get employees", ex);
        }
        return list;
    }

    
    @Override
    public List<Employee> searchByIdOrName(Integer id, String name) {
        List<Employee> list = new ArrayList<>();

        String sql = "SELECT * FROM employees WHERE is_deleted = false"
                   + (id   != null && id   > 0           ? " AND id_employee = ?" : "")
                   + (name != null && !name.trim().isEmpty() ? " AND name LIKE ?" : "");
        if ((id == null || id <= 0) && (name == null || name.trim().isEmpty())) {
            return list;
        }

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            int idx = 1;
            if (id != null && id > 0) {
                st.setInt(idx++, id);
            }
            if (name != null && !name.trim().isEmpty()) {
                st.setString(idx++, "%" + name.trim() + "%");
            }

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Employee emp = new Employee();
                    emp.setId_employee(   rs.getInt("id_employee"));
                    emp.setName(          rs.getString("name"));
                    emp.setGender(        rs.getString("gender"));
                    emp.setIs_deleted(    rs.getBoolean("is_deleted"));
                    list.add(emp);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching employees: " + e.getMessage());
            throw new RuntimeException("Failed to search employees", e);
        }
        return list;
    }


    @Override
        public int getNextEmployeed() {
        String sql = "SELECT MAX(id_employee) AS max_id FROM employees";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            System.out.println("Error getting next employee ID: " + e.getMessage());
            throw new RuntimeException("Failed to get next employee ID", e);
        }
        return 1; // Jika tidak ada data sama sekali
    }
}
