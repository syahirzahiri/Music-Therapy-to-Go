package com.muzammil.musictherapytogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Insight extends AppCompatActivity implements View.OnClickListener {

    private Button btn_main_page, btn_about;
    private ArrayList<InsightData> list_insight = new ArrayList<InsightData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);

        btn_main_page = findViewById(R.id.button_main_insight);
        btn_main_page.setOnClickListener(this);
        btn_about = findViewById(R.id.button_about_insight);
        btn_about.setOnClickListener(this);

        generateInsight();


    }

    private void generateInsight() {

        DatabaseReference refInsight = FirebaseDatabase.getInstance().getReference("insight");
        refInsight.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String timestamp = ds.child("timestamp").getValue().toString();
                    String result = ds.child("value").getValue().toString();
                    InsightData insightData = new InsightData(timestamp, result);
                    list_insight.add(insightData);
                }

                for (int i = list_insight.size() - 1; i >= 0; i--) {
                    InsightData insightData = list_insight.get(i);
                    addCard(insightData.getTimestamp(), insightData.getResult());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void addCard(String timestamp, String result) {
        LinearLayout mainLayout = findViewById(R.id.insightLayout);

        LayoutInflater inflater = getLayoutInflater();
        View myLayout = inflater.inflate(R.layout.insightcard, mainLayout, false);

        long ts = Long.parseLong(timestamp);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(ts);
        String date = DateFormat.format("dd/MM/yyyy h:mm a", cal).toString();

        TextView parentName = myLayout.findViewById(R.id.insightTime);
        parentName.setText(date);

        TextView childName = myLayout.findViewById(R.id.insightValue);
        childName.setText("Stress reduced: " + result +"%");

        mainLayout.addView(myLayout);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_insight: {
                startActivity(new Intent(Insight.this, MainActivity.class));
                break;
            }
            case R.id.button_about_insight: {
                startActivity(new Intent(Insight.this, About.class));
                break;
            }
        }
    }
}
