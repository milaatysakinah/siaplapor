package com.app.siaplapor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.app.siaplapor.rest.ApiConnection;
import com.app.siaplapor.rest.InterfaceConnection;
import com.google.android.material.textfield.TextInputEditText;

public class DetailLaporanFragment extends Fragment {

    TextInputEditText inputNik, inputNama, inputTelepon, inputAlamat, inputIsiLaporan;
    static String user_id = "1";
    Button btnTambahLaporan;
    InterfaceConnection interfaceConnection;
    private String id;
//    ArrayList<Report> daftarLaporan = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputNik = (TextInputEditText)view.findViewById(R.id.tie_nik);
        inputNama = (TextInputEditText)view.findViewById(R.id.tie_name);
        inputTelepon = (TextInputEditText)view.findViewById(R.id.tie_phone);
//        inputAlamat = (TextInputEditText)view.findViewById(R.id.tie_address);
        inputIsiLaporan = (TextInputEditText)view.findViewById(R.id.tie_isiLaporan);
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);

        try {
            final Bundle bundle = getArguments();
            id = bundle.getString("id");
            Log.d("id", id);
            inputNik.setText(bundle.getString("nik"));
            inputNama.setText(bundle.getString("nama"));
            inputTelepon.setText(bundle.getString("telepon"));
//            inputAlamat.setText(bundle.getString("alamat"));
            inputIsiLaporan.setText(bundle.getString("isiLaporan"));
        }

        catch(final Exception e){
            // Do nothing
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_laporan, container, false);
    }
}
