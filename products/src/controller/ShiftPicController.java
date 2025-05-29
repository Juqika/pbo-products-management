/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao_interface.shiftPicInterface;
import dao.shiftPicDAO;
import model.shift_pic;

import java.time.LocalDateTime;
import java.util.List;

public class ShiftPicController {
    private final shiftPicInterface shiftDAO;
    
    public ShiftPicController(){
        this.shiftDAO = new shiftPicDAO();
    }
    public void cInsert(String name, LocalDateTime start, LocalDateTime end, String note){
        shift_pic s = new shift_pic();
        s.setName(name);
        s.setStart_check_time(start);
        s.setStart_check_time(end);
        s.setNote(note);
        s.setIs_deleted(false);
        shiftDAO.insert(s);
    }
    public void cUpdate(int idShift, String name, LocalDateTime start, LocalDateTime end, String note){
        shift_pic s = new shift_pic();
        s.setIdShift(idShift);
        s.setName(name);
        s.setStart_check_time(start);
        s.setStart_check_time(end);
        s.setNote(note);
        shiftDAO.update(s);
    }
    public void cDelete(int idShift){
        shiftDAO.delete(idShift);
    }
    public List<shift_pic> cGetAll(){
        return shiftDAO.getAll();
    }
    public shift_pic cGetById(int idShift){
        return shiftDAO.getById(idShift);
    }
}
