package com.example.testmvc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

public class Control {
    private TextView textV;
    private Button bt1,bt2,bt3;
    private Model model;
    String a;
    public Control(final TextView textV, Button bt1, Button bt2, Button bt3){
        this.textV = textV;
        this.bt1 = bt1;
        this.bt2 = bt2;
        this.bt3 = bt3;
        model = new Model();
        consult();
    }

    private void consult(){
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    a = model.getSubAndTeach();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textV.setText(a);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    a = model.getnumteach();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textV.setText(a);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    a = model.getsortting();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                textV.setText(a);
            }
        });
    }
}
