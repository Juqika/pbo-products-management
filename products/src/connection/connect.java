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
//            sql.setURL("jdbc:mysql://localhost:3306/");
            sql.setDatabaseName("");
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
}
