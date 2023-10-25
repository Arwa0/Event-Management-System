


import java.sql.*;
import javax.swing.JOptionPane;
// [sa on dbo]

/**
 *
 * @author Boody Allam
 */
public class Customer extends User{

    protected int roleId = 1;
//    public static String tempname;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

//    public static String getTempname() {
//        return tempname;
//    }
//
//    public static void setTempname(String tempname) {
//        Customer.tempname = tempname;
//    }
     public static int getUserId(String userName) {
        int id = 0;
        try {
            String connect = Db.getUrl();
            Connection conn = DriverManager.getConnection(connect);
            Statement st = conn.createStatement();
            String sql = "select id from users where username = '" + userName + "'";
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                id = res.getInt("id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        return id;

    }

}
