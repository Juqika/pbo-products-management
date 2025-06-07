/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class connect {
    static Connection cC;
    
    public static Connection getcC(){
        MysqlDataSource sql = new MysqlDataSource();
        if(cC == null){
            sql.setDatabaseName("management_product");
            sql.setUser("root");
            sql.setPassword("");
            
            try{
                cC = sql.getConnection();
            }catch(SQLException e){
                System.out.println("error" + e);
            }
        }
        return cC;
    }
    
    public static void main(String[] args) {
        Connection testConnection = getcC();
        if (testConnection != null) {
            System.out.println("Koneksi berhasil!");
        } else {
            System.out.println("Koneksi gagal!");
        }
    }
}
