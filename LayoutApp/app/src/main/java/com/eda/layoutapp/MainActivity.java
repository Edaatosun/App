package com.eda.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText number;
    TextView result;
    SharedPreferences sharedPreferences;

    TextView counter;

    Button buton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        System.out.println("şuann oncreatteyimm");

        number = findViewById(R.id.editTextNumber);
        result = findViewById(R.id.textView1);
        counter = findViewById(R.id.textView3);
        buton = findViewById(R.id.button3);

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                counter.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                buton.setEnabled(false);
                Toast.makeText(MainActivity.this,"Finished",Toast.LENGTH_LONG);
            }
        }.start();

        // shared preferences

         sharedPreferences = this.getSharedPreferences("com.eda.layoutapp", Context.MODE_PRIVATE);

         int storedAge = sharedPreferences.getInt("storedAge",0);
         if(storedAge==0){
             result.setText("your age:");
         }else{
             result.setText("your age:"+storedAge);
         }

    }

    public void save(View view){
        int number1 = Integer.parseInt(number.getText().toString());
        result.setText("Your age = "+number1);

        sharedPreferences.edit().putInt("storedAge",number1).apply();
    }

    public void delete(View view){
        int storedAge = sharedPreferences.getInt("storedAge",0);

        if(storedAge!=0){
            sharedPreferences.edit().remove("storedAge").apply();
            result.setText("silindii");
        }
    }

    public void changeScreen(View view){
        int storedAge = sharedPreferences.getInt("storedAge",0);
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        if(storedAge==0){
            int age = Integer.parseInt(number.getText().toString());

            intent.putExtra("age",age);
        }
        else {
            intent.putExtra("age",storedAge);
        }
        startActivity(intent);

    }






}