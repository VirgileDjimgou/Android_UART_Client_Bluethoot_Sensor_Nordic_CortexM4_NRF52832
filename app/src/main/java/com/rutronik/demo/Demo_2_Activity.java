package com.rutronik.demo;

/**
 * Created by virgile on 21.12.2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.rutronik.demo.R;
import com.rutronik.gps.GPSTracker;
import com.rutronik.gps.GPS_Temp_Press_Activity;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.rutronik.demo.R.color.disabled;

public class Demo_2_Activity extends Activity{
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();
    int i = 0;


    // Implementation
    public static Button L1 , L2 , L3, L4 , L5 , L6 ;
    public static  SeekBar RotarySensor ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.demo_2);

        RotarySensor = (SeekBar) findViewById(R.id.seekBar_Rot);
        L1 = (Button) findViewById(R.id.Led_1);
        L2 = (Button) findViewById(R.id.Led_2);
        L3 = (Button) findViewById(R.id.Led_3);
        L4 = (Button) findViewById(R.id.Led_4);
        L5 = (Button) findViewById(R.id.Led_5);
        L6 = (Button) findViewById(R.id.Led_6);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //onResume we start our timer so it can start when the app comes from the background
        startTimer();
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1000, 1000); //
    }

    public void stoptimertask(View v) {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        // create class object
                        animation_2();


                    }
                });
            }
        };
    }



    public  static  void animation_1(){

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        L1.setBackgroundColor(color);

        rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        L2.setBackgroundColor(color);


        rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        L3.setBackgroundColor(color);


        rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        L4.setBackgroundColor(color);


        rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        L5.setBackgroundColor(color);


        rnd = new Random();
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        L6.setBackgroundColor(color);


    }

    public void animation_2 (){


         Reset();
           if(i==0){ L1.setBackgroundColor(getResources().getColor(R.color.Red));}

           if(i==1){        L2.setBackgroundColor(getResources().getColor(R.color.Red));
           }
           if(i==2){        L3.setBackgroundColor(getResources().getColor(R.color.Red));
           }
           if(i==3){        L4.setBackgroundColor(getResources().getColor(R.color.Red));
           }
           if(i==4){        L5.setBackgroundColor(getResources().getColor(R.color.Red));
           }
           if(i==5){        L6.setBackgroundColor(getResources().getColor(R.color.Red));
           }
         // Retour

            if(i==7){        L6.setBackgroundColor(getResources().getColor(R.color.Red));}

            if(i==8){        L5.setBackgroundColor(getResources().getColor(R.color.Red));
            }
            if(i==9){        L4.setBackgroundColor(getResources().getColor(R.color.Red));
            }
            if(i==10){        L3.setBackgroundColor(getResources().getColor(R.color.Red));
            }
            if(i==11){        L2.setBackgroundColor(getResources().getColor(R.color.Red));
            }
            if(i== 12){        L1.setBackgroundColor(getResources().getColor(R.color.Red));
                               i = 0; // Reset von Compteur
            }

         i++;

    }

    public void Reset(){
        L1.setBackgroundColor(getResources().getColor(R.color.disabled));
        L2.setBackgroundColor(getResources().getColor(R.color.disabled));
        L3.setBackgroundColor(getResources().getColor(R.color.disabled));
        L4.setBackgroundColor(getResources().getColor(R.color.disabled));
        L5.setBackgroundColor(getResources().getColor(R.color.disabled));
        L6.setBackgroundColor(getResources().getColor(R.color.disabled));

    }
}
