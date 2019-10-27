package com.muzammil.musictherapytogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity implements View.OnClickListener {

    private Button btn_main_page, btn_insight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btn_main_page = findViewById(R.id.button_main_about);
        btn_main_page.setOnClickListener(this);

        btn_insight = findViewById(R.id.button_insight_about);
        btn_insight.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.button_main_about):{
                startActivity(new Intent(About.this, MainActivity.class));
                break;
            }
            case(R.id.button_insight_about):{
                startActivity(new Intent(About.this, Insight.class));
                break;
            }
        }
    }
}
