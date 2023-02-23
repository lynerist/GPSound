package com.hellyleo.gpsound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;

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
                Player player = new Player(model.getGpx().getName(), model.getStartAmp());              
                float counterTime = 0;
                double timeQuantum = (double)model.getSongDuration()/(double)model.getNumPoints();
                
                double startStereo = (double)model.getStartStereo();
                Instant start = Instant.now();
                int count = 0;
                for (TrackPoint point : model.getGpx().getTrack()) {
                    double actualStereo = startStereo + deltaLongToStereo(point.getX());
                    player.SetStereo(actualStereo>1?1:(actualStereo<0?0:actualStereo));                
                    player.SetFrequency(point.getZ());
                    
                    Instant finish = Instant.now();
                    
                    if (Duration.between(start, finish).toMillis()<= counterTime*1000){
                        player.synth.sleepFor(timeQuantum);                    
                    }
                    
                    if ((int)(counterTime)>count){
                        System.out.println(counterTime);
                        count++;
                    }
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
        return stereo>0.99f?0.99f:(stereo<0.1f?0.1f:stereo);
    }
    
    public float deltaLatToPitch(int delta){
        float[] maxDeltas = {2000,8000,12000,20000,100000}; //Sensibility values
        float maxDelta = maxDeltas[model.getSensibility()];

        float numberOfOctaves = 3;
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
