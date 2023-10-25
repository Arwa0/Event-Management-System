

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nadam
 */
public class eventPrice extends javax.swing.JFrame {
User user = new User();
DefaultTableModel dtn=new DefaultTableModel();


    /**
     * Creates new form eventPrice
     */
    public eventPrice() {
        initComponents();
    }
    
        public eventPrice(String username) {
    try {
        initComponents();
        user.setUserName(username);
        table.setModel(dtn);
        dtn.addColumn("event id");
        dtn.addColumn("event name");
        dtn.addColumn("location");
        dtn.addColumn("pm");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        String sql ="select id from users where username ='"+user.getUserName()+"'";
        ResultSet res = Db.query(sql);
        while (res.next()) {
            user.setId(res.getInt("id"));
        }
        
        
        sql = "select * from event2 where sp_id ="+user.getId()+"and estatus_id=2";
        res= Db.query(sql);
              
        while (res.next()) {
           dtn.addRow(new Object[]{res.getInt("id"),res.getString("ename"),res.getString("elocation"),res.getInt("pm_id")});
        }
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex, "error", JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(eventPrice.class.getName()).log(Level.SEVERE, null, ex);
    }
 
    
    //to prevent runtime error when event is not selected , just trying though
    
    contactPmB.setEnabled(false);
    setPriceB.setEnabled(false);
    ListSelectionModel listSelectionModel = table.getSelectionModel();
    listSelectionModel.addListSelectionListener(new ListSelectionListener(){public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm= (ListSelectionModel) e.getSource();
    contactPmB.setEnabled(!lsm.isSelectionEmpty());
    setPriceB.setEnabled(!lsm.isSelectionEmpty());
    
    
    }});
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        contactPmB = new javax.swing.JButton();
        setPriceB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        contactPmB.setText("contact pm");
        contactPmB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactPmBActionPerformed(evt);
            }
        });

        setPriceB.setText("set price");
        setPriceB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPriceBActionPerformed(evt);
            }
        });

        jLabel1.setText("choose an event then choose what you want to do");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setPriceB, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contactPmB, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactPmB, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setPriceB, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
    }//GEN-LAST:event_tableMouseClicked

    private void contactPmBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactPmBActionPerformed
               int row=table.getSelectedRow();
    User pm = new User();
        Event event = new Event() ;
   event.setId((int)table.getValueAt(row,0));
   pm.setId((int)table.getValueAt(row,3));
   new spcontactpm(user.getId(),pm.getId(),event.getId()).setVisible(true);
    }//GEN-LAST:event_contactPmBActionPerformed

    private void setPriceBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPriceBActionPerformed

        int row=table.getSelectedRow();
    Event event = new Event() ;
   event.setId((int)table.getValueAt(row,0));
   new priceSP(user.getId(),event.getId()).setVisible(true);


         
    }//GEN-LAST:event_setPriceBActionPerformed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(eventPrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eventPrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eventPrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eventPrice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new eventPrice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton contactPmB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton setPriceB;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}



