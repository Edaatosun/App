package com.eda.layoutapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    Runnable runnable;
    Handler handler;
    int number;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView2);
        textView2 = findViewById(R.id.textView4);
        startButton = findViewById(R.id.button5);

        Intent intent = getIntent();
        int age = intent.getIntExtra("age",0);
        textView.setText("age:"+age);

        number = 0;

    }


    public void changeScreen(View view){
        //alert
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Warning");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //transition
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
        alert.show();

    }

    public void start(View view){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                textView2.setText("Time"+number);
                number++;
                textView2.setText("time:"+number);

                handler.postDelayed(runnable,1000);

            }
        };
        handler.post(runnable);
        startButton.setEnabled(false);


    }

    public void finish(View view){
        startButton.setEnabled(true);
        handler.removeCallbacks(runnable);
        number = 0;
        textView2.setText("Time:"+number);


    }
}