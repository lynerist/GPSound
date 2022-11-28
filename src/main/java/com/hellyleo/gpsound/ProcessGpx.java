package com.hellyleo.gpsound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Utente
 */
public class ProcessGpx implements ActionListener{

    final private Gpx gpx;
    private int startPitch;
    
    public ProcessGpx(Gpx gpx) {
        this.gpx = gpx;
        this.startPitch = 440;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( "".equals(gpx.getName())) {
            System.out.println("Manca il File");
        } else {
            System.out.println(gpx.getName());
            System.out.println(gpx.printNext());
        } 
    }
    
}
