package com.diplabels.soundtest;

public class ItemView {
    public double getAmp() {
        return amp;
    }

    public void setAmp(double amp) {
        this.amp = amp;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public double getPhase() {
        return phase;
    }

    public void setPhase(double phase) {
        this.phase = phase;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    double amp; int freq; double phase;
    int image;
    ItemView( int image,double amp, int freq, double phase){
        this.amp = amp;
        this.image = image;
        this.freq = freq;
        this.phase = phase;


    }
}
