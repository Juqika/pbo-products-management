/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao_interface;

import java.util.List;
import model.Employee;

/**
 *
 * @author user
 */
public interface EmployeeInterface {
    void insert (Employee e);
    void update(Employee e);
    void delete(int id);
    int getNextEmployeeId();
    List<Employee> getAll();
    List<Employee> getSearchName(String name);
}
