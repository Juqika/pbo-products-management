/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EmployeeDAO;
import dao_interface.EmployeeInterface;
import model.Employee;
import model.TableEmployeeModel;
import view.EmployeeView;

import java.util.List;
import javax.swing.JOptionPane;
import model.Product;
import model.TableProductModel;
/**
 *
 * @author user
 */
public class EmployeeController {
    EmployeeView frame;
    EmployeeInterface impleEmployee;
    
    List<Employee> lb;
    
    public EmployeeController (EmployeeView frame) {
        this.frame = frame;
        impleEmployee = new EmployeeDAO();
        lb = impleEmployee.getAll();
    }
    
     public void clear() {
        frame.getTxtID().setText("");
        frame.getTxtName().setText("");
        frame.getGenderComboBox().setSelectedIndex(0);
        setAutoID(); // Tambahkan ini di dalam clear()

        }
    public void isiTable() {
        lb = impleEmployee.getAll();
        TableEmployeeModel tme = new TableEmployeeModel(lb);
        frame.getTableData().setModel(tme);
    }
    
    public void isiField(int row) {
        frame.getTxtID().setText(String.valueOf(lb.get(row).getId_employee()));
        frame.getTxtName().setText(lb.get(row).getName());
        frame.getGenderComboBox().setSelectedItem(lb.get(row).getGender());
    }
    
    public void insert() {
         if (!frame.getTxtID().getText().trim().isEmpty() && !frame.getTxtName().getText().trim().isEmpty()) {
            Employee employee = new Employee();
            employee.setId_employee(Integer.parseInt(frame.getTxtID().getText()));
            employee.setName(frame.getTxtName().getText());
            employee.setGender(frame.getGenderComboBox().getSelectedItem().toString());
            employee.setIs_deleted(false);  
            impleEmployee.insert(employee);
            
            JOptionPane.showMessageDialog(null, "Successfully saved data.");
        } else {
            JOptionPane.showMessageDialog(frame, "field cannot be empty.");
        }
    }
    
    public void update() {
        if(!frame.getTxtID().getText().trim().isEmpty()) {
        Employee employee = new Employee();
        employee.setId_employee(Integer.parseInt(frame.getTxtID().getText()));
        employee.setName(frame.getTxtName().getText());
        employee.setGender(frame.getGenderComboBox().getSelectedItem().toString());
        employee.setIs_deleted(false);  
        impleEmployee.update(employee);
        
        JOptionPane.showMessageDialog(null, "Successfully saved data.");
        }else {
            JOptionPane.showMessageDialog(frame, "field cannot be empty.");
        }
    }
    
    public void delete() {
         if (!frame.getTxtID().getText().trim().isEmpty()) {
             int id = Integer.parseInt(frame.getTxtID().getText());
             impleEmployee.delete(id);
             JOptionPane.showMessageDialog(null, "Successfully deleted data.");
         } else {
             JOptionPane.showMessageDialog(frame, "Choose the data first.");
         }
     }
    
    public void isiTableSearchName() {
         lb = impleEmployee.getSearchName(frame.getTxtSearchName().getText());
         TableEmployeeModel tme = new TableEmployeeModel(lb);
         frame.getTableData().setModel(tme);
     }
    
    public void searchName() {
         if (!frame.getTxtSearchName().getText().trim().isEmpty()) {
             impleEmployee.getSearchName(frame.getTxtSearchName().getText());
             isiTableSearchName();
         } else {
             JOptionPane.showMessageDialog(frame, "Please choose the data.");
         }
     }
    
    public void setAutoID() {
        int nextId = impleEmployee.getNextEmployeeId();
        frame.getTxtID().setText(String.valueOf(nextId));
    }
}
