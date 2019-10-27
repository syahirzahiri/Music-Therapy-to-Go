package com.muzammil.musictherapytogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MainActivity";
    private Button btn_main_page, btn_insight, btn_about, btn_get_reading, btn_get_final, btn_play, btn_stop;
    private EditText initial_txt, final_txt;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_page = findViewById(R.id.button_main);
        btn_insight = findViewById(R.id.button_insight);
        btn_about = findViewById(R.id.button_about);

        btn_get_reading = findViewById(R.id.button_get_init);
        btn_get_reading.setOnClickListener(this);
        btn_get_final = findViewById(R.id.button_get_final);
        btn_get_final.setOnClickListener(this);

        btn_play = findViewById(R.id.button_start);
        btn_play.setOnClickListener(this);
        btn_stop = findViewById(R.id.button_stop);
        btn_stop.setOnClickListener(this);



        initial_txt = findViewById(R.id.initial_reading);
        final_txt = findViewById(R.id.final_reading);

        mediaPlayer = MediaPlayer.create(this, R.raw.ignite);




    }

    private void getInit(){

        DatabaseReference refInit = FirebaseDatabase.getInstance().getReference("reading");
        refInit.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mInitial = dataSnapshot.child("initial").getValue().toString();
                initial_txt.setText(mInitial);
                btn_get_final.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getFinal() {
        DatabaseReference refInit = FirebaseDatabase.getInstance().getReference("reading");
        refInit.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mFinal = dataSnapshot.child("final").getValue().toString();
                final_txt.setText(mFinal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showResult(){
        startActivity(new Intent(MainActivity.this, Result.class));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_get_init: {
                getInit();
                break;
            }
            case R.id.button_get_final:{
                getFinal();
                showResult();
                break;
            }
            case R.id.button_start:{
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(MainActivity.this, "The Song is Over", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case R.id.button_stop:{
                mediaPlayer.pause();
            }
        }
    }


}
