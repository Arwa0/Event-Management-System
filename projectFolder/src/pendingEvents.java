 



import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class pendingEvents extends javax.swing.JFrame {
DefaultTableModel dtn=new DefaultTableModel();
      
    
    public pendingEvents() {
    try { 
        
       
        initComponents();
        table.setModel(dtn);
        dtn.addColumn("Event_ID");
        dtn.addColumn("Event Name");
        dtn.addColumn("customer_ID");
        dtn.addColumn("loction");
        String sql="select id,ename,customer_id,elocation from event2 where estatus_id=1";
            ResultSet res=Db.query2(sql);
        while(res.next())
            //get data row row from database
         dtn.addRow(new Object[] {res.getInt("id"),res.getString("ename"),res.getInt("customer_id"),res.getString("elocation")});
        
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "SQLException error! ","Error",JOptionPane.ERROR_MESSAGE);
    }
    
    
    //to prevent error when no event is selected
        
            accept.setEnabled(false);
    reject.setEnabled(false);
    ListSelectionModel listSelectionModel = table.getSelectionModel();
    listSelectionModel.addListSelectionListener(new ListSelectionListener(){public void valueChanged(ListSelectionEvent e){
    ListSelectionModel lsm= (ListSelectionModel) e.getSource();
    accept.setEnabled(!lsm.isSelectionEmpty());
    reject.setEnabled(!lsm.isSelectionEmpty());
    
    
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
        accept = new javax.swing.JButton();
        reject = new javax.swing.JButton();
        back = new javax.swing.JButton();

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
        table.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(table);

        accept.setText("accept");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        reject.setText("reject");
        reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectActionPerformed(evt);
            }
        });

        back.setText("back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(reject, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(accept, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(reject, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tableAncestorAdded
       
    }//GEN-LAST:event_tableAncestorAdded

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
    try {
        int row=table.getSelectedRow();
        int id=(int)table.getValueAt(row,0);
        String sql=" update event2 set estatus_id=2 where id='"+id+"'";
        Db.update2(sql);
        selectPM sPM=new selectPM(id);
        sPM.setVisible(true);
        this.dispose();
        } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "sql server not found ","Error",JOptionPane.ERROR_MESSAGE); 
    }
    }//GEN-LAST:event_acceptActionPerformed

    private void rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectActionPerformed
    try {
        int row=table.getSelectedRow();
        int id=(int)table.getValueAt(row,0);
        String sql=" update event2 set estatus_id=3 where id='"+id+"'";
        int result=Db.update2(sql);
        if (result==1)
            JOptionPane.showMessageDialog(null,"Event has been rejected successfuly","success",JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "sql exception error!","error",JOptionPane.ERROR_MESSAGE);
    }
                     
    }//GEN-LAST:event_rejectActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        adminPage ap=new adminPage();
        ap.setSize(400,400);
        ap.setLocation(400,200);
        ap.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backActionPerformed

   
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
            java.util.logging.Logger.getLogger(pendingEvents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pendingEvents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pendingEvents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pendingEvents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pendingEvents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JButton back;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reject;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
