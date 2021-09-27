package com.example.skapanhidro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
import java.util.List;

public class EstimasiActivity extends AppCompatActivity {
    ProgressDialog loading;
    private RecyclerView recyclerView;
    AdapterTanaman adapterTanaman;
    List<ClassTanaman> listTanaman;

    LinearLayout date_pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimasi);
        listTanaman = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewTnaman);
        adapterTanaman = new AdapterTanaman(EstimasiActivity.this,listTanaman);

        recyclerView.setLayoutManager(new LinearLayoutManager(EstimasiActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterTanaman);

        getData();

        //  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hst);

        date_pick = (LinearLayout) findViewById(R.id.date_picker);

        date_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datePicker = new Intent(EstimasiActivity.this, HstActivity.class);
                startActivity(datePicker);
            }
        });

    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_apps);
//
//        date_pick = (LinearLayout) findViewById(R.id.date_picker);
//
//        date_pick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent datePicker = new Intent(EstimasiActivity.this, HstActivity.class);
//                startActivity(datePicker);
//            }
//        });
//    }

    private void getData() {

        loading = ProgressDialog.show(EstimasiActivity.this, "Mohon Tunggu...", "Sedang Proses...", false, false);

        String url = RestApi.tanaman;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(estimasiActivity.this,response,Toast.LENGTH_LONG).show();
                showTanaman(response);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EstimasiActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(EstimasiActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showTanaman(String response){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        list.clear();
        adapterTanaman.notifyDataSetChanged();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("data");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_tanaman = jo.getString("id_plant");
                String nama_tanaman = jo.getString("plant_name");
                String jumlah = jo.getString("plant_count");
                String foto_tanaman = jo.getString("plant_image");
                String masa_panen = jo.getString("plant_harvest");

                ClassTanaman tanaman = new ClassTanaman(
                        id_tanaman,
                        nama_tanaman,
                        foto_tanaman,
                        masa_panen,
                        jumlah

                );
                listTanaman.add(tanaman);
                adapterTanaman.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(EstimasiActivity.this
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }

}