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

    private final static JFileChooser fileChooser = new JFileChooser();
    private final static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder documentBuilder;
        
    public Gpx(){
        // --- File chooser ---
        fileChooser.addChoosableFileFilter(new FileFilter(){
            @Override
            public boolean accept(File file){
                return file.getName().endsWith(".gpx");
            }
            @Override
            public String getDescription() {
                return ".gpx";
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        // --- File Parser ---
        try {
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println(e);
        }           
    }
    
    public String getName(){
        return name;
    }

    private void setName(String name){
        this.name = name;
    }
    
    public boolean loadFile(Component view) throws SAXException, IOException{
        int fileLoaded = fileChooser.showDialog(view, "Load GPX");
        
        if (fileLoaded == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            setName(file.getName());           
            track = new Track(documentBuilder.parse(file));           
        }

        return fileLoaded == JFileChooser.APPROVE_OPTION;
    }   
    
    public Track getTrack(){
        return track;
    }
    
    public int getNumPoints(){
        return track.getNumPoints();
    }
}

