package com.example.skapanhidro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuApps extends AppCompatActivity {
CardView cardPompa,cardSensor, cardestimasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apps);
        cardPompa = (CardView) findViewById(R.id.cardPompa);
        cardSensor= (CardView) findViewById(R.id.cardSensor);
        cardestimasi = (CardView) findViewById(R.id.cardestimasi);

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

        cardSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent estimasi = new Intent(MenuApps.this, EstimasiActivity.class);
                startActivity(estimasi);
            }
        });
    }
}