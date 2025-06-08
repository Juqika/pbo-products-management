/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao_interface.shiftPicInterface;
import dao.shiftPicDAO;
import model.shift_pic;
import model.TableShiftPic;
import view.ShiftPicView;

import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

public class ShiftPicController {
    private ShiftPicView frame;
    private final shiftPicInterface shiftDAO;
    private List<shift_pic> sp;
    
    public ShiftPicController(ShiftPicView frame) {
        this.frame = frame;
        this.shiftDAO = new shiftPicDAO();
        sp = shiftDAO.getAll();
    }
    
    public void clear() {
        frame.gettfName().setText("");
        frame.gettfNote().setText("");
    }
    
    public void isiTable() {
        sp = shiftDAO.getAll();
        TableShiftPic tsp = new TableShiftPic(sp);
        frame.gettTable().setModel(tsp);
    }
    
    public void isiField(int row) {
        frame.gettfID().setText(String.valueOf(sp.get(row).getIdShift()));
        frame.gettfName().setText(sp.get(row).getName());
        frame.gettfNote().setText(sp.get(row).getNote());
        // Tidak perlu mengisi field start dan end karena biasanya diisi otomatis
    }
    
    public void insert() {
        if (!frame.gettfName().getText().trim().isEmpty()) {
            shift_pic s = new shift_pic();
            s.setName(frame.gettfName().getText());
            s.setStart_check_time(LocalDateTime.now());
            s.setEnd_check_time(null); // Atau LocalDateTime.now() jika database tidak mengizinkan null
            s.setNote(frame.gettfNote().getText());
            s.setIs_deleted(false);
            
            shiftDAO.insert(s);
            
            JOptionPane.showMessageDialog(null, "Successfully saved data.");
            isiTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(frame, "Name field cannot be empty.");
        }
    }

    // Metode overload untuk fleksibilitas
    public void insert(String name, LocalDateTime start, LocalDateTime end, String note) {
        // Isi field form dengan parameter
        frame.gettfName().setText(name);
        frame.gettfNote().setText(note);
        
        // Panggil metode utama
        insert();
    }
    
    public void update() {
        if (!frame.gettfID().getText().trim().isEmpty()) {
            shift_pic s = new shift_pic();
            s.setIdShift(Integer.parseInt(frame.gettfID().getText()));
            s.setName(frame.gettfName().getText());
            
            // Get the existing shift to preserve start time
            shift_pic existing = shiftDAO.getById(s.getIdShift());
            if (existing != null) {
                s.setStart_check_time(existing.getStart_check_time());
            } else {
                s.setStart_check_time(LocalDateTime.now());
            }
            
            s.setEnd_check_time(LocalDateTime.now());
            s.setNote(frame.gettfNote().getText());
            s.setIs_deleted(false);
            
            shiftDAO.update(s);
            
            JOptionPane.showMessageDialog(null, "Successfully updated data.");
            isiTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a shift first.");
        }
    }
    
    public void update(int id, String name, LocalDateTime start, LocalDateTime end, String note) {
        // Isi field form dengan parameter
        frame.gettfID().setText(String.valueOf(id));
        frame.gettfName().setText(name);
        frame.gettfNote().setText(note);
        
        // Panggil metode utama
        update();
    }
    
    public void delete() {
        if (!frame.gettfID().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.gettfID().getText());
            shiftDAO.delete(id);
            JOptionPane.showMessageDialog(null, "Successfully deleted data.");
            isiTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(frame, "Choose the data first.");
        }
    }
    
    public void setAutoID() {
        // Assuming you have a method to get next ID in your DAO
        // If not, you need to implement it similar to EmployeeDAO
        int nextId = 1; // Default value
        try {
            // Get the highest ID and add 1
            List<shift_pic> allShifts = shiftDAO.getAll();
            if (!allShifts.isEmpty()) {
                int maxId = 0;
                for (shift_pic s : allShifts) {
                    if (s.getIdShift() > maxId) {
                        maxId = s.getIdShift();
                    }
                }
                nextId = maxId + 1;
            }
        } catch (Exception e) {
            System.out.println("Error getting next ID: " + e.getMessage());
        }
        
        frame.gettfID().setText(String.valueOf(nextId));
    }
    
    public shift_pic getShiftById(int id) {
        return shiftDAO.getById(id);
    }
}
