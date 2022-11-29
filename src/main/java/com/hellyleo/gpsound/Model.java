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

    public Model() {
        gpx = new Gpx();
        startPitch = 440;
    }
    
    public Gpx getGpx(){
        return gpx;
    }
    
    public void setstartPitch(int n){
        startPitch = n;
    }
    public int getstartPitch(){
        return startPitch;
    }
    
}
