package com.example.onthimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NgheNhac extends AppCompatActivity {
    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnback3;
    private ServiceConnection serviceConnection;
    private boolean isConnected;
    private ServiceMusic musicPlayerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nghe_nhac);
        connectService();
        btnPlay =  findViewById(R.id.btnplay);
        btnPause = findViewById(R.id.btnstop);
        btnback3 = findViewById(R.id.btnBack3);
        btnback3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(NgheNhac.this,ListMusic.class));
                overridePendingTransition( R.anim.enter_y,R.anim.exit_y );
            }
        } );
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isConnected){
                    return;
                }
                boolean isPlaying = musicPlayerService.play();

                Toast.makeText(musicPlayerService, isPlaying ? "Playing music" : "Pause Music", Toast.LENGTH_SHORT).show();

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isConnected){
                    return;
                }
                boolean isPause = musicPlayerService.pause();

                Toast.makeText(musicPlayerService, isPause ? "Pause Music" : "Playing Music", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void connectService() {

        Intent intent = new Intent(this, ServiceMusic.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                ServiceMusic.MyServiceBinder myBinder = (ServiceMusic.MyServiceBinder) service;

                musicPlayerService = myBinder.getService();
                isConnected = true;
                Toast.makeText(musicPlayerService, "Connected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isConnected = false;
                Toast.makeText(musicPlayerService, "Disconnected", Toast.LENGTH_SHORT).show();
                musicPlayerService = null;

            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
}