
package javastudentapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class student {
 public void insertUpdateDeleteStudent(char operation, Integer id, String first_name, String last_name, String sex, 
         String birth_date, String phone, String address)
 {
     Connection con = MyConnection.getConnection();
     PreparedStatement ps;
     if(operation == 'i')
     {
         try {
             ps = con.prepareStatement("INSERT INTO student(first_name, last_name, sex, birth_date, phone, address) VALUES (?,?,?,?,?,?)");
             ps.setString(1, first_name);
             ps.setString(2, last_name);
             ps.setString(3, sex);
             ps.setString(4, birth_date);
             ps.setString(5, phone);
             ps.setString(6, address);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "New Student Added");
            } 

//             if(ps.executeUpdate() > 0)
//             {
//                 JOptionPane.showMessageDialog(null, "Student added successfully");
//             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
     }
     if( operation == 'u')
     {
         try{
            ps=con.prepareStatement("UPDATE student SET first_name= ?, last_name= ?, sex= ?, birth_date= ?, phone= ?, address= ? where id = ?");
            ps.setString(1, first_name);
             ps.setString(2, last_name);
             ps.setString(3, sex);
             ps.setString(4, birth_date);
             ps.setString(5, phone);
             ps.setString(6, address);
            ps.setInt(7,id);
            if(ps.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "Student data Updated");
            }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
     }
     if( operation == 'd' )
     {
         try{
             ps = con.prepareStatement("DELETE from student where id = ?");
             ps.setInt(1, id);
             if(ps.executeUpdate() > 0)
             {
                 JOptionPane.showMessageDialog(null, "Student deleted");
             }
         }
         catch(SQLException ex)
         {
             System.out.println(ex.getMessage());
         }
     }
 }
 public void fillStudentJtable(JTable table, String valueToSearch)
 {
    Connection con = MyConnection.getConnection();
     try {
         PreparedStatement ps = con.prepareStatement("SELECT * FROM student where CONCAT(first_name, last_name, phone, address) LIKE ?");
        ps.setString(1, "%"+valueToSearch+"%");
        ResultSet rs = ps.executeQuery();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object row[];
        while(rs.next())
        {
            row= new Object[7];
            row[0]= rs.getInt(1);
            row[1]= rs.getString(2);
            row[2]= rs.getString(3);
            row[3]= rs.getString(4);
            row[4]= rs.getString(5);
            row[5]= rs.getString(6);
            row[6]= rs.getString(7);
            model.addRow(row);
                    
        }
                 } catch (SQLException ex) {
         Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
}
