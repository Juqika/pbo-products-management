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
        setAutoID(); 

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
    
    public void isiTableSearch() {
        String nameText = frame.getTxtSearchName().getText().trim();
        Integer id = null;
        String name = null;

        if (!nameText.isEmpty()) {
            name = nameText;
        }

        List<Employee> lb = impleEmployee.searchByIdOrName(id, name);

        TableEmployeeModel tme = new TableEmployeeModel(lb);
        frame.getTableData().setModel(tme);
    }

    public void searchByIdOrName() {
        String searchText = frame.getTxtSearchName().getText().trim();

        List<Employee> results;

        if (searchText.isEmpty() || searchText.equals("0")) {
            results = impleEmployee.getAll();
        } else {
            Integer id = null;
            String name = null;

            try {
                id = Integer.parseInt(searchText);
            } catch (NumberFormatException e) {
                name = searchText;
            }

            results = impleEmployee.searchByIdOrName(id, name);
        }

        TableEmployeeModel tme = new TableEmployeeModel(results);
        frame.getTableData().setModel(tme);
    }

    public void setAutoID() {
        int nextId = impleEmployee.getNextEmployeed();
        frame.getTxtID().setText(String.valueOf(nextId));
    }
}
