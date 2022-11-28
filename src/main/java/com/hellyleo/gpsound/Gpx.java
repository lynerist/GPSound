/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellyleo.gpsound;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Gpx {
   
    private String name = "";
    private Track track; 

    private final static JFileChooser fc = new JFileChooser();
    private final static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder db;
        
    public Gpx(){
        // --- File chooser ---
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
        
        // --- File Parser ---
        
        try {
            dbf.setIgnoringElementContentWhitespace(true);
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println(e);
        }           
    }
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public boolean loadFile(Component view) throws SAXException, IOException{
     
        int returnVal = fc.showDialog(view, "Load GPX");
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            setName(file.getName());
           
            track = new Track(db.parse(file));           
            
        }

        return returnVal == JFileChooser.APPROVE_OPTION;
    }   
    
    public String printNext(){
        return track.next().toString();
    }
}

