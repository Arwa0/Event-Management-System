

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author Boody Allam
 */
public class Db extends User {

    private Statement st;

    public Statement getSt() {
        return st;
    }
    private Connection conn;
    private String sql;
    private ResultSet res;
    static  ResultSet rs ; 
    public Db() {
        try {
            String connect = "jdbc:sqlserver://localhost\\BOODYALLAM\\SQLEXPRESS:1433;databaseName=Event"
                    + ";username=sa" + ";password=12345";
            conn = DriverManager.getConnection(connect);
            st = conn.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    
   public static int update2(String sql) throws SQLException {
        int res=0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection con=DriverManager.getConnection(Db.getUrl());
            Statement st = con.createStatement();
            res=st.executeUpdate(sql); 
           
            } catch (ClassNotFoundException ex) { 
                JOptionPane.showMessageDialog(null, "sql server not found!","Error",JOptionPane.ERROR_MESSAGE);
            } 
        return res;
    }
    
    
    public static ResultSet query2(String sql)throws SQLException{
     try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection con=DriverManager.getConnection(Db.getUrl());
            Statement st = con.createStatement();
            rs=st.executeQuery(sql); 
           
            } catch (ClassNotFoundException ex) { 
                JOptionPane.showMessageDialog(null, "sql server not found!","Error",JOptionPane.ERROR_MESSAGE);
            } return rs;
    }

      //to read from database   
    public static ResultSet query (String sql) {
    ResultSet res = null ;
                    try{
        String ConnectionUrl = Db.getUrl();
       Connection con = DriverManager.getConnection(ConnectionUrl);
       Statement st = con.createStatement();
       res = st.executeQuery(sql);
       //st.close();
       //con.close();
       return res ; 
                    }
       catch( SQLException ex){
       
       JOptionPane.showMessageDialog(null, ex.getMessage());
       
       }
   return res ;                 
    }






//to write in database
 public static void update (String sql){ 
                         try{
        String ConnectionUrl =Db.getUrl();
       Connection con = DriverManager.getConnection(ConnectionUrl);
       Statement st = con.createStatement();
       st.executeUpdate(sql);
       st.close();
       con.close();
      
    }
       catch( SQLException ex){
       
       JOptionPane.showMessageDialog(null, ex.getMessage());
       
       }
}

  
 
    public static String getUrl() {

        return  "jdbc:sqlserver://localhost\\DESKTOP-8713V0A\\SQLEXPRESS:1433;databaseName=Event"+";username=sa"+";password=12345";
    }
   
 public int getPmIdfromRole(int roleId)
    {
       int id = 0;
        try {
            sql = "select id from users where role_id = " + roleId + "";
            res = st.executeQuery(sql);
            while (res.next()) {
                id =res.getInt("id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return id;

    }
    public String checkedContact (String username , String ename)
    {
    String s = "";
        try{
            sql = "select message from contact where sendfrom ="+getUserId(username)+" and event_id = "+
                    getEventId(ename)+ "";
            res = st.executeQuery(sql);
            while (res.next()) {
                s = (res.getString("message"));
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return s;
    }
    public void contactPm(String message ,String eventName ,String username) {
        try {
            sql = "insert into contact values ("+getUserId(username)+","+getPmIdfromRole(2)+
                    ","+getEventId(eventName)+
                    ", '"+message+"' )";
            st.executeUpdate(sql);
             JOptionPane.showMessageDialog(null, "Thank you ! we will response you as soon as possible",
                            "Send Successfully",
                            JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
    public String getEmail (String userName)
    {
    String s = "";
        try{
            sql = "select email from users where id ="+getUserId(userName)+"";
            res = st.executeQuery(sql);
            while (res.next()) {
                s = (res.getString("email"));
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return s;
    }
    public String getMessagesFromPm(String eventName,String username)
    {
    String s = "";
        try{
            sql = "select message from contact where sendfrom ="+getPmIdfromRole(2)+" and sendto = "+
                    getUserId(username)+" and event_id = "+getEventId(eventName)+"";
            res = st.executeQuery(sql);
            while (res.next()) {
                s = (res.getString("message"));
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return s;
    }

    public void bookEvent(Event event,String username ) {
        try {
            sql = "insert into event2 (customer_id,elocation,ename,sp_id,edate)values ("+getUserId(username)+",'"
                    + event.getLocation() + "','"
                    + event.getName() + "'," + getSpId(event.getSpname())+ ",'" + event.getDate()  + "')";
            st.executeUpdate(sql);
            sql = "insert into event_sp (event_id,sp_id) values ("
                    + getEventId(event.getName()) + ","
                    + getSpId(event.getSpname()) + ")";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,
                    "Your reservation has been received \n Thank you  !",
                    "Booked Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
            deleteSelectedDate(event.getDate());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage()
            , "main eroor", JOptionPane.ERROR_MESSAGE);
        }
    }
        public int getEventId(String ename) {
        int id = 0;
        try {
            sql = "select id from event2 where ename = '" + ename + "'";
            res = st.executeQuery(sql);
            while (res.next()) {
                id = res.getInt("id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "event eroorr", JOptionPane.ERROR_MESSAGE);

        }
        return id;

    }

    public int getSpId(String sname) {
        int id = 0;
        try {
            sql = "select id from users where role_id = 3 and name = '" + sname + "'";
             res = st.executeQuery(sql);
            while (res.next()) {
                id =res.getInt("id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return id;

    }

    public void deleteSelectedDate(Date date) {
        try {
            sql = "delete from vdate where odate = '" + date + "'";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateEvent(Event event, Date oldDate , String oldName) {
        try {
            sql = "update event2 set elocation='" + event.getLocation() + "' , ename = '" + event.getName()
                    + "' , edate='" + event.getDate() + "',sp_id = " + getSpId(event.getSpname()) + " where id ="
                    + getEventId(oldName) + "";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        reInsertDate(oldDate);
        deleteSelectedDate(event.getDate());
    }

    public void reInsertDate(Date d) {
        try {
            sql = "insert into vdate values ('" + d + "')";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public int getUserId(String userName) {
        int id = 0;
        try {
            sql = "select id from users where username = '" + userName + "'";
            res = st.executeQuery(sql);
            while (res.next()) {
                id =res.getInt("id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return id;

    }



    public void deleteEvent(String eventname, Date oldDate) {
        try {
            sql = "delete from event2 where id = " + getEventId(eventname) + "";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Your event has been removed ", "Removed Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "11111ERROR", JOptionPane.ERROR_MESSAGE);
        }
                            reInsertDate(oldDate);

    }
    public  boolean checkEventName(String name){
                    boolean flag = false;
        try {
            sql = "select ename from event2";
            res = st.executeQuery(sql);
            while (res.next()) {
                if(res.getString("ename").equals(name))
                {
                    flag=true;
                    break;
            }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "event eroorr", JOptionPane.ERROR_MESSAGE);

        }
        if(flag)
            return true;
        else return false ;
    }

    public String getEventLocation(String s) {
        String s1 = "";
        try {
            sql = "select elocation from event2 where id = " + getEventId(s) + "";
            res = st.executeQuery(sql);
            while (res.next()) {
                s1 = (res.getString("elocation"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return s1;
    }

    public String getSpname(String s) {
        String s1 = "";
        try {
            sql = "select distinct name from users where role_id = 3 and id ="
                    + getSpIdFromEventId(s) + "";
             res = st.executeQuery(sql);
            while (res.next()) {
                s1 = (res.getString("name"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return s1;
    }
    public int getSpIdFromEventId(String s)
    {
        int id= 0 ;
        try { 
            sql = "select sp_id from event2 where id ="+getEventId(s)+"";
             res = st.executeQuery(sql);
            while (res.next()) {
                id = (res.getInt("sp_id"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return id;
    }

    public Date getEventDate(String name) {
        Date d1 = new Date();
        try {
           sql = "select edate from event2 where id = " + getEventId(name) + "";
            res = st.executeQuery(sql);
            while (res.next()) {
                d1= (res.getDate("edate"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return d1;
    }
    public String getStatus(String ename)
    {
     String s = "";
             try {
           sql = "select sname from estatus join event2 on estatus.id=event2.estatus_id  where event2.id=" + getEventId(ename) + "";
            res = st.executeQuery(sql);
            while (res.next()) {
                s= (res.getString("sname"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return s; 
    }
    
    public float getPmPrice(String eventName)
    {
        float p= 0 ;
        try { 
            sql = "select price from event2 where id ="+getEventId(eventName)+"";
             res = st.executeQuery(sql);
            while (res.next()) {
                p = (res.getFloat("price"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return p;
    }
    public float getSpPrice(String eventName)
    {
        float p = 0 ;
        try { 
            
            sql = "select price from event_sp where event_id="+getEventId(eventName)+"";
            res = st.executeQuery(sql);
            while(res.next())
            {
            p=(res.getFloat("price"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return p;
    }
    

    public void close() {
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
  
}
