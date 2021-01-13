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

public class ProfileFragment extends Fragment {

    TextInputEditText name, username, email, address;
    static String user_id = "1";
    InterfaceConnection interfaceConnection;
    private String id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = (TextInputEditText)view.findViewById(R.id.tie_name);
        username = (TextInputEditText)view.findViewById(R.id.tie_username);
        email = (TextInputEditText)view.findViewById(R.id.tie_email);
        address = (TextInputEditText)view.findViewById(R.id.tie_address);
        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);

        try {
            final Bundle bundle = getArguments();
            id = bundle.getString("id");
            Log.d("id", id);
            name.setText(bundle.getString("name"));
            username.setText(bundle.getString("username"));
            email.setText(bundle.getString("email"));
            address.setText(bundle.getString("address"));
        }

        catch(final Exception e){
            // Do nothing
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }
}
