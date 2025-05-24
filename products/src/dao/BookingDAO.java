/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.connect;
import dao_interface.BookingInterface;
import model.Booking;
import model.Product;
import model.Vendor;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author windows 10
 */
public class BookingDAO implements BookingInterface {
    Connection conn;
    
    public BookingDAO() {
         conn = connect.getcC();
    }

     @Override
    public void insert(Booking b) {
        String sql = "INSERT INTO bookings (product_id, vendor_id, request_date, arrive_date, status, comment) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, b.getProduct().getIdProduct());
            st.setInt(2, b.getVendor().getIdVendor());
            st.setObject(3, b.getRequestDate());
            st.setObject(4, b.getArriveDate());
            st.setInt(5, b.getStatus());
            st.setString(6, b.getComment());
            st.executeUpdate();
            
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    b.setIdBooking(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            System.out.println("Error inserting booking: " + e.getMessage());
        }
    }
    
        @Override
    public void update(Booking b) {
        String sql = "UPDATE bookings SET product_id = ?, vendor_id = ?, request_date = ?, arrive_date = ?, status = ?, comment = ? WHERE id_booking = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, b.getProduct().getIdProduct());
            st.setInt(2, b.getVendor().getIdVendor());
            st.setObject(3, b.getRequestDate());
            st.setObject(4, b.getArriveDate());
            st.setInt(5, b.getStatus());
            st.setString(6, b.getComment());
            st.setInt(7, b.getIdBooking());
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error updating booking: " + e.getMessage());
        }
    }
    
        @Override
    public void delete(int id) {
        String sql = "UPDATE bookings SET is_deleted = true WHERE id_booking = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error deleting booking: " + e.getMessage());
        }
    }
    
    @Override
    public void restore(int id) {
        String sql = "UPDATE bookings SET is_deleted = false WHERE id_booking = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error restoring booking: " + e.getMessage());
        }
    }
    
        @Override
    public List<Booking> getAll() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Booking b = new Booking();
                b.setIdBooking(rs.getInt("id_booking"));
                b.setProduct(new ProductDAO().getById(rs.getInt("product_id")));
                b.setVendor(new VendorDAO().getById(rs.getInt("vendor_id")));
                b.setRequestDate(rs.getObject("request_date", LocalDateTime.class));
                b.setArriveDate(rs.getObject("arrive_date", LocalDateTime.class));
                b.setStatus(rs.getInt("status"));
                b.setComment(rs.getString("comment"));
                b.setIsDeleted(false);
                list.add(b);
            }
        } catch(SQLException e) {
            System.out.println("Error getting all bookings: " + e.getMessage());
        }
        return list;
    }
    
        @Override
    public Booking getById(int id) {
        String sql = "SELECT * FROM bookings WHERE id_booking = ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Booking b = new Booking();
                b.setIdBooking(rs.getInt("id_booking"));
                b.setProduct(new ProductDAO().getById(rs.getInt("product_id")));
                b.setVendor(new VendorDAO().getById(rs.getInt("vendor_id")));
                b.setRequestDate(rs.getObject("request_date", LocalDateTime.class));
                b.setArriveDate(rs.getObject("arrive_date", LocalDateTime.class));
                b.setStatus(rs.getInt("status"));
                b.setComment(rs.getString("comment"));
                b.setIsDeleted(false);
                return b;
            }
        } catch(SQLException e) {
            System.out.println("Error getting booking by ID: " + e.getMessage());
        }
        return null;
    }
    
        @Override
    public List<Booking> getByVendor(int vendorId) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE vendor_id = ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, vendorId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Booking b = new Booking();
                b.setIdBooking(rs.getInt("id_booking"));
                b.setProduct(new ProductDAO().getById(rs.getInt("product_id")));
                b.setVendor(new VendorDAO().getById(rs.getInt("vendor_id")));
                b.setRequestDate(rs.getObject("request_date", LocalDateTime.class));
                b.setArriveDate(rs.getObject("arrive_date", LocalDateTime.class));
                b.setStatus(rs.getInt("status"));
                b.setComment(rs.getString("comment"));
                b.setIsDeleted(false);
                list.add(b);
            }
        } catch(SQLException e) {
            System.out.println("Error getting bookings by vendor: " + e.getMessage());
        }
        return list;
    }
    
        @Override
    public List<Booking> getByStatus(int status) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE status = ? AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, status);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Booking b = new Booking();
                b.setIdBooking(rs.getInt("id_booking"));
                b.setProduct(new ProductDAO().getById(rs.getInt("product_id")));
                b.setVendor(new VendorDAO().getById(rs.getInt("vendor_id")));
                b.setRequestDate(rs.getObject("request_date", LocalDateTime.class));
                b.setArriveDate(rs.getObject("arrive_date", LocalDateTime.class));
                b.setStatus(rs.getInt("status"));
                b.setComment(rs.getString("comment"));
                b.setIsDeleted(false);
                list.add(b);
            }
        } catch(SQLException e) {
            System.out.println("Error getting bookings by status: " + e.getMessage());
        }
        return list;
    }
    
        @Override
    public List<Booking> getByRequestDate(LocalDateTime date) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE DATE(request_date) = DATE(?) AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setObject(1, date);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(mapResultSetToBooking(rs));
            }
        } catch(SQLException e) {
            System.out.println("Error getting bookings by request date: " + e.getMessage());
        }
        return list;
    }
    
        @Override
    public List<Booking> getByArrivalDate(LocalDateTime date) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE DATE(arrive_date) = DATE(?) AND is_deleted = false";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setObject(1, date);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
               list.add(mapResultSetToBooking(rs));
            }
        } catch(SQLException e) {
            System.out.println("Error getting bookings by arrival date: " + e.getMessage());
        }
        return list;
    }

    
        @Override
    public void updateStatus(int bookingId, int newStatus) {
        String sql = "UPDATE bookings SET status = ? WHERE id_booking = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1, newStatus);
            st.setInt(2, bookingId);
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error updating booking status: " + e.getMessage());
        }
    }
    
        @Override
    public void updateArrivalDate(int bookingId, LocalDateTime newArrivalDate) {
        String sql = "UPDATE bookings SET arrive_date = ? WHERE id_booking = ?";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setObject(1, newArrivalDate);
            st.setInt(2, bookingId);
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error updating arrival date: " + e.getMessage());
        }
    }
    
    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
    Booking b = new Booking();
    b.setIdBooking(rs.getInt("id_booking"));
    
    // Handle potential null product
    Product product = new ProductDAO().getById(rs.getInt("product_id"));
    if (product == null) {
        product = new Product(); // Create empty product or handle differently
        product.setIdProduct(rs.getInt("product_id"));
    }
    b.setProduct(product);
    
    // Handle potential null vendor
    Vendor vendor = new VendorDAO().getById(rs.getInt("vendor_id"));
    if (vendor == null) {
        vendor = new Vendor(); // Create empty vendor or handle differently
        vendor.setIdVendor(rs.getInt("vendor_id"));
    }
    b.setVendor(vendor);
    
    b.setRequestDate(rs.getObject("request_date", LocalDateTime.class));
    b.setArriveDate(rs.getObject("arrive_date", LocalDateTime.class));
    b.setStatus(rs.getInt("status"));
    b.setComment(rs.getString("comment"));
    b.setIsDeleted(rs.getBoolean("is_deleted"));
    return b;
  }
}
