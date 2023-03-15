package com.hellyleo.gpsound;

import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

public class Model{
    private final Gpx gpx;
    private double startStereo; //-0.5 - 0.5
    private double startAmp; // 0.0 - 2.0
    private int songDuration;
    private int sensibility;
    private final JProgressBar progressBar;
    private final JComponent[] variInput;

    public Model(JProgressBar progress, JComponent[] ui) {
        progressBar = progress;
        variInput = ui;
        progress.setStringPainted(true);
        gpx = new Gpx();
        startStereo = 0;
        startAmp = 1.0;
        songDuration = 20;
        sensibility = 1; // 0:Walk, 1:Hike, 2:Mountain Bike, 3:Racing Bike, 4:Vehicle  
    }
    
    public Gpx getGpx(){
        return gpx;
    }
    
    public void setStartStereo(double startStereo) {
        this.startStereo = startStereo;
    }
    
    public double getStartStereo() {
        return startStereo;
    }  
    
    public void setStartAmp(double startAmp) {
        this.startAmp = startAmp;
    }
    
    public double getStartAmp() {
        return startAmp;
    }    

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }
    
    public int getSongDuration() {
        return songDuration;
    }

    public void setSensibility(int sensibility) {
        this.sensibility = sensibility;
    }
    
    public int getSensibility() {
        return sensibility;
    }    
    
    public int getNumPoints(){
        return gpx.getNumPoints();
    }
    
    public void advanceProgressBar(int progress){
        progressBar.setValue(progress);
    }
    
    public void disableInput(){
         Arrays.stream(this.variInput).forEach((x)->x.setEnabled(false));
    }
    
    public void enableInput(){
         Arrays.stream(this.variInput).forEach((x)->x.setEnabled(true));
    }
}
