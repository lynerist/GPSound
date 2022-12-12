package com.hellyleo.gpsound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Utente
 */
public class ProcessGpx implements ActionListener{

    final private Model model;
    
    public ProcessGpx(Model model) {
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( "".equals(model.getGpx().getName())) {
            System.out.println("Manca il File");
        } else {
            TrackPoint next = model.getGpx().getNext();
            System.out.print(next+ " ");
            System.out.println(deltaLongToStereo(model.getSensibility(), next.getX()));
        } 
    }
    
    public float deltaLongToStereo(int sensibility, int delta){
        float[] maxDeltas = {1000,4000,5000,10000,50000}; //Sensibility values
        float maxDelta = maxDeltas[sensibility];
        return (float)(delta/(maxDelta*2)+0.5);       
    }
}
