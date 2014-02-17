/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import enterpriseclient.ClientProtocol;

/**
 *
 * @author jaspertomas
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FormMain
     */
    public FrmMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmdLogout = new javax.swing.JButton();
        cmdPurchaseOrder = new javax.swing.JButton();
        cmdInvoice = new javax.swing.JButton();
        cmdDSR = new javax.swing.JButton();
        cmdAccountsReceivable = new javax.swing.JButton();
        cmdTest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("TMC Program");

        cmdLogout.setText("Logout");
        cmdLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLogoutActionPerformed(evt);
            }
        });

        cmdPurchaseOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/po.jpg"))); // NOI18N
        cmdPurchaseOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPurchaseOrderActionPerformed(evt);
            }
        });

        cmdInvoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inv.jpg"))); // NOI18N
        cmdInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdInvoiceActionPerformed(evt);
            }
        });

        cmdDSR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dsr.jpg"))); // NOI18N
        cmdDSR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDSRActionPerformed(evt);
            }
        });

        cmdAccountsReceivable.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        cmdAccountsReceivable.setText("Accounts Receivable");
        cmdAccountsReceivable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAccountsReceivableActionPerformed(evt);
            }
        });

        cmdTest.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        cmdTest.setText("Test");
        cmdTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTestActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(cmdLogout)
                .add(25, 25, 25))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cmdAccountsReceivable)
                            .add(layout.createSequentialGroup()
                                .add(cmdPurchaseOrder)
                                .add(18, 18, 18)
                                .add(cmdInvoice)
                                .add(18, 18, 18)
                                .add(cmdDSR))
                            .add(cmdTest))))
                .addContainerGap(443, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cmdPurchaseOrder)
                    .add(cmdInvoice)
                    .add(cmdDSR))
                .add(33, 33, 33)
                .add(cmdAccountsReceivable, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cmdTest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 18, Short.MAX_VALUE)
                .add(cmdLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLogoutActionPerformed
        ClientProtocol.sendExit();
        setVisible(false);
        FormManager.getInstance().getFrmLogin().setVisible(true);
    }//GEN-LAST:event_cmdLogoutActionPerformed

    private void cmdPurchaseOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPurchaseOrderActionPerformed
        //setVisible(false);
        FormManager.getInstance().getFrmPurchaseOrder().setLocation(200, 200);
        FormManager.getInstance().getFrmPurchaseOrder().setVisible(true);
    }//GEN-LAST:event_cmdPurchaseOrderActionPerformed

    private void cmdInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdInvoiceActionPerformed
        //setVisible(false);
        FormManager.getInstance().getFrmInvoice().setLocation(200, 200);
        FormManager.getInstance().getFrmInvoice().setVisible(true);
    }//GEN-LAST:event_cmdInvoiceActionPerformed

    private void cmdDSRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDSRActionPerformed
        //setVisible(false);
        FormManager.getInstance().getFrmDSR().setLocation(200, 200);
        FormManager.getInstance().getFrmDSR().setVisible(true);
    }//GEN-LAST:event_cmdDSRActionPerformed

    private void cmdAccountsReceivableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAccountsReceivableActionPerformed
        //setVisible(false);
        FormManager.getInstance().getFrmAccountsReceivable().setLocation(200, 200);
        FormManager.getInstance().getFrmAccountsReceivable().setVisible(true);
    }//GEN-LAST:event_cmdAccountsReceivableActionPerformed

    private void cmdTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTestActionPerformed
        ClientProtocol.sendDbSelect("AccountsReceivable"," 1 limit 1");
    }//GEN-LAST:event_cmdTestActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAccountsReceivable;
    private javax.swing.JButton cmdDSR;
    private javax.swing.JButton cmdInvoice;
    private javax.swing.JButton cmdLogout;
    private javax.swing.JButton cmdPurchaseOrder;
    private javax.swing.JButton cmdTest;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
