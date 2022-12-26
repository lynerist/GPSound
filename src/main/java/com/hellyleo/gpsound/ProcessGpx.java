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
            try {
                Player player = new Player(model.getGpx().getName());
                float counterTime = 0;
                float timeQuantum = (float)model.getSongDuration()/(float)model.getNumPoints();

                for (TrackPoint point : model.getGpx().getTrack()) {
                    player.SetStereo(deltaLongToStereo(point.getX()));                
                    player.SetFrequency((int) deltaLatToPitch(point.getY()));
                    player.SetAmplitude(deltaAltToAmp(point.getZ()));                    
                    player.synth.sleepFor(timeQuantum);
                    //System.out.println(player);
                    System.out.println(counterTime);
                    counterTime += timeQuantum;
                }
                
                player.stop();
                                
            } catch (Exception err) {
                System.out.println(err);
            }
            
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
        float[] maxDeltas = {2000,8000,12000,20000,100000}; //Sensibility values
        float maxDelta = maxDeltas[model.getSensibility()];

        float numberOfOctaves = 4;
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
            int baseFrequence = model.getStartPitch()/(int)Math.pow(2, (int)(delta/metersPerOctave));
            pitch = baseFrequence>0?(int)(baseFrequence - delta%metersPerOctave/metersPerOctave*(baseFrequence/2)):20;
        }
        
        return pitch>20000?20000:(pitch<20?20:pitch);       
    }
}
