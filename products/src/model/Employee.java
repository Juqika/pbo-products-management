/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Employee {
    private int id_employee;
    private String name, gender;
    private boolean is_deleted;

    // Konstruktor default
    public Employee() {}

    // Konstruktor dengan parameter
    public Employee(int id_employee, String name, String gender, boolean is_deleted) {
        this.id_employee = id_employee;
        this.name = name;
        this.gender = gender;
        this.is_deleted = is_deleted;
    }

    // Getter & Setter untuk is_deleted
    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    // Getter & Setter untuk id_employee
    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    // Getter & Setter untuk name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter & Setter untuk gender (diubah dari 'male')
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
