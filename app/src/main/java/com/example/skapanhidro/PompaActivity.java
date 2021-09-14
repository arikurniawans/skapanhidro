package com.example.skapanhidro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PompaActivity extends AppCompatActivity {
    Button btnON, btnOFF;
    TextView txtstatus;
    String success;
    private static final String TAG = PompaActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pompa);
        btnON = (Button) findViewById(R.id.btnControl);
        txtstatus = (TextView) findViewById(R.id.txtstatus);
        //btnOFF = (Button) findViewById(R.id.btnOn);

        btnON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnON.getText().equals("on")){
                    btnON.setText("off");
                    hidupkanPompa("0");
                    txtstatus.setText("OFF");

                }else if(btnON.getText().equals("off")){
                    btnON.setText("on");
                    hidupkanPompa("1");
                    txtstatus.setText("ON");
                }
            }
        });

//        btnOFF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hidupkanPompa("0");
//            }
//        });

    }

    private void hidupkanPompa(final String Status) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Loading Server ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, RestApi.Pompa, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getString(TAG_SUCCESS);

                    // Check for error node in json
                    if (success.equals("true")) {

                        Log.e("Successfully On Pompa!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Request Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "1");
                params.put("nama_pompa", "Pompa Air");
                params.put("status_pompa", Status);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}