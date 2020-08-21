package com.example.sozlukvolley;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KelimelerAdapter extends RecyclerView.Adapter<KelimelerAdapter.CardviewNesneTutucu>  {
    private Context mContext;
    private ArrayList<Kelimeler> kelimelerListesi;


    public KelimelerAdapter(Context mContext, ArrayList<Kelimeler> kelimelerListesi) {
        this.mContext = mContext;
        this.kelimelerListesi = kelimelerListesi;
    }

    public  class  CardviewNesneTutucu extends RecyclerView.ViewHolder {

        private TextView tvIngilizce,tvTurkce;
        private CardView cardview;

        public CardviewNesneTutucu(@NonNull View itemView) {
            super(itemView);
            tvIngilizce = itemView.findViewById(R.id.tvIngilizce);
            tvTurkce = itemView.findViewById(R.id.tvTurkce);
            cardview = itemView.findViewById(R.id.cardview);
        }

    }

    @NonNull
    @Override
    public CardviewNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tasarim, parent, false);
        return new CardviewNesneTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardviewNesneTutucu holder, int position) {
        final Kelimeler kelimeler = kelimelerListesi.get(position);
        holder.tvIngilizce.setText(kelimeler.getIngilizce());
        holder.tvTurkce.setText(kelimeler.getTurkce());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,DetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("nesne",kelimeler);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelimelerListesi.size();
    }



}
