/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.hellyleo.gpsound;

import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author Leo Helly
 */
public class GPSound extends javax.swing.JFrame {

    /**
     * Creates new form GPSound
     */
    
    private final Model model;
    
    public GPSound() {
        initComponents();
        model = new Model();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sensibilityGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        load = new javax.swing.JButton();
        fileName = new javax.swing.JLabel();
        process = new javax.swing.JButton();
        startPitch = new javax.swing.JLabel();
        startPitchSlider = new javax.swing.JSlider();
        startPitchValue = new javax.swing.JTextField();
        startStereo = new javax.swing.JLabel();
        startStereoSlider = new javax.swing.JSlider();
        startStereoValue = new javax.swing.JTextField();
        startAmp = new javax.swing.JLabel();
        startAmpSlider = new javax.swing.JSlider();
        startAmpValue = new javax.swing.JTextField();
        songDuration = new javax.swing.JLabel();
        seconds = new javax.swing.JLabel();
        songDurationValue = new javax.swing.JSpinner();
        sensibility_0 = new javax.swing.JRadioButton();
        sensibility_1 = new javax.swing.JRadioButton();
        sensibility_2 = new javax.swing.JRadioButton();
        sensibility_3 = new javax.swing.JRadioButton();
        sensibility_4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        load.setText("Load File");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });

        process.setText("Process");
        process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processActionPerformed(evt);
            }
        });

        startPitch.setText("Start Pitch");

        startPitchSlider.setMaximum(2000);
        startPitchSlider.setMinimum(20);
        startPitchSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        startPitchSlider.setPaintLabels(true);
        startPitchSlider.setToolTipText("");
        startPitchSlider.setValue(440);
        startPitchSlider.setName(""); // NOI18N
        startPitchSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startPitchSliderStateChanged(evt);
            }
        });

        startPitchValue.setText("440");
        startPitchValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPitchValueActionPerformed(evt);
            }
        });

        startStereo.setText("Start Stereophony");

        startStereoSlider.setMaximum(50);
        startStereoSlider.setMinimum(-50);
        startStereoSlider.setValue(0);
        startStereoSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startStereoSliderStateChanged(evt);
            }
        });

        startStereoValue.setText("0");
        startStereoValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startStereoValueActionPerformed(evt);
            }
        });

        startAmp.setText("Start Amplitude");

        startAmpSlider.setMaximum(200);
        startAmpSlider.setMinimum(1);
        startAmpSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        startAmpSlider.setPaintLabels(true);
        startAmpSlider.setToolTipText("");
        startAmpSlider.setValue(100);
        startAmpSlider.setName(""); // NOI18N
        startAmpSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startAmpSliderStateChanged(evt);
            }
        });

        startAmpValue.setText("100");
        startAmpValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAmpValueActionPerformed(evt);
            }
        });

        songDuration.setText("Song Duration");

        seconds.setText("s");

        songDurationValue.setModel(new javax.swing.SpinnerNumberModel(30, 1, 1800, 1));
        songDurationValue.setToolTipText("");
        songDurationValue.setRequestFocusEnabled(false);
        songDurationValue.setValue(30);
        songDurationValue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                songDurationValueStateChanged(evt);
            }
        });

        sensibilityGroup.add(sensibility_0);
        sensibility_0.setToolTipText("");
        sensibility_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensibility_0ActionPerformed(evt);
            }
        });

        sensibilityGroup.add(sensibility_1);
        sensibility_1.setToolTipText("");
        sensibility_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensibility_1ActionPerformed(evt);
            }
        });

        sensibilityGroup.add(sensibility_2);
        sensibility_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensibility_2ActionPerformed(evt);
            }
        });

        sensibilityGroup.add(sensibility_3);
        sensibility_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensibility_3ActionPerformed(evt);
            }
        });

        sensibilityGroup.add(sensibility_4);
        sensibility_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensibility_4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(379, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sensibility_3)
                            .addComponent(sensibility_0)
                            .addComponent(sensibility_1))
                        .addGap(212, 212, 212)
                        .addComponent(startPitchSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(startAmpSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(startStereoValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(startStereo)
                        .addGap(113, 113, 113))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 536, Short.MAX_VALUE)
                                .addComponent(startStereoSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(songDuration)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(startPitch)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(startAmp))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(startPitchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(startAmpValue, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(load)
                                .addGap(18, 18, 18)
                                .addComponent(fileName, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(process))
                        .addGap(143, 143, 143)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sensibility_2)
                            .addComponent(sensibility_4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(songDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(seconds, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(load)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fileName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(sensibility_0)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sensibility_1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sensibility_3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sensibility_2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sensibility_4)
                            .addComponent(process))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startPitch)
                            .addComponent(startAmp))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(startPitchSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(startAmpSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startPitchValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startAmpValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(songDuration, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seconds)
                    .addComponent(songDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(startStereo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(startStereoSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startStereoValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        try {                
                fileName.setText(model.getGpx().loadFile(this)?model.getGpx().getName():"");  
            } catch (SAXException | IOException ex) {
                System.err.println("Parse has failed");                
            }
    }//GEN-LAST:event_loadActionPerformed

    private void processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processActionPerformed
        ProcessGpx processGpx = new ProcessGpx(model);
        processGpx.actionPerformed(evt);
    }//GEN-LAST:event_processActionPerformed

    private void updateStartPitch(int n){
        model.setStartPitch(n);
        startPitchSlider.setValue(model.getStartPitch());
        startPitchValue.setText(String.valueOf(model.getStartPitch()));
    }
    
    private void startPitchSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startPitchSliderStateChanged
       updateStartPitch(startPitchSlider.getValue());   
    }//GEN-LAST:event_startPitchSliderStateChanged

    private void startPitchValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startPitchValueActionPerformed
        try{
            updateStartPitch(Integer.parseInt(startPitchValue.getText()));
        }catch (NumberFormatException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_startPitchValueActionPerformed

    private void updateStartStereo(double n){
        model.setStartStereo(n);
        startStereoSlider.setValue((int)((model.getStartStereo()-0.5)*100 +0.5*(model.getStartStereo()<0.5?-1:1)));
        startStereoValue.setText(String.valueOf((int)((model.getStartStereo()-0.5)*100 +0.5*(model.getStartStereo()<0.5?-1:1))));
    }
        
    private void startStereoValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startStereoValueActionPerformed
        try{
            updateStartStereo((double)Integer.parseInt(startStereoValue.getText())/100+0.5);   
        }catch (NumberFormatException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_startStereoValueActionPerformed

    private void startStereoSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startStereoSliderStateChanged
        updateStartStereo((double)startStereoSlider.getValue()/100+0.5);   
    }//GEN-LAST:event_startStereoSliderStateChanged

    private void updateStartAmp(double n){
        model.setStartAmp(n);
        startAmpSlider.setValue((int)(model.getStartAmp()*100+0.5));
        startAmpValue.setText(String.valueOf((int)(model.getStartAmp()*100+0.5)));
    }
    
    private void startAmpSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startAmpSliderStateChanged
        updateStartAmp((double)startAmpSlider.getValue()/100);   
    }//GEN-LAST:event_startAmpSliderStateChanged

    private void startAmpValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startAmpValueActionPerformed
        try{
            updateStartAmp((double)Integer.parseInt(startAmpValue.getText())/100);   
        }catch (NumberFormatException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_startAmpValueActionPerformed

    private void songDurationValueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_songDurationValueStateChanged
        updateSongDuration((int)songDurationValue.getValue());
    }//GEN-LAST:event_songDurationValueStateChanged

    private void sensibility_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensibility_0ActionPerformed
        model.setSensibility(0);
    }//GEN-LAST:event_sensibility_0ActionPerformed

    private void sensibility_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensibility_1ActionPerformed
        model.setSensibility(1);
    }//GEN-LAST:event_sensibility_1ActionPerformed

    private void sensibility_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensibility_3ActionPerformed
        model.setSensibility(2);
    }//GEN-LAST:event_sensibility_3ActionPerformed

    private void sensibility_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensibility_2ActionPerformed
        model.setSensibility(3);
    }//GEN-LAST:event_sensibility_2ActionPerformed

    private void sensibility_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensibility_4ActionPerformed
        model.setSensibility(4);
    }//GEN-LAST:event_sensibility_4ActionPerformed

    private void updateSongDuration(int n){
        model.setSongDuration(n);
        songDurationValue.setValue(n);
    }
    
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
            java.util.logging.Logger.getLogger(GPSound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GPSound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GPSound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GPSound.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GPSound().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fileName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton load;
    private javax.swing.JButton process;
    private javax.swing.JLabel seconds;
    private javax.swing.ButtonGroup sensibilityGroup;
    private javax.swing.JRadioButton sensibility_0;
    private javax.swing.JRadioButton sensibility_1;
    private javax.swing.JRadioButton sensibility_2;
    private javax.swing.JRadioButton sensibility_3;
    private javax.swing.JRadioButton sensibility_4;
    private javax.swing.JLabel songDuration;
    private javax.swing.JSpinner songDurationValue;
    private javax.swing.JLabel startAmp;
    private javax.swing.JSlider startAmpSlider;
    private javax.swing.JTextField startAmpValue;
    private javax.swing.JLabel startPitch;
    private javax.swing.JSlider startPitchSlider;
    private javax.swing.JTextField startPitchValue;
    private javax.swing.JLabel startStereo;
    private javax.swing.JSlider startStereoSlider;
    private javax.swing.JTextField startStereoValue;
    // End of variables declaration//GEN-END:variables
}
