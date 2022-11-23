/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.hellyleo.gpsound;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Leonardo Albani & Helene Korsten
 */
public class GPSound {
    
    final private Gpx gpx;

    public GPSound() {
        gpx = new Gpx();
        buildInterface();
    }
        
    private JFrame buildInterface(){
        // --- All the page ---
        
        JFrame view = new JFrame("GPSound");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(300,200);
        view.setLayout(new GridLayout(2, 1));
        //view.pack();
        
        
        // --- Upper part --- Load File
        
        JPanel upperPart = new JPanel();
        view.add(upperPart);
        
        JButton loadGpx = new JButton("LoadGpx");
        loadGpx.setSize(100,30);
        upperPart.add(loadGpx);
        
        JLabel nameFile = new JLabel();
        view.add(nameFile);
        
        loadGpx.addActionListener((ActionEvent e) -> {
            try {
                if (gpx.loadFile(view)){   
                    nameFile.setText(gpx.getName());
                }
            } catch (SAXException | IOException ex) {
                System.err.println("Parse has failed");
                Logger.getLogger(GPSound.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // --- Lower part ---
        
        JPanel lowerPart = new JPanel();
        view.add(lowerPart);
        
        JButton process = new JButton("Process");
        process.setSize(100,30);
        process.addActionListener(new ProcessGpx(gpx));
        lowerPart.add(process);
        
        
        
        view.setVisible(true);
        
        return view;
    }

    public static void main(String[] args) {
        new GPSound();
        
    }
}
