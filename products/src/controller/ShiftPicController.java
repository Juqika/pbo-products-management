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
import model.Employee;

import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

public class ShiftPicController {
    private ShiftPicView frame;
    private final shiftPicInterface shiftDAO;
    private List<shift_pic> sp;
    private TableShiftPic tableModel;
    
    public ShiftPicController(ShiftPicView frame) {
        this.frame = frame;
        this.shiftDAO = new shiftPicDAO();
        refreshData();
        
        // Inisialisasi model tabel
        tableModel = new TableShiftPic(sp);
        frame.gettTable().setModel(tableModel);
    }
    
    // Metode untuk refresh data dari database
    private void refreshData() {
        sp = shiftDAO.getAll();
    }
    
    public List<Employee> getEmployees() {
        return ((shiftPicDAO)shiftDAO).getEmployees();
    }
    
    public void clear() {
        frame.gettfNote().setText("");
    }
    
    public void isiTable() {
        refreshData();
        
        // Update model tabel dengan data baru
        if (tableModel == null) {
            tableModel = new TableShiftPic(sp);
            frame.gettTable().setModel(tableModel);
        } else {
            tableModel.updateData(sp);
        }
    }
    
    public void isiField(int row) {
        if (row >= 0 && row < sp.size()) {
            frame.gettfNote().setText(sp.get(row).getNote());
            
            // Set selected employee in ComboBox
            String employeeName = sp.get(row).getName();
            if (employeeName != null && !employeeName.isEmpty()) {
                frame.getCbName().setSelectedItem(employeeName);
            }
        }
    }
    
    public void insert() {
        String employeeName = (String) frame.getCbName().getSelectedItem();
        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an employee");
            return;
        }
        
        int employeeId = getEmployeeIdByName(employeeName);
        if (employeeId == -1) {
            JOptionPane.showMessageDialog(frame, "Employee not found");
            return;
        }
        
        shift_pic s = new shift_pic();
        s.setIdEmployee(employeeId);
        s.setStart_check_time(LocalDateTime.now());
        s.setNote(frame.gettfNote().getText());
        s.setIs_deleted(false);
        
        shiftDAO.insert(s);
        
        JOptionPane.showMessageDialog(null, "Successfully saved data.");
        isiTable();
        clear();
    }
    
    public void update(int idShift) {
        String employeeName = (String) frame.getCbName().getSelectedItem();
        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an employee");
            return;
        }
        
        int employeeId = getEmployeeIdByName(employeeName);
        if (employeeId == -1) {
            JOptionPane.showMessageDialog(frame, "Employee not found");
            return;
        }
        
        shift_pic existing = shiftDAO.getById(idShift);
        if (existing == null) {
            JOptionPane.showMessageDialog(frame, "Shift not found");
            return;
        }
        
        existing.setIdEmployee(employeeId);
        existing.setEnd_check_time(LocalDateTime.now());
        existing.setNote(frame.gettfNote().getText());
        
        shiftDAO.update(existing);
        
        JOptionPane.showMessageDialog(null, "Successfully updated data.");
        isiTable();
        clear();
    }
    
    public void delete(int idShift) {
        int confirm = JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to delete this shift?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            shiftDAO.delete(idShift);
            JOptionPane.showMessageDialog(null, "Successfully deleted data.");
            isiTable();
            clear();
        }
    }
    
    public void fillEmployeeComboBox() {
        List<Employee> employees = getEmployees();
        frame.getCbName().removeAllItems();
        
        for (Employee e : employees) {
            frame.getCbName().addItem(e.getName());
        }
    }

    public int getEmployeeIdByName(String name) {
        for (Employee e : getEmployees()) {
            if (e.getName().equals(name)) {
                return e.getId_employee();
            }
        }
        return -1;
    }
}
