
package javastudentapp;
import java.sql.*;


public class MyConnection {
   public static Connection getConnection(){
     Connection con =null;
       try {
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://localhost/stdmgdb", "root", "");
       } catch (ClassNotFoundException | SQLException ex) {
           System.out.println(ex.getMessage());
       }
     return con;
   } 
}
