 package com.oguzhanakduman.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;

 public class MainActivity extends AppCompatActivity {
    // Ses kullanmak için ses oynatma objesi
    MediaPlayer player;
    // Sayacın ekranda azaldığını göstermek için kullanılan textview
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textView id eşleştirmesi
        textView = findViewById(R.id.textView);

        // Sayaç oluşturma (milisInFuture: kaçıncı milisecond'dan sayacın başlayacağı, countDownInterval: kaçar milisecond olarak azalacağı)
        new CountDownTimer(20000,1000){

            // Sayaç her countDownInterval kadar azaldığında çalışacak olan fonksiyon
            @Override
            public void onTick(long l) {
                textView.setText("Timer: " + l / 1000);
            }

            // Sayaç bittiğinde çalışacak olan fonksiyon
            @Override
            public void onFinish() {
                stop();
                textView.setText("Timer is Finished");
                Toast.makeText(getApplicationContext(),"Timer is Finished!",Toast.LENGTH_LONG);
                play(R.raw.clock_finish);
            }
        }.start();
    }

    // Sesi başlatmak için kullanılan play fonksiyonu
    public void play(int resId){
        if(player == null){
            player = MediaPlayer.create(this, resId);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    // Sesi duraklatmak için kullanılabilecek olan pause fonksiyonu
    public void pause(){
        if(player != null){
            player.pause();
        }
    }

    // Sesi durdurmak ve sıfırlamak için kullanılan stop fonksiyonu
    public void stop(){
        stopPlayer();
    }

    private void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
            Toast.makeText(this,"MediaPlayer relased", Toast.LENGTH_LONG).show();
        }
    }

     @Override
     protected void onStop() {
         super.onStop();
         stopPlayer();
     }
 }