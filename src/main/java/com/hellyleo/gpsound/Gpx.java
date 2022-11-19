/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellyleo.gpsound;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Utente
 */

public class Gpx {
    
    private String name;
        
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public void loadFile(Component view){
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileFilter(){
            @Override
            public boolean accept(File file){
                return file.getName().endsWith(".gpx");
            }
            @Override
            public String getDescription() {
                return ".gpx";
            }
        });
        fc.setAcceptAllFileFilterUsed(false);
        
        
        int returnVal = fc.showDialog(view, "Load GPX");
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            setName(file.getName());
        }
    }

    public Gpx(String name) {
        this.name = name;
    }

    
}
