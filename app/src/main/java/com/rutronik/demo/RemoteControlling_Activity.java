package com.rutronik.demo;

/**
 * Created by virgile on 21.12.2016.
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class RemoteControlling_Activity extends Activity {
    private Button play, pause , home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.remote_video);

        play = (Button) findViewById(R.id.Play);
        pause = (Button) findViewById(R.id.Pause);
        home = (Button) findViewById(R.id.Home);




        play.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // \n is for new line
                Toast.makeText(getApplicationContext(), "Play The  Video  ....." , Toast.LENGTH_LONG).show();

                String message = "X";
                byte[] value = new byte[0];
                try {
                    value = message.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                MainActivity.mService.writeRXCharacteristic(value);


            }
        });


        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // \n is for new line
                Toast.makeText(getApplicationContext(), "stop the Video ...." , Toast.LENGTH_LONG).show();

                String message = "Y";
                byte[] value = new byte[0];
                try {
                    value = message.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                MainActivity.mService.writeRXCharacteristic(value);


            }
        });

        home .setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // \n is for new line
                Toast.makeText(getApplicationContext(), "Back to Home Menu ....." , Toast.LENGTH_LONG).show();

                String message = "H";
                byte[] value = new byte[0];
                try {
                    value = message.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                MainActivity.mService.writeRXCharacteristic(value);


            }
        });


    }
}
