package com.app.siaplapor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.siaplapor.response.DataResponse;
import com.app.siaplapor.rest.ApiConnection;
import com.app.siaplapor.rest.InterfaceConnection;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLaporanFragment extends Fragment {

    TextInputLayout layoutNik, layoutNama, layoutTelepon, layoutAlamat, layoutIsiLaporan;
    TextInputEditText inputNik, inputNama, inputTelepon, inputAlamat, inputIsiLaporan;
    String user_id = "1";
    Button btnTambahLaporan;
    InterfaceConnection interfaceConnection;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutNik = (TextInputLayout)view.findViewById(R.id.layoutNik);
        layoutNama = (TextInputLayout)view.findViewById(R.id.layoutNama);
        layoutTelepon = (TextInputLayout)view.findViewById(R.id.layoutTelepon);
        layoutAlamat = (TextInputLayout)view.findViewById(R.id.layoutAlamat);
        layoutIsiLaporan = (TextInputLayout)view.findViewById(R.id.layoutIsiLaporan);
        inputNik = (TextInputEditText)view.findViewById(R.id.inputNik);
        inputNama = (TextInputEditText)view.findViewById(R.id.inputNama);
        inputTelepon = (TextInputEditText)view.findViewById(R.id.inputTelepon);
        inputAlamat = (TextInputEditText)view.findViewById(R.id.inputAlamat);
        inputIsiLaporan = (TextInputEditText)view.findViewById(R.id.inputIsiLaporan);
        btnTambahLaporan = (Button)view.findViewById(R.id.btnTambahLaporan);
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);
        btnTambahLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLaporan();
            }
        });
    }

    private void addLaporan() {
        String nik = inputNik.getText().toString();
        String nama = inputNama.getText().toString();
        String telepon = inputTelepon.getText().toString();
        String alamat = inputAlamat.getText().toString();
        String isi_laporan = inputIsiLaporan.getText().toString();

        Call<DataResponse> addLaporan = interfaceConnection.insert_data_report(nik,nama,telepon,alamat,isi_laporan,user_id);
        addLaporan.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.d("Error here", "Error here", t);
                t.printStackTrace();
                Log.d("Error here", "Error here2", t);
                Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
