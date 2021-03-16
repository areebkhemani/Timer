package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView countdownTextview;
    SeekBar timerSeekbar;
    Button goButton;
    CountDownTimer countDownTimer;
    Boolean counterIsAcctive = false;

    public void reset(){
        timerSeekbar.setProgress(30);
        countdownTextview.setText("0:30");
        timerSeekbar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsAcctive = false;

    }

    public void onClick(View view) {
        if (counterIsAcctive)
        {
          reset();

        }
        else {

            counterIsAcctive = true;
            goButton = findViewById(R.id.Gobutton);
            goButton.setText("STOP!");
            countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    timerSeekbar.setEnabled(false);
                    generateCounter((int) l / 1000);


                }

                @Override
                public void onFinish() {
                    MediaPlayer horn = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    horn.start();
                    reset();


                }
            }.start();
        }
    }

    public void generateCounter(int time){
        int minutes = time / 60;
        int seconds = time % 60;
        String SecondString =Integer.toString(seconds) ;
        if(seconds <=9)
        {
            SecondString = "0" + seconds;
        }
        countdownTextview.setText(Integer.toString(minutes)+ ":" + SecondString);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar = findViewById(R.id.timerSeekBar);
         countdownTextview = findViewById(R.id.timerTextView);

        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        timerSeekbar.setMin(1);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                generateCounter(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

}