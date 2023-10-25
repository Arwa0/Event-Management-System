
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nadam
 */
import java.util.Properties;
import java.sql.*;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
public class User {
    private int id ;
    private String userName;
    private char[] password ;
    private String email ;
    private int role_id;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
    
    
    
    
    
    
    
    //creates an account by inserting it into the database 
    public int createAccount(){
               int x;
       try{
       String pass ="";
      
       for(int i = 0 ; i< password.length;i++){
           pass+=this.password[i];
       }
       
       String sql ="insert into users(name,username,email,pass,role_id) values('"+this.name+"','"+this.userName+"'"+",'"+this.email+"','"+pass+"',"+this.role_id+")";
      Db.update2(sql);
    
    
      sql = "select id,role_id from users where username= '"+this.userName+"' and pass = '"+pass+"'";
     ResultSet res= Db.query(sql);
       if(res.next()){
           this.id= res.getInt("id");
           System.out.println(id);
           this.role_id=res.getInt("role_id");
       }
       
       if (role_id==3){
           //need to figure this out
        sql="insert into sp(spname,sp_id) values('"+this.name+"',"+id+")";
        Db.update2(sql);
       }
       }
             catch(SQLException ex){
       
      JOptionPane.showMessageDialog(null, ex);
      return 0 ;

             } 
       return 1 ;
    }
    
    
    
    
    //log in function returns the role id , you can then choose which frame you're going to make visible according 
    
    public int logIn(){
             int x = 0; 
        try{

      String pass ="";
      for(int i = 0 ; i< this.password.length;i++){
           pass+=password[i];
       }
       
      String sql ="select role_id from users where username ='"+this.userName+"' and pass='"+pass+"'";
      ResultSet s = Db.query(sql);
      if(s.next()){
       x = s.getInt("role_id");}
       else JOptionPane.showMessageDialog(null, "wrong email or password", "log in failed", JOptionPane.ERROR_MESSAGE);
      
              }
      
      catch(SQLException ex){
          JOptionPane.showMessageDialog(null, ex);
      }
    
    return x ;
    
}
    
    
    
    
public void sendEmail(String recipient,String subject,String text) throws Exception
{
    Properties properties=new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    
    String sEmail="managementevent299@gmail.com";
    String sPassword="HelloThisIsAProject";
    
    Session session = Session.getInstance(properties,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(sEmail,sPassword);
        }
                });
    Message message;
        message = prepareMessage(session,sEmail,recipient,subject,text);
    Transport.send(message);
    
}

    private Message prepareMessage(Session session, String sEmail,String recipient,String subject,String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        } catch (MessagingException ex) {
            //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

}
