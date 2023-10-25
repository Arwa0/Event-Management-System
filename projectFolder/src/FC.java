



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FC {
     public ArrayList<FlagChange> NewChange(){
        ArrayList<FlagChange> list = new ArrayList<>();
        try{
      String ConnectionUrl=Db.getUrl();
      Connection con = DriverManager.getConnection(ConnectionUrl);
      Statement st = con.createStatement();
       
      String sql = "select customer_id , ename , price , email from users , event2  where users.id = [customer_id] and flag = " + 1;
      
       ResultSet res = st.executeQuery(sql);
       FlagChange flagChange;
       while(res.next()){
        flagChange = new FlagChange();
       flagChange.setCid(res.getInt("customer_id"));
       flagChange.setName(res.getString("ename"));
        
        flagChange.setPrice(res.getDouble("price"));//error bc price not string
        flagChange.setEmail(res.getString("email"));


       
       list.add(flagChange);
//        
       }
       
       
        }
    catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
      
       return list;
       
    }
}
