package com.hellyleo.gpsound;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.FilterLowPass;
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
   
    private final Channel leftChannel;
    private final Channel rightChannel;

    
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
        private SineOscillator SineToSmoothTimbre(){
            SineOscillator sine;
            synth.add(sine = new SineOscillator());
            sine.output.connect(0, microDelay.getInput(), 0);            
            return sine;
        }     
        private class Harmonic{
            double coeff, amp;
            SineOscillator s;
            public Harmonic(double n, double a){
                this.amp = a;
                this.coeff = n;
                this.s = SineToSmoothTimbre();
                s.amplitude.set(a);
            }
            public void setFrequency(double freq){
                this.s.frequency.set(freq*coeff);
            }
            public void setStereo(double amp){
                //System.out.println("a="+ this.amp*amp);
                this.s.amplitude.set(this.amp*amp);
            }
        }
                        
        private SineOscillator osc;
        
        private final UnitOscillator[] components;
        private final Harmonic[] harmonics;
        private final boolean isRight;
        private final MyInterpolatingDelay microDelay;
        private final FilterLowPass filter;
        
        private boolean isDelayed;
        private double actualStereo;
        
        public Channel(Synthesizer synth, WaveRecorder recorder, boolean isRight){
            this.isRight = isRight;
            
            synth.add(osc = new SineOscillator());            

            synth.add(microDelay = new MyInterpolatingDelay());
            
            osc.output.connect(0, microDelay.getInput(), 0);
            
            synth.add(filter = new FilterLowPass());
            filter.output.connect(0, recorder.getInput(), isRight?1:0);
            filter.Q.set(1);
            filter.frequency.set(6000);
            
            microDelay.output.connect(0, filter.getInput(), 0);
                                    
            microDelay.allocate(8);
         
            
            
            components = new UnitOscillator[]{osc};
            harmonics = new Harmonic[]{ new Harmonic(0.5, 0.4),
                                        new Harmonic(0.25, 0.3),
                                        new Harmonic(2, 0.38),
                                        new Harmonic(3, 0.75),
                                        new Harmonic(4, 0.38),
                                        new Harmonic(5, 0.155),
                                        new Harmonic(6, 0.18),
                                        new Harmonic(7, 0.205),
                                        new Harmonic(9, 0.09)
                                        };
        }
        
        public void SetFrequency(int frequency){
            Arrays.stream(this.components).forEach((x)->x.frequency.set(frequency));
            Arrays.stream(this.harmonics).forEach((x)->x.setFrequency(frequency));
        }
        
        public void SetStereo(double stereo){
            osc.amplitude.set(isRight?stereo:1-stereo);
            //System.out.println(isRight?stereo:1-stereo);
            
            //micro delay
            if (stereo>0 && !isRight || stereo<0 && isRight){
                if (abs(stereo-actualStereo) > 0.1){
                    actualStereo = stereo;
                    isDelayed = true;
                    microDelay.allocate(((int)((abs(actualStereo)/0.1)))*2000);    
                }
            }else if (isDelayed){
                actualStereo = 0;
                microDelay.allocate(8);
                isDelayed = false;
            }
            
            Arrays.stream(this.harmonics).forEach((x)->x.setStereo(isRight?stereo:1-stereo));
        }
        
        public void SetCutoffFrequency(double cutOff){
            filter.frequency.set(cutOff);
        }

        public void SetQBehind(boolean b){
            filter.Q.set(b?0.2:1);
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
        rightChannel= new Channel(synth, recorder, true);
               
               
        synth.start();
        recorder.start();
        
    }
    
    public void SetFrequency(int frequency){
        leftChannel.SetFrequency(frequency);
        rightChannel.SetFrequency(frequency);
        //filter.frequency.set(frequency);

        //filterNoise.frequency.set(frequency);
        this.frequency = frequency;
    }
    
    public void SetStereo(double stereo){
        leftChannel.SetStereo(stereo);
        rightChannel.SetStereo(stereo);
    }
    
    public void SetQBehind(boolean b) {
        leftChannel.SetQBehind(b);
        rightChannel.SetQBehind(b);
    }
    
    public void SetCutoffFrequency(double cutoffFrequency){
        leftChannel.SetCutoffFrequency(cutoffFrequency);
        rightChannel.SetCutoffFrequency(cutoffFrequency);
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
