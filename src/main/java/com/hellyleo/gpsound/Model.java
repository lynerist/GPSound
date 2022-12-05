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
    private int startPitch;
    private double startStereo;
    private double startAmp;

    public Model() {
        gpx = new Gpx();
        startPitch = 440;
        startStereo = 0.5;
        startAmp = 1.0;
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
}
