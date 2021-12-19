package com.example.onthimusic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder> {

    private ArrayList<Music> mMusic;
    private OnClickListner listener;
    public AdapterMusic(ArrayList<Music> mMusic, OnClickListner listener) {
        this.mMusic = mMusic;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music,
                parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music music = mMusic.get(position);
        holder.tvid.setText(music.getId());
        holder.tvtencs.setText(music.getTencs());
        holder.tvtenbaihat.setText(music.getTenbaihat());

        holder.music = music;
    }

    @Override
    public int getItemCount() {
        return mMusic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvid,tvtencs,tvtenbaihat;
        Button btndelete,btnupdate,btnnghe;
        Music music;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            tvtencs = itemView.findViewById(R.id.tvTencs);
            tvtenbaihat = itemView.findViewById(R.id.tvTenbaihat);
            tvid = itemView.findViewById(R.id.tvID);
            btndelete = itemView.findViewById(R.id.btndelete);
            btnupdate = itemView.findViewById(R.id.btnupdate);
            btnnghe = itemView.findViewById(R.id.btnnghe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClicklistener(music);
                }
            });
            btnupdate.setOnClickListener(view->{
                listener.buttonsuaClick(music);
            });
            btndelete.setOnClickListener(view->{
                listener.buttonxoaClick(music);
            });
            btnnghe.setOnClickListener( view->{
                listener.btnngheClick(music);
            } );
        }
    }
}
