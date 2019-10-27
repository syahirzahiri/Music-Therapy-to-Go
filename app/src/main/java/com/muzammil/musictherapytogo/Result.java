package com.muzammil.musictherapytogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity implements View.OnClickListener {

    private Button btn_new_session;
    private TextView result_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btn_new_session = findViewById(R.id.button_new_session);
        btn_new_session.setOnClickListener(this);

        result_txt = findViewById(R.id.result_text);
        String newResult = getIntent().getStringExtra("result");
        result_txt.setText("You have reduced " + newResult + "% of your stress!");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_new_session:{
                startActivity(new Intent(Result.this,MainActivity.class));
                finish();
            }
        }
    }
}
