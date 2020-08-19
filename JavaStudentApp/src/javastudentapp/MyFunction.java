
package javastudentapp;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFunction {
    public static int countData(String tableName)
    {   int total =0;
        Connection con = MyConnection.getConnection();
        
        try {
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM " +tableName);
            while(rs.next())
            {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return total;
    }
}
