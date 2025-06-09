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
    
        /**
     * Constructor untuk ShiftPicController
     * Menginisialisasi controller dengan view yang diberikan,
     * membuat koneksi ke database melalui DAO, dan memuat data awal
     * 
     * @param frame View yang akan dikontrol
     */
    public ShiftPicController(ShiftPicView frame) {
        this.frame = frame;
        this.shiftDAO = new shiftPicDAO();
        refreshData();
        
        // Inisialisasi model tabel
        tableModel = new TableShiftPic(sp);
        frame.gettTable().setModel(tableModel);
    }
    
    /**
     * Memperbarui data dari database
     * Mengambil semua data shift_pic yang tidak dihapus
     */
    private void refreshData() {
        sp = shiftDAO.getAll();
    }
    
    /**
     * Mendapatkan daftar semua karyawan
     * 
     * @return List berisi objek Employee
     */
    public List<Employee> getEmployees() {
        return ((shiftPicDAO)shiftDAO).getEmployees();
    }
    
    /**
     * Membersihkan field input di form
     */
    public void clear() {
        frame.gettfNote().setText("");
    }
    
    /**
     * Mengisi tabel dengan data dari database
     * Membuat model tabel baru dan mengisinya dengan data shift_pic
     */
    public void isiTable() {
        // Memperbarui data dari database
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
        // Mengisi field note dengan data dari shift_pic yang dipilih
        frame.gettfNote().setText(sp.get(row).getNote());
        
        // Mengatur ComboBox untuk menampilkan nama karyawan yang sesuai
        String employeeName = sp.get(row).getName();
        if (employeeName != null && !employeeName.isEmpty()) {
            frame.getCbName().setSelectedItem(employeeName);
        }
    }
    
    /**
     * Menyimpan shift baru ke database
     * Mengambil data dari form dan membuat objek shift_pic baru
     */
    public void insert() {
        // Mendapatkan nama karyawan dari ComboBox
        String employeeName = (String) frame.getCbName().getSelectedItem();
        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an employee");
            return;
        }
        
        // Mendapatkan ID karyawan berdasarkan nama
        int employeeId = getEmployeeIdByName(employeeName);
        if (employeeId == -1) {
            JOptionPane.showMessageDialog(frame, "Employee not found");
            return;
        }
        
        // Membuat objek shift_pic baru
        shift_pic s = new shift_pic();
        s.setIdEmployee(employeeId);
        s.setStart_check_time(LocalDateTime.now()); // Waktu mulai adalah waktu sekarang
        s.setNote(frame.gettfNote().getText());
        s.setIs_deleted(false);
        
        // Menyimpan ke database
        shiftDAO.insert(s);
        
        // Menampilkan pesan sukses
        JOptionPane.showMessageDialog(null, "Successfully saved data.");
        
        // Memperbarui tabel dan membersihkan form
        isiTable();
        clear();
    }
    
    /**
     * Memperbarui shift yang ada di database
     * Mengambil data dari form dan memperbarui objek shift_pic yang ada
     * 
     * @param idShift ID shift yang akan diperbarui
     */
    public void update(int idShift) {
        // Mendapatkan nama karyawan dari ComboBox
        String employeeName = (String) frame.getCbName().getSelectedItem();
        if (employeeName == null || employeeName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please select an employee");
            return;
        }
        
        // Mendapatkan ID karyawan berdasarkan nama
        int employeeId = getEmployeeIdByName(employeeName);
        if (employeeId == -1) {
            JOptionPane.showMessageDialog(frame, "Employee not found");
            return;
        }
        
        // Mendapatkan shift yang ada dari database
        shift_pic existing = shiftDAO.getById(idShift);
        if (existing == null) {
            JOptionPane.showMessageDialog(frame, "Shift not found");
            return;
        }
        
        // Memperbarui data shift
        existing.setIdEmployee(employeeId);
        existing.setEnd_check_time(LocalDateTime.now()); // Waktu selesai adalah waktu sekarang
        existing.setNote(frame.gettfNote().getText());
        
        // Menyimpan perubahan ke database
        shiftDAO.update(existing);
        
        // Menampilkan pesan sukses
        JOptionPane.showMessageDialog(null, "Successfully updated data.");
        
        // Memperbarui tabel dan membersihkan form
        isiTable();
        clear();
    }
    
    /**
     * Menghapus shift dari database (soft delete)
     * 
     * @param idShift ID shift yang akan dihapus
     */
    public void delete(int idShift) {
        // Konfirmasi penghapusan
        int confirm = JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to delete this shift?", 
                "Confirm Delete", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Menghapus shift dari database
            shiftDAO.delete(idShift);
            
            // Menampilkan pesan sukses
            JOptionPane.showMessageDialog(null, "Successfully deleted data.");
            
            // Memperbarui tabel dan membersihkan form
            isiTable();
            clear();
        }
    }
    
    /**
     * Mengisi ComboBox dengan nama-nama karyawan
     */
    public void fillEmployeeComboBox() {
        // Mendapatkan daftar karyawan
        List<Employee> employees = getEmployees();
        
        // Menghapus semua item yang ada di ComboBox
        frame.getCbName().removeAllItems();
        
        // Menambahkan nama karyawan ke ComboBox
        for (Employee e : employees) {
            frame.getCbName().addItem(e.getName());
        }
    }

    /**
     * Mendapatkan ID karyawan berdasarkan nama
     * 
     * @param name Nama karyawan
     * @return ID karyawan, atau -1 jika tidak ditemukan
     */
    public int getEmployeeIdByName(String name) {
        // Mencari karyawan dengan nama yang sesuai
        for (Employee e : getEmployees()) {
            if (e.getName().equals(name)) {
                return e.getId_employee();
            }
        }
        return -1; // Karyawan tidak ditemukan
    }
}
