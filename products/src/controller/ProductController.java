/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao_interface.ProductInterface;
import model.Product;
import model.TableProductModel;
import view.ProductView;

import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author windows 10
 */
public class ProductController {
    ProductView frame;
        ProductInterface implProduct;
        
        List<Product> lb;
        
        public ProductController (ProductView frame) {
            this.frame = frame;
            implProduct = new ProductDAO();
            lb = implProduct.getAll();
        }
        
        public void clear() {
            frame.getTxtID().setText("");
            frame.getTxtName().setText("");
            frame.getTypeComboBox().setSelectedIndex(0);
            frame.getQty().setText("");
            setAutoID(); // Tambahkan ini di dalam clear()

        }
    
    public void isiTable() {
        lb = implProduct.getAll();
        TableProductModel tme = new TableProductModel(lb);
        frame.getTableData().setModel(tme);
    }
    
    public void isiField(int row) {
        frame.getTxtID().setText(String.valueOf(lb.get(row).getIdProduct()));
        frame.getTxtName().setText(lb.get(row).getName());
        frame.getTypeComboBox().setSelectedItem(lb.get(row).getType());
        frame.getQty().setText(String.valueOf(lb.get(row).getQty()));
    }
    
    public void insert() {
        if (!frame.getTxtID().getText().trim().isEmpty() && !frame.getTxtName().getText().trim().isEmpty()) {
            Product product = new Product();
            product.setIdProduct(Integer.parseInt(frame.getTxtID().getText()));
            product.setName(frame.getTxtName().getText());
            product.setType(frame.getTypeComboBox().getSelectedItem().toString());
            product.setQty(Integer.parseInt(frame.getQty().getText()));
            product.setIs_deleted(false);  // Default to false for new products
            implProduct.insert(product);
            
            JOptionPane.showMessageDialog(null, "Successfully saved data.");
        } else {
            JOptionPane.showMessageDialog(frame, "field cannot be empty.");
        }
    }
    
     public void update() {
        if (!frame.getTxtID().getText().trim().isEmpty()) {
            Product product = new Product();
            product.setIdProduct(Integer.parseInt(frame.getTxtID().getText()));
            product.setName(frame.getTxtName().getText());
            product.setType(frame.getTypeComboBox().getSelectedItem().toString());
            product.setQty(Integer.parseInt(frame.getQty().getText()));
            product.setIs_deleted(false);  // Default to false for new products
            implProduct.update(product);
            
            JOptionPane.showMessageDialog(null, "Successfully updated data.");
        } else {
            JOptionPane.showMessageDialog(frame, "choose the data first.");
        }
    }
     
     public void delete() {
         if (!frame.getTxtID().getText().trim().isEmpty()) {
             int id = Integer.parseInt(frame.getTxtID().getText());
             implProduct.delete(id);
             JOptionPane.showMessageDialog(null, "Successfully deleted data.");
         } else {
             JOptionPane.showMessageDialog(frame, "Choose the data first.");
         }
     }
     
public void isiTableSearch() {
    String idText = frame.getTxtID().getText().trim();
    String nameText = frame.getTxtSearch().getText().trim();
    
    int id = -1; // Nilai default untuk ID
    if (!idText.isEmpty()) {
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid ID format! Please enter a number.");
            return;
        }
    }
    
    // Panggil metode search dengan kedua parameter
    lb = implProduct.searchByIdOrName(id, nameText);
    TableProductModel tme = new TableProductModel(lb);
    frame.getTableData().setModel(tme);
}

public void searchByIdOrName() {
    String idText = frame.getTxtID().getText().trim();
    String nameText = frame.getTxtSearch().getText().trim();
    
    if (idText.isEmpty() && nameText.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter ID or Name to search.");
        return;
    }
    
    isiTableSearch();
}

     
     public void setAutoID() {
        int nextId = implProduct.getNextProductId();
        frame.getTxtID().setText(String.valueOf(nextId));
    }
}
