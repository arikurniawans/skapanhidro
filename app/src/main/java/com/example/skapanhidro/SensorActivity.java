package com.example.skapanhidro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SensorActivity extends AppCompatActivity {
    ProgressDialog loading;
    TextView txtNama,txtNilai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        txtNama = (TextView) findViewById(R.id.txtNama);
        txtNilai = (TextView) findViewById(R.id.txtNilai);

        getDataSensor();
    }

    private void   getDataSensor() {

        loading = ProgressDialog.show(SensorActivity.this, "Mohon Tunggu...", "Sedang Proses...", false, false);

        String url = RestApi.Sensor;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
//                Toast.makeText(DetailHerbalActivity.this,response,Toast.LENGTH_LONG).show();
                showHerbal(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SensorActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SensorActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showHerbal(String response){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("data");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String nama_sensor = jo.getString("nama_sensor");
                String nilai_sensor = jo.getString("nilai_sensor");
                txtNama.setText(nama_sensor);
                txtNilai.setText(nilai_sensor);


            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(SensorActivity.this
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }

}