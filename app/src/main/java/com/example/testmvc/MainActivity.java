package com.example.testmvc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textV;
    Button bt1,bt2,bt3;
    Control control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textV = findViewById(R.id.textV);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        control = new Control(textV,bt1,bt2,bt3);
//        String a = String.valueOf(R.raw.database);
//        Toast.makeText(MainActivity.this,a, Toast.LENGTH_SHORT).show();
    }
}
