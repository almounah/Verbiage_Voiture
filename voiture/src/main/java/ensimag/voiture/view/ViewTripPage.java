/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ensimag.voiture.view;

import ensimag.voiture.controller.ButtonHandler;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author almounah
 */
public class ViewTripPage extends javax.swing.JFrame {

    /**
     * Creates new form ViewTripPage
     */
    public ViewTripPage() {
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

        depCityLab2 = new javax.swing.JLabel();
        arrCityLab2 = new javax.swing.JLabel();
        correspondanceLab = new javax.swing.JLabel();
        bookButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tripPriceLab = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tripIndex = new javax.swing.JTextField();
        totaltrips = new javax.swing.JTextField();
        depCityLabel1 = new javax.swing.JLabel();
        arrCityLab1 = new javax.swing.JLabel();
        nextButt = new javax.swing.JButton();
        prevButt = new javax.swing.JButton();
        arrowLab1 = new javax.swing.JLabel();
        passedByCityBox1 = new javax.swing.JComboBox<>();
        passesByLab1 = new javax.swing.JLabel();
        depTime1 = new javax.swing.JTextField();
        arrTime1 = new javax.swing.JTextField();
        arrowLab2 = new javax.swing.JLabel();
        passesByLab2 = new javax.swing.JLabel();
        depTime2 = new javax.swing.JTextField();
        arrTime2 = new javax.swing.JTextField();
        passedByCityBox2 = new javax.swing.JComboBox<>();
        validateButt1 = new javax.swing.JButton();
        validateButt2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        depCityLab2.setText("DepCity");

        arrCityLab2.setText("ArrCity");

        correspondanceLab.setFont(new java.awt.Font("Cantarell", 2, 14)); // NOI18N
        correspondanceLab.setText("1 Correspondance");

        bookButton.setText("Cancel Trip");
        bookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        jLabel3.setText("Search Result");

        tripPriceLab.setEditable(false);

        jLabel13.setText("Price :");

        tripIndex.setEditable(false);
        tripIndex.setText("0");

        totaltrips.setEditable(false);
        totaltrips.setText("/0");

        depCityLabel1.setText("DepCity");

        arrCityLab1.setText("ArrCity");

        nextButt.setText("Next Trip");
        nextButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtActionPerformed(evt);
            }
        });

        prevButt.setText("Previous Trip");
        prevButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtActionPerformed(evt);
            }
        });

        arrowLab1.setText("------------------------------------->");

        passesByLab1.setText("Passes by 4 cities");

        depTime1.setEditable(false);

        arrTime1.setEditable(false);

        arrowLab2.setText("------------------------------------->");

        passesByLab2.setText("Passes by 4 cities");

        depTime2.setEditable(false);

        arrTime2.setEditable(false);

        validateButt1.setText("Validate and Pay");

        validateButt2.setText("Validate and Pay");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(tripIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(depCityLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(depCityLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totaltrips, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arrowLab1)
                                .addGap(18, 18, 18)
                                .addComponent(arrCityLab1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passesByLab2)
                                    .addComponent(passesByLab1)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(arrowLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arrCityLab2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(depTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(passedByCityBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(arrTime1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(182, 182, 182)
                                            .addComponent(passedByCityBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(depTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 50, Short.MAX_VALUE)
                                        .addComponent(arrTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tripPriceLab, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(validateButt2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(validateButt1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prevButt)
                        .addGap(119, 119, 119)
                        .addComponent(bookButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButt)
                        .addGap(26, 26, 26))))
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(correspondanceLab, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tripIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totaltrips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(passesByLab1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(depCityLabel1)
                    .addComponent(arrCityLab1)
                    .addComponent(arrowLab1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passedByCityBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrTime1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validateButt1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(correspondanceLab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passesByLab2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(depCityLab2)
                    .addComponent(arrCityLab2)
                    .addComponent(arrowLab2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nextButt)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passedByCityBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(depTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(arrTime2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validateButt2))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(prevButt)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(tripPriceLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(bookButton)
                                .addContainerGap())))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookButtonActionPerformed

    private void nextButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtActionPerformed
        // TODO add your handling code here:
        ButtonHandler.notifyShowNextUpcomingTrip(true, this);
    }//GEN-LAST:event_nextButtActionPerformed

    private void prevButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtActionPerformed
        // TODO add your handling code here:
        ButtonHandler.notifyShowNextUpcomingTrip(false, this);

    }//GEN-LAST:event_prevButtActionPerformed

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
            java.util.logging.Logger.getLogger(ViewTripPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTripPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTripPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTripPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTripPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arrCityLab1;
    private javax.swing.JLabel arrCityLab2;
    private javax.swing.JTextField arrTime1;
    private javax.swing.JTextField arrTime2;
    private javax.swing.JLabel arrowLab1;
    private javax.swing.JLabel arrowLab2;
    private javax.swing.JButton bookButton;
    private javax.swing.JLabel correspondanceLab;
    private javax.swing.JLabel depCityLab2;
    private javax.swing.JLabel depCityLabel1;
    private javax.swing.JTextField depTime1;
    private javax.swing.JTextField depTime2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton nextButt;
    private javax.swing.JComboBox<String> passedByCityBox1;
    private javax.swing.JComboBox<String> passedByCityBox2;
    private javax.swing.JLabel passesByLab1;
    private javax.swing.JLabel passesByLab2;
    private javax.swing.JButton prevButt;
    private javax.swing.JTextField totaltrips;
    private javax.swing.JTextField tripIndex;
    private javax.swing.JTextField tripPriceLab;
    private javax.swing.JButton validateButt1;
    private javax.swing.JButton validateButt2;
    // End of variables declaration//GEN-END:variables

    public JLabel getArrCityLab1() {
        return arrCityLab1;
    }

    public JLabel getArrCityLab2() {
        return arrCityLab2;
    }

    public JTextField getArrTime1() {
        return arrTime1;
    }

    public JTextField getArrTime2() {
        return arrTime2;
    }

    public JLabel getArrowLab1() {
        return arrowLab1;
    }

    public JLabel getArrowLab2() {
        return arrowLab2;
    }

    public JButton getBookButton() {
        return bookButton;
    }

    public JLabel getCorrespondanceLab() {
        return correspondanceLab;
    }

    public JLabel getDepCityLab2() {
        return depCityLab2;
    }

    public JLabel getDepCityLabel1() {
        return depCityLabel1;
    }

    public JTextField getDepTime1() {
        return depTime1;
    }

    public JTextField getDepTime2() {
        return depTime2;
    }

    public JButton getjButton1() {
        return validateButt1;
    }

    public JButton getjButton2() {
        return validateButt2;
    }

    public JLabel getjLabel13() {
        return jLabel13;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public JButton getNextButt() {
        return nextButt;
    }

    public JComboBox<String> getPassedByCityBox1() {
        return passedByCityBox1;
    }

    public JComboBox<String> getPassedByCityBox2() {
        return passedByCityBox2;
    }

    public JLabel getPassesByLab1() {
        return passesByLab1;
    }

    public JLabel getPassesByLab2() {
        return passesByLab2;
    }

    public JButton getPrevButt() {
        return prevButt;
    }

    public JTextField getTotaltrips() {
        return totaltrips;
    }

    public JTextField getTripIndex() {
        return tripIndex;
    }

    public JTextField getTripPriceLab() {
        return tripPriceLab;
    }
    
}
