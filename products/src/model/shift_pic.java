/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author windows 10
 */
public class shift_pic {
    private int idShift;
    private String name, note;
    private LocalDateTime start_check_time, end_check_time;
    private boolean is_deleted;
    
    public shift_pic(){}
    public shift_pic(int idShift, String name, LocalDateTime start_check_time, LocalDateTime end_check_time, String note, boolean is_deleted){
        this.idShift = idShift;
        this.name = name;
        this.start_check_time = start_check_time;
        this.end_check_time = end_check_time;
        this.note = note;
        this.is_deleted = is_deleted;
    }
    public boolean isIs_deleted() {
        return is_deleted;
    }
    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
    public int getIdShift() {
        return idShift;
    }
    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public LocalDateTime getStart_check_time() {
        return start_check_time;
    }
    public void setStart_check_time(LocalDateTime start_check_time) {
        this.start_check_time = start_check_time;
    }
    public LocalDateTime getEnd_check_time() {
        return end_check_time;
    }
    public void setEnd_check_time(LocalDateTime end_check_time) {
        this.end_check_time = end_check_time;
    }

}
