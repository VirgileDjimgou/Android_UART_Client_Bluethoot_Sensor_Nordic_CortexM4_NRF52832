package com.rutronik.gps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;


import com.rutronik.demo.MainActivity;
import com.rutronik.demo.R;

import java.io.UnsupportedEncodingException;

public class GPS_Temp_Press_Activity extends Activity {
	Timer timer;
	TimerTask timerTask;

	public static Button Initialise , Mitte , Max;
	public static TextView Latitude_Text , Longitude_Text ;
	//we are going to use a handler to be able to run in our TimerTask
	final Handler handler = new Handler();

	private SeekBar seekBar_Temp;
	private TextView Temp_view;

	private SeekBar seekBar_Pressure;
	private TextView Pressure_View;

	double latitude ;
	double longitude ;


	// Initialise Accelerometer


	// GPSTracker class
	GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_1);

		seekBar_Temp = (SeekBar) findViewById(R.id.seekBar_temp);
		Temp_view = (TextView) findViewById(R.id.TempView);
		Temp_view.setText("0");
		seekBar_Temp.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
				progress = progresValue;
				// Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Temp_view.setText("Fake Temperatur : " + progress + "/" + seekBar.getMax());
				String VarLong = "T"+Double.toString(progress);
				byte[] value = new byte[0];
				try {

					value = VarLong.getBytes("UTF-8");

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				MainActivity.mService.writeRXCharacteristic(value);
			}
		});

		seekBar_Pressure = (SeekBar) findViewById(R.id.seekBar_pressure);
		Pressure_View = (TextView) findViewById(R.id.PressureView);
		Pressure_View.setText("0");
		seekBar_Pressure.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;

			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
				progress = progresValue;
				// Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Pressure_View.setText("Fake Pressure " + progress + "/" + seekBar.getMax());
				String VarLong = "P"+Double.toString(progress);
				byte[] value = new byte[0];
				try {

					value = VarLong.getBytes("UTF-8");

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				MainActivity.mService.writeRXCharacteristic(value);
			}
		});



		Latitude_Text = (TextView) findViewById(R.id.Latitude);
		Longitude_Text = (TextView) findViewById(R.id.Long);
        Initialise= (Button) findViewById(R.id.Initialise);



		Initialise.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// \n is for new line
		        	Toast.makeText(getApplicationContext(), "Alle Gauge Initialisieren ... " , Toast.LENGTH_LONG).show();

					String message = "R";
					byte[] value = new byte[0];
					try {
						value = message.getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					MainActivity.mService.writeRXCharacteristic(value);


			}
		});

		Mitte= (Button) findViewById(R.id.Mitte);


		Mitte.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// \n is for new line
				Toast.makeText(getApplicationContext(), " alle Gauge in der Mitte Value (50) setzen  ..... ", Toast.LENGTH_LONG).show();

				String message = "Z";
				byte[] value = new byte[0];
				try {
					value = message.getBytes("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				MainActivity.mService.writeRXCharacteristic(value);


			}
		});

		Max= (Button) findViewById(R.id.Max);



		Max.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// \n is for new line
				Toast.makeText(getApplicationContext(), "alle Gauge in Max Value (100)  setzen ....." , Toast.LENGTH_LONG).show();

				String message = "M";
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
		timer.schedule(timerTask, 2000, 2000); //
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
						gps = new GPSTracker(GPS_Temp_Press_Activity.this);
						// check if GPS enabled
						if(gps.canGetLocation()) {

							latitude = gps.getLatitude();
							longitude = gps.getLongitude();
						}else {

							// can't get location
							// GPS or Network is not enabled
							// Ask user to enable GPS/network in settings
							gps.showSettingsAlert();
						}

						byte[] value = new byte[0];

                        String VarLat = "L"+Double.toString(latitude);
						try {
							// value = "Echohuhuhzz".getBytes("UTF-8");
							value = VarLat.getBytes("UTF-8");

						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						MainActivity.mService.writeRXCharacteristic(value);


						String VarLong = "A"+Double.toString(longitude);
						try {
							// value = "Echohuhuhzz".getBytes("UTF-8");
							value = VarLong.getBytes("UTF-8");

						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						MainActivity.mService.writeRXCharacteristic(value);

						Latitude_Text.setText("");
						Latitude_Text.setText("Latitude GPS Sensor Value : "+ Double.toString(latitude));
						Longitude_Text.setText("");
						Longitude_Text.setText("Longiude GPS Sensor Value : " + Double.toString(longitude));


					}
				});
			}
		};
	}


    
}