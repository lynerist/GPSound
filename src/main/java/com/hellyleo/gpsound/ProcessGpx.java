package com.hellyleo.gpsound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 *
 * @author Utente
 */
public class ProcessGpx implements ActionListener{

    final private Gpx gpx;
    
    public ProcessGpx(Gpx gpx) {
        this.gpx = gpx;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println(gpx.getName());
        } catch(Exception ec){
            System.out.println(gpx);
            System.out.println("Manca il File");
        }
    }
    
}
