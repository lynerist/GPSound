package com.hellyleo.gpsound;

import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JProgressBar;

public class Model{
    private final Gpx gpx;
    private double startStereo; //-0.5 - 0.5
    private double amplitude; // 0.0 - 2.0
    private int songDuration;
    private int sensibility;
    private final JProgressBar progressBar;
    private final JComponent[] inputParameters;

    public Model(JProgressBar progressbar, JComponent[] inputComponents) {
        // --- Progress visualization ---
        progressBar = progressbar;
        progressBar.setStringPainted(true);
        inputParameters = inputComponents; //needed to disable components during computation
        
        // --- Variable Inizialization ---
        gpx = new Gpx();
        startStereo = 0;
        amplitude = 1.0;
        songDuration = 20;
        sensibility = 1; // 0:Walk, 1:Hike, 2:Mountain Bike, 3:Bike, 4:Vehicle  
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
    
    public void setAmplitude(double startAmp) {
        this.amplitude = startAmp;
    }
    
    public double getAmplitude() {
        return amplitude;
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
        Arrays.stream(this.inputParameters).forEach((x)->x.setEnabled(false));
    }
    
    public void enableInput(){
        Arrays.stream(this.inputParameters).forEach((x)->x.setEnabled(true));
    }
    
    public boolean isFileLoaded(){
        return !"".equals(getGpx().getName());
    }
}
