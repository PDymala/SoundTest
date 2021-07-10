package com.diplabels.soundtest;

import com.diplabels.soundtest.android.JSynAndroidAudioDevice;
import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.scope.AudioScope;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;

import java.util.ArrayList;

/**
 * Play independent sine waves on the left and right channel.
 */
public class SineSynth {


    ArrayList<SineOscillator> sineOscillatorArrayList = new ArrayList<>();
    private Synthesizer mSynth;
    private LineOut mLineOut;// stereo output

    public void deleteWave(int a){
       // mSynth.remove(sineOscillatorArrayList.get(a));
        sineOscillatorArrayList.get(a).noteOff();
        sineOscillatorArrayList.remove(a);


    }

    //przy del dodac   mSynth = JSyn.createSynthesizer( new JSynAndroidAudioDevice() );
//    albo jakos inaczej aby kasowało ten istniejacy

    private void runOscillator() {

        stopAll();

        // Create the unit generators and add them to the synthesizer.

        for (SineOscillator sO : sineOscillatorArrayList){
            mSynth.add(sO);
        }
        startAll();

    }

    /**
    @param freq 20hz - 20.000hz - for human. Depends if possible on speakers
     @param phase ?
     @param amp 0 - 1  (-1 - 1 )?

     */
    public void addWave(double amp, int freq, double phase){
        SineOscillator rOsc1 = new SineOscillator();


        rOsc1.output.connect(0, mLineOut.input, 0);
        rOsc1.output.connect(0, mLineOut.input, 1);

        rOsc1.amplitude.set(amp);
        rOsc1.frequency.set(freq);
        rOsc1.phase.set(phase);


        sineOscillatorArrayList.add(rOsc1);

    }


    public SineSynth() {
        init();
    }
    public void init(){

        // Create a JSyn synthesizer that uses the Android output.
        mSynth = JSyn.createSynthesizer( new JSynAndroidAudioDevice() );
        mLineOut = new LineOut();
        mSynth.add(mLineOut);


        // swing'owy element
//        AudioScope  scope = new AudioScope(mSynth);
//      ]
//        scope.addProbe( osc1.output ); // display multiple signals
//        scope.addProbe( osc2.output );
//// Trigger on a threshold level vs AUTO trigger.
//        scope.setTriggerMode( AudioScope.TriggerMode.NORMAL );
//        scope.start();

    }

    public void startAll() {
        for (SineOscillator sO : sineOscillatorArrayList){
            mSynth.add(sO);
        }
        mSynth.start();
        mLineOut.start();
    }

    public void stopAll() {
        mLineOut.stop();
        mSynth.stop();
    }







}
