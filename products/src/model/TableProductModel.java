/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableProductModel extends AbstractTableModel {
    List <Product> lb;
    
    public TableProductModel(List<Product> lb) {
        this.lb = lb;
    }
    
    @Override
    public int getColumnCount() {
        return 5;
    }
    
    @Override
    public int getRowCount() {
        return lb.size();
    }
    
     public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Name";
            case 2:
                return "Type";
            case 3:
                return "Quantity";
            case 4:
                return "Available";
            default:
                return null;
        }
    }
     
     public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return lb.get(row).getIdProduct();
            case 1:
                return lb.get(row).getName();
            case 2:
                return lb.get(row).getType();
            case 3:
                return lb.get(row).getQty();
            case 4:
                return lb.get(row).isIs_available();
            default:
                return null;
        }
    }
}
