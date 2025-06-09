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
import dao.EmployeeDAO;
import dao_interface.EmployeeInterface;
import model.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ShiftPicController {
    private ShiftPicView frame;
    private final shiftPicInterface shiftDAO;
    private final EmployeeInterface employeeDAO;
    private List<shift_pic> sp;
    
    public ShiftPicController(ShiftPicView frame) {
        this.frame = frame;
        this.shiftDAO = new shiftPicDAO();
        this.employeeDAO = new EmployeeDAO();
        sp = shiftDAO.getAll();
    }
    
    // Jika perlu menampilkan daftar employee di suatu tempat
    public List<Employee> getEmployees() {
        return ((shiftPicDAO)shiftDAO).getEmployees();
    }
    
    public void clear() {
        frame.gettfNote().setText("");
    }
    
    public void isiTable() {
        // Ambil semua data shift_pic yang sudah termasuk nama employee
        sp = shiftDAO.getAll();
        
        // Buat model tabel sederhana
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Shift");
        model.addColumn("Employee");
        model.addColumn("Start Time");
        model.addColumn("End Time");
        model.addColumn("Note");
        
        // Format datetime
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Isi model dengan data
        for (shift_pic s : sp) {
            String startTime = s.getStart_check_time() != null ? s.getStart_check_time().format(df) : "";
            String endTime = s.getEnd_check_time() != null ? s.getEnd_check_time().format(df) : "";
            
            model.addRow(new Object[]{
                s.getIdShift(),
                s.getName(),
                startTime,
                endTime,
                s.getNote()
            });
        }
        
        // Set model ke tabel
        frame.gettTable().setModel(model);
    }
    
    public void isiField(int row) {
        // Jika kita tidak lagi menggunakan field ID, kita hanya perlu mengisi field note
        frame.gettfNote().setText(sp.get(row).getNote());
    }
    
    public void insert() {
        // Ambil nama employee dari ComboBox
        String employeeName = (String) frame.getCbName().getSelectedItem();
        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an employee");
            return;
        }
        
        // Dapatkan ID employee berdasarkan nama
        int employeeId = getEmployeeIdByName(employeeName);
        if (employeeId == -1) {
            JOptionPane.showMessageDialog(frame, "Employee not found");
            return;
        }
        
        shift_pic s = new shift_pic();
        s.setIdEmployee(employeeId);
        
        // Set waktu mulai ke waktu sekarang
        LocalDateTime now = LocalDateTime.now();
        s.setStart_check_time(now);
        s.setEnd_check_time(null);
        s.setNote(frame.gettfNote().getText());
        s.setIs_deleted(false);
        
        shiftDAO.insert(s);
        
        JOptionPane.showMessageDialog(null, "Successfully saved data.");
        isiTable();
        clear();
    }
    
    public void update(int idShift) {
        // Ambil nama employee dari ComboBox
        String employeeName = (String) frame.getCbName().getSelectedItem();
        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an employee");
            return;
        }
        
        // Dapatkan ID employee berdasarkan nama
        int employeeId = getEmployeeIdByName(employeeName);
        if (employeeId == -1) {
            JOptionPane.showMessageDialog(frame, "Employee not found");
            return;
        }
        
        shift_pic s = new shift_pic();
        s.setIdShift(idShift);
        s.setIdEmployee(employeeId);
        
        // Get the existing shift to preserve start time
        shift_pic existing = shiftDAO.getById(idShift);
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
    }
    
    public void delete(int idShift) {
        // Tambahkan konfirmasi sebelum menghapus
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

    // Metode untuk mendapatkan ID employee berdasarkan nama
    public int getEmployeeIdByName(String name) {
        List<Employee> employees = getEmployees();
        for (Employee e : employees) {
            if (e.getName().equals(name)) {
                return e.getId_employee();
            }
        }
        return -1; // Return -1 jika tidak ditemukan
    }
}
