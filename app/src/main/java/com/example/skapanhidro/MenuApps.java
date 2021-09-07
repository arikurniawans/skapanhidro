package com.example.skapanhidro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuApps extends AppCompatActivity {
CardView cardPompa,cardSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apps);
        cardPompa = (CardView) findViewById(R.id.cardPompa);
        cardSensor= (CardView) findViewById(R.id.cardSensor);

        cardPompa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pompa = new Intent(MenuApps.this, PompaActivity.class);
                startActivity(pompa);
            }
        });

        cardSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sensor = new Intent(MenuApps.this, SensorActivity.class);
                startActivity(sensor);
            }
        });
    }
}