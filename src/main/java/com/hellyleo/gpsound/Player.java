package com.hellyleo.gpsound;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterHighShelf;
import com.jsyn.unitgen.PinkNoise;
import com.jsyn.unitgen.RedNoise;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Player {
    public File outputFile;
    private final WaveRecorder recorder;
    private FilterHighShelf filter;
    
    private FilterBandPass filterNoise;

    private Channel leftChannel;
    private Channel rightChannel;

    
    double amplitudeLeft;
    double amplitudeRight;
    int frequency;


    public Synthesizer synth;

    private class Channel{
        private UnitOscillator osc;
        private SineOscillator sine;
        private RedNoise red;
        private final UnitOscillator[] components;
        private boolean isRight;

        
        public Channel(Synthesizer synth, WaveRecorder recorder, boolean isRight){
            this.isRight = isRight;
            synth.add(osc = new SquareOscillator());
            osc.output.connect(0, recorder.getInput(), isRight?1:0);
            
            //synth.add(sineLeft = new SineOscillator());
            //synth.add(redLeft = new RedNoise());
            //redLeft.amplitude.set(0.4);
            
            components = new UnitOscillator[]{osc};
        }
        
        public void SetFrequency(int frequency){
            Arrays.stream(this.components).forEach((x)->x.frequency.set(frequency));
            //filterNoise.frequency.set(frequency);
        }
        
        public void SetStereo(double stereo){
            //osc.amplitude.set(isRight?stereo:1-stereo);
            if (stereo>0 && !isRight){
                //Latency??????   
            }
            //sineLeft.amplitude.set(1-stereo);
        }
    }
    
    public Player(String fileName,double amplitude) throws FileNotFoundException{
        System.out.println(fileName);
        outputFile = new File(fileName.split("\\.")[0] + ".wav");
        amplitudeLeft = 0.5f;
        amplitudeRight = 0.5f;        
        frequency = 440;
                
        synth = JSyn.createSynthesizer();
        recorder = new WaveRecorder(synth, outputFile); 
        
        leftChannel = new Channel(synth, recorder, false);
        rightChannel = new Channel(synth, recorder, true);
        
        
        //synth.add(filter = new FilterHighShelf());
        //filter.amplitude.set(amplitude);
        
        /*PinkNoise pink = new PinkNoise();
        filterNoise = new FilterBandPass();
        pink.output.connect(filterNoise.getInput());
        filterNoise.amplitude.set(0.75);
        synth.add(filterNoise);
        filterNoise.output.connect(recorder.getInput());*/
        
               
        synth.start();
        recorder.start();
        
    }
    
    public void SetFrequency(int frequency){
        leftChannel.SetFrequency(frequency);
        rightChannel.SetFrequency(frequency);

        //filterNoise.frequency.set(frequency);
        this.frequency = frequency;
    }
    
    public void SetStereo(double stereo){
        leftChannel.SetStereo(stereo);
        rightChannel.SetStereo(stereo);
    }
    
    public void stop() throws IOException{
        recorder.stop();
        recorder.close();
        synth.stop();
    }
    
    @Override
    public String toString(){
        return "pitch: " + frequency + " stereo: " + amplitudeLeft ;
    }
}
