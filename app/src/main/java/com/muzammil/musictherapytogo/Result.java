package com.muzammil.musictherapytogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

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
        Float resultval = Float.parseFloat(newResult);

        if(resultval > 0){
            result_txt.setText("You have reduced " + newResult + "% of your stress!");
            addInsight(newResult);
        }else{
            result_txt.setText("No stress has been reduced");
            addInsight("0.00");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

    }

    private void addInsight(String newResult){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        String key = ref.push().getKey();
        Map<String, Object> value = new HashMap<>();
        value.put("value", newResult);
        value.put("timestamp", ServerValue.TIMESTAMP);
        ref.child("insight").child(key).setValue(value);

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
