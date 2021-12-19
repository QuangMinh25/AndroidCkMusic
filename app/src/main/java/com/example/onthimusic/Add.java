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

public class Add extends AppCompatActivity {
    Button btnCreate,btnBack;
    EditText edName,edNamebaihat;
    String url="https://61bf645fb25c3a00173f4e21.mockapi.io/api/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBack2);

        edName = findViewById(R.id.edName);
        edNamebaihat = findViewById(R.id.edNamebaihat);


        btnCreate.setOnClickListener(view->{
            PostApi(url);
        });
        btnBack.setOnClickListener(view->{
            startActivity(new Intent(Add.this,ListMusic.class));
        });
    }
    private void PostApi(String url)
    {
        StringRequest stringRequest = new StringRequest( Request.Method.POST,
                url + "music/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Add.this, "Successfully",
                        Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Add.this, "Error by Post Data",
                        Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String ,String> params = new HashMap<>();
                String id = "";
                String tencs = edName.getText().toString();
                String tenbaihat = edNamebaihat.getText().toString();

                params.put("id",id);
                params.put("tencs",tencs);
                params.put("tenbaihat",tenbaihat);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}