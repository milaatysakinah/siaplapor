package com.app.siaplapor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.siaplapor.model.Report;
import com.app.siaplapor.response.DataResponse;
import com.app.siaplapor.rest.ApiConnection;
import com.app.siaplapor.rest.InterfaceConnection;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardUserFragment extends Fragment {
    ArrayList<Report> daftarLaporan = new ArrayList<>();
    RecyclerView tabel_laporan;
    InterfaceConnection interfaceConnection;
    AdapterDaftarLaporan adapterDaftarLaporan;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabel_laporan = (RecyclerView)view.findViewById(R.id.recyclerView_daftarLaporan);
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        loadDataLaporan();
    }

    private void loadDataLaporan() {
        adapterDaftarLaporan = new AdapterDaftarLaporan(getContext());
        Call<DataResponse> daftar_laporan = interfaceConnection.get_user_report("1");
        daftar_laporan.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    List<Report> listLaporan = response.body().getList_report();
                    daftarLaporan.addAll(listLaporan);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                adapterDaftarLaporan.updateDataLaporan(daftarLaporan);
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {

            }
        });
        startRecyclerView();
    }

    private void startRecyclerView() {
        tabel_laporan.setAdapter(adapterDaftarLaporan);
        tabel_laporan.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_user, container, false);
    }
}
