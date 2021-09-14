package com.example.skapanhidro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HstActivity extends AppCompatActivity {
    EditText etTglHst;
    Button btnLihat;
    ProgressDialog pDialog;
    int success;
    private static final String TAG = HstActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hst);
        etTglHst = (EditText) findViewById(R.id.etTanggalAwal);
        btnLihat = (Button) findViewById(R.id.btnLihat);
        Intent intent = getIntent();
        String idtanam = intent.getStringExtra("id_tanaman");

        etTglHst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TampilTanggalAwal();
            }
        });
    }

    public void TampilTanggalAwal(){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                String tanggal_awal = format.format(calendar.getTime());
                etTglHst.setText(tanggal_awal);

            }
        });
    }

}