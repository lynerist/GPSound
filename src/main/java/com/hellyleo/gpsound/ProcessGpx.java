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
            //for (int i = 0; i < 100; i++) {
                TrackPoint next = model.getGpx().getNext();
                System.out.print(next+ " ");
                System.out.println(deltaLongToStereo(next.getX()));
                System.out.println(deltaLatToPitch(next.getY()));
                System.out.println(deltaAltToAmp(next.getZ()));

            //}
        } 
    }
    
    public float deltaLongToStereo(int delta){
        float[] maxDeltas = {1000,4000,6000,10000,50000}; //Sensibility values
        float maxDelta = maxDeltas[model.getSensibility()];
        float stereo = (float)(delta/(maxDelta*2)+0.5);      
        return stereo>1?1:(stereo<0?0:stereo);
    }
    
    public float deltaAltToAmp(int delta){
        float maxDelta = 2000;
        float amp = 1+(float)(delta/(maxDelta*2));
        return amp>2?2:(amp<0?0:amp);       
    }
    
    public float deltaLatToPitch(int delta){
        float[] maxDeltas = {1000,4000,6000,10000,50000}; //Sensibility values
        float maxDelta = maxDeltas[model.getSensibility()];

        int numberOfOctaves = 6;
        float metersPerOctave = maxDelta/numberOfOctaves;
        
        // (delta/metersPerOctave) -> number of octave jumps from startpitch 
        // (delta%metersPerOctave) reminder of the delta after the octave jumps
        //delta%metersPerOctave/baseFrequence percentage of octave to add 
        int pitch = 0;
        if (delta>=0){
            
            int baseFrequence = model.getStartPitch()*(int)Math.pow(2, (int)(delta/metersPerOctave));
            pitch = (int) (baseFrequence + delta%metersPerOctave/metersPerOctave*baseFrequence);        
        }else{
            delta *= -1;
            System.out.println("delta -> " + delta);
            int baseFrequence = model.getStartPitch()/(int)Math.pow(2, (int)(delta/metersPerOctave));
            System.out.println("baseFrequence -> " + baseFrequence);

            pitch = baseFrequence>0?(int)(baseFrequence - delta%metersPerOctave/metersPerOctave*(baseFrequence/2)):20;
        }
        
        return pitch>20000?20000:(pitch<20?20:pitch);       
    }
}
