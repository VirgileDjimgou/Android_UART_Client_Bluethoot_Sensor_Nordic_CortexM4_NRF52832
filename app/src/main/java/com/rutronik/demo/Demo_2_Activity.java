package com.rutronik.demo;

/**
 * Created by virgile on 21.12.2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class Demo_2_Activity extends Activity{


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

    public  static  void animation_1(){


    }

    public static void animation_2 (){



    }
}
