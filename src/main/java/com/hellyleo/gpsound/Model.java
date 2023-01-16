/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hellyleo.gpsound;


/**
 *
 * @author Utente
 */
public class Model{
    private final Gpx gpx;
    private int startPitch; //20-20000
    private double startStereo; //-0.5 - 0.5
    private double startAmp; // 0.0 - 2.0
    private int songDuration;
    private int sensibility;

    public Model() {
        gpx = new Gpx();
        startPitch = 440;
        startStereo = 0;
        startAmp = 1.0;
        songDuration = 20;
        sensibility = 1; // 0:Walk, 1:Hike, 2:Mountain Bike, 3:Racing Bike, 4:Vehicle  
    }
    
    public Gpx getGpx(){
        return gpx;
    }
    
    public void setStartPitch(int n){
        startPitch = n;
    }
    public int getStartPitch(){
        return startPitch;
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
}
