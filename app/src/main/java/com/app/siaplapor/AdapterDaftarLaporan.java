package com.app.siaplapor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.siaplapor.model.LaporanModel;
import com.app.siaplapor.rest.InterfaceConnection;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterDaftarLaporan extends RecyclerView.Adapter<AdapterDaftarLaporan.ViewHolder> {

    InterfaceConnection interfaceConnection;
    ArrayList<LaporanModel> daftarLaporan;
    Context mContext;
    String id;

    public AdapterDaftarLaporan(Context context) {
        daftarLaporan = new ArrayList<>();
        mContext = context;
    }

    @NonNull
    @Override
    public AdapterDaftarLaporan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_daftar_laporan, parent, false);
        AdapterDaftarLaporan.ViewHolder holder = new AdapterDaftarLaporan.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDaftarLaporan.ViewHolder holder, int position) {
        holder.nik.setText(daftarLaporan.get(position).getNik());
        holder.nama.setText(daftarLaporan.get(position).getNama());
        holder.telepon.setText(daftarLaporan.get(position).getTelepon());
        holder.alamat.setText(daftarLaporan.get(position).getAlamat());
        holder.isi_laporan.setText(daftarLaporan.get(position).getIsi_laporan());
        holder.user_id.setText(daftarLaporan.get(position).getUserId());
    }

    @Override
    public int getItemCount() {
        return daftarLaporan.size();
    }

    public void updateDataLaporan(ArrayList<LaporanModel> updateDataLaporan) {
        daftarLaporan.clear();
        daftarLaporan.addAll(updateDataLaporan);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout_daftar_laporan;
        TextView nik, nama, telepon, alamat, isi_laporan, user_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_daftar_laporan = (ConstraintLayout)itemView.findViewById(R.id.layout_daftar_laporan);
            nik = (TextView) itemView.findViewById(R.id.textViewNik);
            nama = (TextView) itemView.findViewById(R.id.textViewNama);
            //telepon = (TextView) itemView.findViewById(R.id.textViewTelepon);
            alamat = (TextView) itemView.findViewById(R.id.textViewAlamat);
            //isi_laporan = (TextView) itemView.findViewById(R.id.textViewIsiLaporan);
            //user_id = (TextView) itemView.findViewById(R.id.textViewUserId);
        }
    }
}
