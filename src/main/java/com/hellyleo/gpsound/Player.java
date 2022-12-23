package com.hellyleo.gpsound;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.Pan;
import com.jsyn.unitgen.SawtoothOscillatorBL;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player {
    public File outputFile;
    private WaveRecorder recorder;
    private UnitOscillator oscLeft;
    private UnitOscillator oscRight;
    float amplitude;
    float amplitudeLeft;
    float amplitudeRight;


    public Synthesizer synth;
    
    public Player(String fileName) throws FileNotFoundException{
        System.out.println(fileName);
        outputFile = new File(fileName.split("\\.")[0] + ".wav");
        amplitude = 1;
        amplitudeLeft = 0.5f;
        amplitudeRight = 0.5f;
        
        

        synth = JSyn.createSynthesizer();
        synth.add(oscLeft = new SawtoothOscillatorBL());
        synth.add(oscRight = new SawtoothOscillatorBL());

        recorder = new WaveRecorder(synth, outputFile);      
        oscLeft.output.connect(0, recorder.getInput(), 0);
        oscRight.output.connect(0, recorder.getInput(), 1);
        
        synth.start();
        recorder.start();
        
    }
    
    public void SetFrequency(int frequency){
        oscLeft.frequency.set(frequency);
        oscRight.frequency.set(frequency);

    }
    public void SetAmplitude(float amplitude){
        this.amplitude = amplitude;
        oscLeft.amplitude.set(amplitudeLeft*amplitude);
        oscRight.amplitude.set(amplitudeRight*amplitude);
    }
    public void SetStereo(float stereo){
        amplitudeLeft = 1-stereo;
        amplitudeRight = stereo;
        SetAmplitude(amplitude);
    }
    
    public void stop() throws IOException{
        recorder.stop();
        recorder.close();
        synth.stop();
    }
}
