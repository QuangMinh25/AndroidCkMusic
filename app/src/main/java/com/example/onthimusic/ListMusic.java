package com.example.onthimusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListMusic extends AppCompatActivity implements OnClickListner{
    RecyclerView rcv_music;
    ArrayList<Music> mMusic;
    AdapterMusic adt;
    ImageView btnBack,btnthem;
    View view;
    String url="https://61bf645fb25c3a00173f4e21.mockapi.io/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_music);
        rcv_music = findViewById(R.id.rcv_music);
        btnBack = findViewById(R.id.btnquayve1);
        btnthem = findViewById(R.id.btnthem);
        view =findViewById( R.id.view2 );


        mMusic = new ArrayList<>();
        btnBack.setOnClickListener(view->{
            TroLai();
        });
        btnthem.setOnClickListener(view->{
            Them();
        });
        GetData(url);
    }
    private void TroLai(){
        startActivity(
                new Intent(ListMusic.this,MainActivity.class));
        overridePendingTransition( R.anim.enter_y,R.anim.exit_y );
    }
    private void Them(){
        startActivity(
                new Intent(ListMusic.this,Add.class));
        overridePendingTransition( R.anim.enter_y,R.anim.exit_y );
    }

    private void GetData(String url)
    {
        JsonArrayRequest request = new JsonArrayRequest
                (url+"music", new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(ListMusic.this, "True",
                                Toast.LENGTH_SHORT).show();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = (JSONObject) response.get(i);
                                String id = obj.getString("id");
                                String tencs = obj.getString("tencs");
                                String tenbaihat = obj.getString("tenbaihat");

                                Music music = new Music(id,tencs,tenbaihat);
                                mMusic.add(music);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adt = new AdapterMusic(mMusic,ListMusic.this);
                        rcv_music.setHasFixedSize(true);
                        rcv_music.setAdapter(adt);
                        rcv_music.setLayoutManager(new GridLayoutManager(ListMusic.this,1));//keo xuong
                        //rcv_thucuong.setLayoutManager(new GridLayoutManager(Show.this,2));//keo xuong 2 côt
                        //rcv_thucuong.setLayoutManager(new LinearLayoutManager(Show.this, LinearLayoutManager.HORIZONTAL, false));//keo ngang



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListMusic.this,
                                "Error make by API server!",Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }



    @Override
    public void itemClicklistener(Music music) {

    }

    @Override
    public void buttonsuaClick(Music music) {

        Intent intent = new Intent(ListMusic.this,Update.class);
        overridePendingTransition( R.anim.enter_y,R.anim.exit_y );
        intent.putExtra("key1",music);
        startActivity(intent);
    }

    @Override
    public void buttonxoaClick(Music music) {
        Intent intent = new Intent(ListMusic.this,Delete.class);
        overridePendingTransition( R.anim.enter_y,R.anim.exit_y );
        intent.putExtra("key1",music);
        startActivity(intent);
    }

    @Override
    public void btnngheClick(Music music) {
        Intent intent = new Intent(ListMusic.this,NgheNhac.class);

        intent.putExtra("key1",music);
        startActivity(intent);
    }


}