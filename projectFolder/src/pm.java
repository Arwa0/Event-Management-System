 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class pm {
    
    public pm(){
    }
    
    public ArrayList<Event> reciveCusRequest(int x){
        System.out.println(x);
        ArrayList<Event> list = new ArrayList<>();
        try{
      String sql = "select id,ename,event2.sp_id,elocation,edate,customer_id from event2 where pm_id =" + x ;
      
       ResultSet res = Db.query(sql);
       Event event;
       while(res.next()){
        event = new Event();
       event.setId(res.getInt("id"));
       event.setName(res.getString("ename"));
        event.setSpid(res.getInt("sp_id"));
       event.setCid(res.getInt("customer_id"));
       event.setLocation(res.getString("elocation"));
       event.setDate(res.getDate("edate"));

       
       list.add(event);
       
       }
       
       
        }
    catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
      
       return list;
       
    }
    
    
//  public static double showBill(int event_id){
//      double total=0 ;
//              try{
//      String ConnectionUrl="jdbc:sqlserver://localhost\\DESKTOP-8713V0A\\SQLEXPRESS:1433;databaseName=Event"+";username=sa"+";password=12345";
//      Connection con = DriverManager.getConnection(ConnectionUrl);
//      Statement st = con.createStatement();
//      String sql = "select price from event_sp where event_id= "+event_id;
//      ResultSet res = st.executeQuery(sql);
//      while(res.next()){
//        total+=  res.getDouble("price");
//      }
//              
//     
//              }
//              
//     catch(SQLException ex){
//         JOptionPane.showMessageDialog(null, ex);
//         
//     }         
//   return total;           
//  }  
    
    
    
   
    
    
}

