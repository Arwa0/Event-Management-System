

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nadam
 */
import java.sql.*;


import javax.swing.JOptionPane;
public class serviceProvider {
    public static String getMessageFromPM(int spId,int pmId, int eventId)
    {  
        String m ="";
        try {
           
        String Sql = "select message from contact where sendto="+spId+"and sendfrom="+pmId+"and event_id="+eventId;
        ResultSet res =Db.query(Sql);
        while (res.next()) {
            m+=(res.getString("message"));
        }   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    return m;
    }
    
    public static int sendMessageToPm(int spId,int pmId, int eventId,String message)
    { 
        String sql ="insert into contact values("+spId+","+pmId+","+eventId+",'"+message+"')";
      Db db = new Db();
        try {
            db.getSt().executeUpdate(sql);
              
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "you have sent a message about this event already , please wait for a response");
            return 0;
        }
      
      
   return 1 ;
    }
    
    public static void setDate(String date,int sp)
    { String sql = "insert into vdate(sp_id,odate) values("+sp+","+"'"+date+"')";
      Db.update(sql);
    
    }
    
    
    
}
