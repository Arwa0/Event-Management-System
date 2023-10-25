/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author albostan
 */
public class database {
 static int res;
 static ResultSet rs;
    public static int update(String sql) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-8713V0A\\SQLEXPRESS:1433;databaseName=Event"+";username=sa"+";password=12345");
            Statement st = con.createStatement();
            res=st.executeUpdate(sql); 
           
            } catch (ClassNotFoundException ex) { 
                JOptionPane.showMessageDialog(null, "sql server not found!","Error",JOptionPane.ERROR_MESSAGE);
            } 
        return res;
    }
    
    
    public static ResultSet query(String sql)throws SQLException{
     try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-8713V0A\\SQLEXPRESS:1433;databaseName=Event"+";username=sa"+";password=12345");
            Statement st = con.createStatement();
            rs=st.executeQuery(sql); 
           
            } catch (ClassNotFoundException ex) { 
                JOptionPane.showMessageDialog(null, "sql server not found!","Error",JOptionPane.ERROR_MESSAGE);
            } return rs;
    }
}

