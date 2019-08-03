package finalproject;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ordersJFrame extends javax.swing.JFrame {
    public ordersJFrame() {
        initComponents();
        
        setDefaultCloseOperation(coffeeJFrame.HIDE_ON_CLOSE);
        
        final String CONN_STRING = "jdbc:derby://localhost:1527/dbCoffeeStoreData";
        try
        {
            Connection conn = DriverManager.getConnection(CONN_STRING, "root", "root");
            Statement comm = conn.createStatement();
            ResultSet getCustomers = comm.executeQuery("SELECT lastName, firstName FROM customer");
            List<String> customerNames = new LinkedList<>();           
            while(getCustomers.next())
            {
                customerNames.add(getCustomers.getString(1) + ", " + getCustomers.getString(2));
            }            
            jLCustomers.setModel(new javax.swing.AbstractListModel<String>() {
                List<String> strings = customerNames;
            public int getSize() { return strings.size(); }
            public String getElementAt(int i) { return strings.get(i); }
            });
            listPane.setViewportView(jLCustomers);
        }
        catch (Exception ex)
        {
            System.out.println("Something went wrong .. " + ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        listPane = new javax.swing.JScrollPane();
        jLCustomers = new javax.swing.JList();
        btnGetTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Last Name", "First Name", "Coffee", "How Many", "Total"
            }
        ));
        tablePane.setViewportView(jTable1);

        jLCustomers.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jLCustomers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listPane.setViewportView(jLCustomers);

        btnGetTable.setText("Find Orders");
        btnGetTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listPane)
                    .addComponent(btnGetTable, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(listPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGetTable)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetTableActionPerformed
        final String CONN_STRING = "jdbc:derby://localhost:1527/dbCoffeeStoreData";
        try
        {
            Connection conn = DriverManager.getConnection(CONN_STRING, "root", "root");
            Statement comm = conn.createStatement();
            String[] names = jLCustomers.getSelectedValue().toString().split(", ");
            ResultSet getCustomers = comm.executeQuery("SELECT customer.lastName, customer.firstName, coffee.coffeeName, "
                                                     + "orders.numberOrdered, orders.total FROM customer JOIN orders ON "
                                                     + "orders.customerID=customer.customerID JOIN coffee ON "
                                                     + "coffee.coffeeID=orders.coffeeID WHERE customer.lastName='" + names[0]
                                                     + "' AND customer.firstName='" + names[1] + "'");
            while(jTable1.getRowCount() > 0) 
            {
                ((DefaultTableModel) jTable1.getModel()).removeRow(0);
            }
            int columns = getCustomers.getMetaData().getColumnCount();
            while(getCustomers.next())
            {
                Object[] row = new Object[columns];
                for (int x = 0; x < columns; x++)
                {  
                    row[x] = getCustomers.getObject(x + 1);
                }
                ((DefaultTableModel) jTable1.getModel()).insertRow(getCustomers.getRow()-1,row);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Something went wrong .. " + ex.getMessage());
        }
    }//GEN-LAST:event_btnGetTableActionPerformed
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
            java.util.logging.Logger.getLogger(ordersJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ordersJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ordersJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ordersJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ordersJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetTable;
    private javax.swing.JList jLCustomers;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane listPane;
    private javax.swing.JScrollPane tablePane;
    // End of variables declaration//GEN-END:variables
}
