package com.maulanaabdulsiddiq.myuas2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_uas;
    private Button button_haji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_uas = findViewById(R.id.btn_uas);
        button_haji = findViewById(R.id.btn_haji);

        button_uas.setOnClickListener(this);
        button_haji.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button_uas){
            startActivity(new Intent(this,UasActivity.class));
        }

        if(v == button_haji){
            startActivity(new Intent(this,HajiActivity.class));
        }
    }
}
