package com.hellyleo.gpsound;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.Delay;
import com.jsyn.unitgen.FilterBandPass;
import com.jsyn.unitgen.FilterBiquad;
import com.jsyn.unitgen.FilterHighShelf;
import com.jsyn.unitgen.FilterPeakingEQ;
import com.jsyn.unitgen.InterpolatingDelay;
import com.jsyn.unitgen.PinkNoise;
import com.jsyn.unitgen.RedNoise;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitOscillator;
import com.jsyn.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Arrays;


public class Player {
    public File outputFile;
    private final WaveRecorder recorder;
    private FilterPeakingEQ filter;
    
    private FilterBandPass filterNoise;

    private Channel leftChannel;
    private Channel rightChannel;

    
    double amplitudeLeft;
    double amplitudeRight;
    int frequency;


    public Synthesizer synth;
    
    
    private class MyInterpolatingDelay extends UnitFilter{
        public UnitInputPort delay;
        private float[] buffer;
        private int cursor;
        private int numFrames;

        public MyInterpolatingDelay() {
            addPort(delay = new UnitInputPort("Delay"));
        }

       
        public void allocate(int numFrames) {
            this.numFrames = numFrames;
            // Allocate extra frame for guard point to speed up interpolation.
            buffer = new float[numFrames + 1];
        }


        @Override
        public void generate(int start, int limit) {
            double[] inputs = input.getValues();
            double[] outputs = output.getValues();
            double[] delays = delay.getValues();

            for (int i = start; i < limit; i++) {
                // This should be at the beginning of the loop
                // because the guard point should == buffer[0].
                if (cursor >= numFrames) {
                    // Write guard point! Must allocate one extra sample.
                    buffer[numFrames] = (float) inputs[i];
                    cursor = 0;
                }

                float x = (float) inputs[i];
                buffer[cursor] = x;

                /* Convert delay time to a clipped frame offset. */
                double delayFrames = delays[i] * getFrameRate();

                // Clip to zero delay.
                if (delayFrames <= 0.0) {
                    outputs[i] = buffer[cursor];
                } else {
                    // Clip to maximum delay.
                    if (delayFrames >= numFrames) {
                        delayFrames = numFrames - 1;
                    }

                    // Calculate fractional index into delay buffer.
                    double readIndex = cursor - delayFrames;
                    if (readIndex < 0.0) {
                        readIndex += numFrames;
                    }
                    // setup for interpolation.
                    // We know readIndex is > 0 so we do not need to call floor().
                    int iReadIndex = (int) readIndex;
                    double frac = readIndex - iReadIndex;

                    // Get adjacent values relying on guard point to prevent overflow.
                    double val0 = buffer[iReadIndex];
                    double val1 = buffer[iReadIndex + 1];

                    // Interpolate new value.
                    outputs[i] = val0 + (frac * (val1 - val0));
                }
                cursor += 1;
            }
        }
    }
    
    private class Channel{
        private UnitOscillator osc;
        
        private RedNoise red;
        private final UnitOscillator[] components;
        private boolean isRight;
        private MyInterpolatingDelay squareMicroDelay;
        
        private boolean isDelayed;
        private double actualStereo;
        
        private SineOscillator SineToSmoothTimbre(){
            SineOscillator sine;
            synth.add(sine = new SineOscillator());
            sine.output.connect(0, squareMicroDelay.getInput(), 0);
            return sine;
        }
                
        public Channel(Synthesizer synth, WaveRecorder recorder,FilterBiquad filter, boolean isRight){
            this.isRight = isRight;
            synth.add(osc = new SquareOscillator());
            synth.add(squareMicroDelay = new MyInterpolatingDelay());
            
            osc.output.connect(0, squareMicroDelay.getInput(), 0);
       
            //synth.add(redLeft = new RedNoise());
            //redLeft.amplitude.set(0.4);
                  
            squareMicroDelay.output.connect(0, filter.getInput(), 0);
            
            
            filter.output.connect(0, recorder.getInput(), isRight?1:0);
            
            squareMicroDelay.allocate(8);
            
            components = new UnitOscillator[]{osc, SineToSmoothTimbre(),SineToSmoothTimbre()};
        }
        
        public void SetFrequency(int frequency){
            Arrays.stream(this.components).forEach((x)->x.frequency.set(frequency));
            //filterNoise.frequency.set(frequency);
        }
        
        public void SetStereo(double stereo){
            //diminuire lo spostamento? no direi di no
            /*stereo-=0.5;
            stereo*=0.5;
            stereo+=0.5;*/
            osc.amplitude.set(isRight?stereo:1-stereo);
            if (stereo>0 && !isRight || stereo<0 && isRight){
                if (abs(stereo-actualStereo) > 0.1){
                    actualStereo = stereo;
                    isDelayed = true;
                    squareMicroDelay.allocate(((int)((abs(actualStereo)/0.1)))*2000);    
                }
            }else if (isDelayed){
                actualStereo = 0;
                squareMicroDelay.allocate(8);
                isDelayed = false;
            }
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
        
        synth.add(filter = new FilterPeakingEQ());
        filter.amplitude.set(amplitude);
        filter.gain.set(0.001);
        
        leftChannel = new Channel(synth, recorder, filter, false);
        rightChannel= new Channel(synth, recorder, filter, true);
        
        
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
        filter.frequency.set(frequency);

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
