package com.app.siaplapor;

import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.siaplapor.model.Report;
import com.app.siaplapor.response.DataResponse;
import com.app.siaplapor.rest.ApiConnection;
import com.app.siaplapor.rest.InterfaceConnection;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDaftarLaporan extends RecyclerView.Adapter<AdapterDaftarLaporan.ViewHolder> {

    InterfaceConnection interfaceConnection;
    ArrayList<Report> daftarLaporan;
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
        holder.nama.setText(daftarLaporan.get(position).getName());
        //holder.telepon.setText(daftarLaporan.get(position).getTelepon());
        //holder.isi_laporan.setText(daftarLaporan.get(position).getIsi_laporan());
        //holder.user_id.setText(daftarLaporan.get(position).getUserId());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = daftarLaporan.get(position).getId();
                popupDelete();
            }
        });
    }

    private void popupDelete() {
        Context context = new ContextThemeWrapper(mContext, R.style.AppTheme);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
        materialAlertDialogBuilder.setTitle("Hapus Laporan")
                .setMessage("Apa anda yakin ingin menghapus Laporan ini?")
                .setNegativeButton("Batalkan", null)
                .setPositiveButton("Ya!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteLaporan();
                    }
                })
                .show();
    }

    private void deleteLaporan(){
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        Call<DataResponse> delete_laporan = interfaceConnection.delete_data_report(id);
        delete_laporan.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(mContext,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(mContext, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarLaporan.size();
    }

    public void updateDataLaporan(ArrayList<Report> updateDataLaporan) {
        daftarLaporan.clear();
        daftarLaporan.addAll(updateDataLaporan);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout_daftar_laporan;
        TextView nik, nama, telepon, alamat, isi_laporan, user_id;
        ImageButton btnDelete, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_daftar_laporan = (ConstraintLayout)itemView.findViewById(R.id.layout_daftar_laporan);
            nik = (TextView) itemView.findViewById(R.id.textViewNik);
            nama = (TextView) itemView.findViewById(R.id.textViewNama);
            //telepon = (TextView) itemView.findViewById(R.id.textViewTelepon);
            alamat = (TextView) itemView.findViewById(R.id.textViewAlamat);
            //isi_laporan = (TextView) itemView.findViewById(R.id.textViewIsiLaporan);
            //user_id = (TextView) itemView.findViewById(R.id.textViewUserId);
            btnDelete = (ImageButton)itemView.findViewById(R.id.imgBtnDeleteLaporan);
        }
    }
}
