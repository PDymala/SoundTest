package com.diplabels.soundtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chibde.visualizer.LineVisualizer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonAdd;
    Button buttonStart;
    Button buttonStop;
    EditText edittextAddAmplitude;
    EditText edittextAddFrequency;
    EditText edittextAddPhase;
    RecyclerView mRecyclerView;
    adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ItemView> waveListItem = new ArrayList<>();
    SineSynth ss;
    Thread sineThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();
        init();




    }

    private void permission() {
        //sound visualizer
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.RECORD_AUDIO
                    },
                    100);

        }


    }

    public void init(){
        sineThread = new Thread(() -> ss = new SineSynth());
        sineThread.start();
        //Memory leak here. Not stopping thread enywhere

        buttonAdd = findViewById(R.id.buttonAddWAve);
        buttonStart = findViewById(R.id.button3start);
        buttonStop = findViewById(R.id.buttonstop);

        edittextAddAmplitude = findViewById(R.id.editTextAddAmp);
                edittextAddFrequency = findViewById(R.id.editTextAddFreq);
        edittextAddPhase = findViewById(R.id.editTextAddPhase);
        edittextAddPhase.setText(Double.toString(0.0));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new adapter(waveListItem);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListerner(new adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ss.stopAll();
                waveListItem.remove(position);
                ss.deleteWave(position);
                mAdapter.notifyItemRemoved(position);
                ss.startAll();
            }
        });
    }


    public void startButton(View v){
        ss.startAll();
    }
    public void stopButton(View v){
        ss.stopAll();
    }

    public void addWave(View v){
        ss.stopAll();
        ss.addWave(Double.parseDouble(String.valueOf(edittextAddAmplitude.getText())), Integer.parseInt(String.valueOf(edittextAddFrequency.getText())), Double.parseDouble(String.valueOf(edittextAddPhase.getText())));
        waveListItem.add(new ItemView(R.drawable.ic_baseline_audiotrack_24,Double.parseDouble(String.valueOf(edittextAddAmplitude.getText())), Integer.parseInt(String.valueOf(edittextAddFrequency.getText())), Double.parseDouble(String.valueOf(edittextAddPhase.getText()))));
       mAdapter.notifyItemInserted(waveListItem.size()+1);
        ss.startAll();
    }
    protected void onPause(){
        ss.stopAll();
        super.onPause();

    }
    @Override
    protected void onResume(){
        ss.stopAll();
        super.onResume();

    }
    @Override
    protected void onDestroy(){
        ss.stopAll();
        //stop thread
        super.onDestroy();

    }


}