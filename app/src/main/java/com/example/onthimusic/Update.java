package com.example.onthimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {

    EditText edTencs,edTenbaihat;
    Button btnSave,btnBack;
    Music music;
    String id;
    String url="https://61bf645fb25c3a00173f4e21.mockapi.io/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edTencs = findViewById(R.id.edName2);
        edTenbaihat = findViewById(R.id.edNamebaihat2);

        btnSave = findViewById(R.id.btnSave1);
        btnBack = findViewById(R.id.btnBack3);

        Intent intent = getIntent();
        if(intent!=null){
            music = (Music) intent.getSerializableExtra("key1");
            id = music.getId();
            edTencs.setText(music.getTencs().toString());
            edTenbaihat.setText(music.getTenbaihat().toString());

        }
        btnSave.setOnClickListener(view->{
            PutApi(url);
        });
        btnBack.setOnClickListener(view->{
            startActivity(new Intent(Update.this,ListMusic.class));
        });
    }
    private void PutApi(String url)
    {
        StringRequest stringRequest = new StringRequest( Request.Method.PUT,
                url + "music/" +id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Update.this, "Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update.this, "Error by Post Data",
                        Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String ,String> params = new HashMap<>();
                params.put("tencs",edTencs.getText().toString());
                params.put("tenbaihat",edTenbaihat.getText().toString());

                return params; }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}