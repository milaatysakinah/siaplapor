package com.app.siaplapor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.siaplapor.data.session.SessionRepository;
import com.app.siaplapor.data.session.UserSessionRepository;
import com.app.siaplapor.model.Report;
import com.app.siaplapor.model.User;
import com.app.siaplapor.response.DataResponse;
import com.app.siaplapor.response.UserResponse;
import com.app.siaplapor.rest.ApiConnection;
import com.app.siaplapor.rest.InterfaceConnection;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDaftarLaporan extends RecyclerView.Adapter<AdapterDaftarLaporan.ViewHolder> {

    InterfaceConnection interfaceConnection;
    ArrayList<Report> daftarLaporan;
    Context mContext;
    String id;
    private String role;
    private SessionRepository userRepository;
    User userLogin;

    public AdapterDaftarLaporan(Context context) {
        daftarLaporan = new ArrayList<>();
        mContext = context;
        userRepository =  new UserSessionRepository(mContext);
        userLogin = (User) userRepository.getSessionData();
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
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
        String isiLaporan = daftarLaporan.get(position).getReport();

        if(isiLaporan.length() > 10) {
            isiLaporan = (isiLaporan.substring(0, 10)) + "...";
        }

        holder.isi.setText(isiLaporan);
        //holder.user_id.setText(daftarLaporan.get(position).getUserId());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = daftarLaporan.get(position).getId();
                popupDelete();
            }
        });

        holder.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = daftarLaporan.get(position).getId();

                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("nik", daftarLaporan.get(position).getNik());
                bundle.putString("nama", daftarLaporan.get(position).getName());
                bundle.putString("telepon", daftarLaporan.get(position).getPhone());
//                bundle.putString("alamat", daftarLaporan.get(position).get());
                bundle.putString("isiLaporan", daftarLaporan.get(position).getReport());

                Fragment fragment = new DetailLaporanFragment();
                fragment.setArguments(bundle);

                User userLoggin = (User) userRepository.getSessionData();
                if(userLoggin != null) {
                    Call<UserResponse> checkrole = interfaceConnection.checkRole(userLoggin.getEmail());
                    checkrole.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful()) {
                                List<User> listUser = response.body().getList_user();
                                com.app.siaplapor.model.User userLogin = listUser.get(0);
                                role = listUser.get(0).getRole();
                                if (role.equals("admin")) {
                                    FragmentManager fragmentManager = ((MainAdminActivity) mContext).getSupportFragmentManager();

                                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                    Intent intent = new Intent(mContext, MainAdminActivity.class);
                                } else {
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    FragmentManager fragmentManager = ((MainActivity) mContext).getSupportFragmentManager();

                                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                }
                            } else {
                                try {
                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(mContext, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Log.d("Error here", "Error here", t);
                            t.printStackTrace();
                            Log.d("Error here", "Error here2", t);
                            Toast.makeText(mContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
//                FragmentManager fragmentManager = ((MainActivity)mContext).getSupportFragmentManager();
//
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
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
        TextView nik, nama, telepon, isi, isi_laporan, user_id;
        ImageButton btnDelete, btnEdit, btnShow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_daftar_laporan = (ConstraintLayout)itemView.findViewById(R.id.layout_daftar_laporan);
            nik = (TextView) itemView.findViewById(R.id.textViewNik);
            nama = (TextView) itemView.findViewById(R.id.textViewNama);
            //telepon = (TextView) itemView.findViewById(R.id.textViewTelepon);
            isi = (TextView) itemView.findViewById(R.id.textViewIsiLaporan);
            //isi_laporan = (TextView) itemView.findViewById(R.id.textViewIsiLaporan);
            //user_id = (TextView) itemView.findViewById(R.id.textViewUserId);
            btnDelete = (ImageButton)itemView.findViewById(R.id.imgBtnDeleteLaporan);
            btnShow =  (ImageButton)itemView.findViewById(R.id.imgBtnShowLaporan);
        }
    }
}
