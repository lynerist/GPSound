package com.hellyleo.gpsound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class DomainTranspositor implements ActionListener{
    
    final private Model model;
    
    static private final int WALK_MAX_DELTA = 1000;
    static private final int HIKE_MAX_DELTA = 4000;
    static private final int MOUNTAINBIKE_MAX_DELTA = 6000;
    static private final int BIKE_MAX_DELTA = 10000;
    static private final int VEHICLE_MAX_DELTA = 50000;
    static private final int MAX_DELTA_BY_SENSIBILITY [] = {WALK_MAX_DELTA,
                                                    HIKE_MAX_DELTA, 
                                                    MOUNTAINBIKE_MAX_DELTA, 
                                                    BIKE_MAX_DELTA, 
                                                    VEHICLE_MAX_DELTA};
    
    public DomainTranspositor(Model model) {
        this.model = model;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!model.isFileLoaded()) {
            System.out.println("Manca il File");
        } else {
            try {
                Player player = new Player(model.getGpx().getName(), model.getAmplitude());              
                float counterTime = 0;
                double timeQuantum = (double)model.getSongDuration()/(double)model.getNumPoints();
                
                double startStereo = (double)model.getStartStereo();
                Instant start = Instant.now();
                int count = 0;
                boolean isBehind = false;
                for (TrackPoint point : model.getGpx().getTrack()) {
                    double actualStereo = startStereo + deltaLongToStereo(point.getX());
                    player.SetStereo(actualStereo>1?1:(actualStereo<0?0:actualStereo));                
                    player.SetFrequency(point.getZ());

                    double actualBackCutFreq = deltaLatToCutoffFrequency(point.getY());                    
                    if (actualBackCutFreq > 0){
                        player.SetCutoffFrequency(actualBackCutFreq);
                    }
                    
                    if (!isBehind && actualBackCutFreq < 0 || isBehind && actualBackCutFreq >= 0){
                       isBehind = !isBehind;
                       player.SetQBehind(isBehind);
                    }
                    
                    Instant finish = Instant.now();
                    
                    if (Duration.between(start, finish).toMillis()<= counterTime*1000){
                        player.synth.sleepFor(timeQuantum);                    
                    }
                    
                    if ((int)(counterTime)>count){
                        System.out.println((int)(counterTime*100/model.getSongDuration()));
                        count++;
                        model.advanceProgressBar((int)(counterTime*100/model.getSongDuration()));
                    }
                    counterTime += timeQuantum;
                }
                model.advanceProgressBar(100);
                player.stop();
                model.enableInput();
                                
            } catch (IOException | InterruptedException err) {
                System.out.println(err);
            }
        } 
    }
    
    public float deltaLongToStereo(int delta){
        float maxDelta = MAX_DELTA_BY_SENSIBILITY[model.getSensibility()];
        float stereo = (float)(delta/(maxDelta*2)+0.5);      
        return stereo>0.99f?0.99f:(stereo<0.1f?0.1f:stereo);
    }
    
    public float deltaLatToCutoffFrequency(int delta){
        if (delta > 0) return 0;
        
        float maxDelta = MAX_DELTA_BY_SENSIBILITY[model.getSensibility()] * -1;

        float minCutOffFreq = 100;
        float maxCutOffFreq = 6000;
        
        return maxCutOffFreq - (maxCutOffFreq-minCutOffFreq)*(delta/maxDelta<1?delta/maxDelta:1);            
    }
}
