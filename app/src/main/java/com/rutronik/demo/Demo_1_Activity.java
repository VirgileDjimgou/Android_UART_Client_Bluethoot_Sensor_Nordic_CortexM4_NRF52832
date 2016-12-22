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
import android.widget.Toast;
import com.rutronik.demo.R;

public class Demo_1_Activity extends Activity {
    private Button Demo_butt_1, Demo_Butt_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.demo_1);


    }
}
