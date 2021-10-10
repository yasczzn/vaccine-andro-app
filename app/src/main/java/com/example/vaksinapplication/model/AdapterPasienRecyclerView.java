package com.example.vaksinapplication.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vaksinapplication.CreateActivity;
import com.example.vaksinapplication.DetailActivity;
import com.example.vaksinapplication.R;
import com.example.vaksinapplication.ReadActivity;
import com.example.vaksinapplication.model.Pasien;

import java.util.ArrayList;

public class AdapterPasienRecyclerView extends RecyclerView.Adapter<AdapterPasienRecyclerView.ViewHolder> {
    private ArrayList<Pasien> daftarPasien;
    private Context context;
    FirebaseDataListener listener;

    public AdapterPasienRecyclerView(ArrayList<Pasien> pasiens, Context ctx){
        daftarPasien = pasiens;
        context = ctx;
        listener = (ReadActivity)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namapasien);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pasien, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = daftarPasien.get(position).getNama();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(DetailActivity.getActIntent((Activity) context).putExtra("data", daftarPasien.get(position)));
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();
                TextView editButton = (TextView) dialog.findViewById(R.id.tv_editdata);
                TextView delButton = (TextView) dialog.findViewById(R.id.tv_deletedata);
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(CreateActivity.getActIntent((Activity)
                                        context).putExtra("data", daftarPasien.get(position)));
                            }

                        }
                );
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                listener.onDeleteData(daftarPasien.get(position),
                                        position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
        return daftarPasien.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Pasien pasien, int position);
    }

}