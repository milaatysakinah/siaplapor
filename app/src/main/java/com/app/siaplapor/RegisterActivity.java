package com.app.siaplapor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.siaplapor.model.User;
import com.app.siaplapor.data.session.SessionRepository;
import com.app.siaplapor.data.session.UserSessionRepository;
import com.app.siaplapor.response.DataResponse;
import com.app.siaplapor.response.UserResponse;
import com.app.siaplapor.rest.ApiConnection;
import com.app.siaplapor.rest.InterfaceConnection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextView tvLogin;
    private TextInputEditText tifEmail, tifPassword, tifAddress, tifUsername, tifName;
    private FirebaseAuth auth;
    InterfaceConnection interfaceConnection;
    private String role;
    private SessionRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        tvLogin = (TextView) findViewById(R.id.tv_login);
        tifEmail = (TextInputEditText) findViewById(R.id.etEmail);
        tifPassword = (TextInputEditText) findViewById(R.id.etPassword);
        tifAddress = (TextInputEditText) findViewById(R.id.etAddress);
        tifUsername = (TextInputEditText) findViewById(R.id.etUsername);
        tifName = (TextInputEditText) findViewById(R.id.etName);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        userRepository =  new UserSessionRepository(this);

        interfaceConnection = ApiConnection.getClient().create(InterfaceConnection.class);

        User userLoggin = (User) userRepository.getSessionData();
        if(userLoggin != null) {
            Call<UserResponse> checkrole = interfaceConnection.checkRole(userLoggin.getEmail());
            checkrole.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        List<com.app.siaplapor.model.User> listUser = response.body().getList_user();
                        com.app.siaplapor.model.User userLogin = listUser.get(0);
                        role = listUser.get(0).getRole();
                        if(role.equals("admin")){
                            Intent intent = new Intent(RegisterActivity.this, MainAdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.d("Error here", "Error here", t);
                    t.printStackTrace();
                    Log.d("Error here", "Error here2", t);
                    Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            });
        }

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tifName.getText().toString().trim();
                String username = tifUsername.getText().toString().trim();
                String email = tifEmail.getText().toString().trim();
                String password = tifPassword.getText().toString().trim();
                String address = tifAddress.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Call<UserResponse> register_user = interfaceConnection.register( name, username, email, password, address);
                                    register_user.enqueue(new Callback<UserResponse>() {
                                        @Override
                                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                            if (response.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                if(response.body().getMessage().equalsIgnoreCase("User registered")) {
                                                    redirectToLogin();
                                                }
                                            } else {
                                                try {
                                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                    Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                                                } catch (Exception e) {
                                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserResponse> call, Throwable t) {
                                            Log.d("Error here", "Error here", t);
                                            t.printStackTrace();
                                            Log.d("Error here", "Error here2", t);
                                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                        }
                                    });
//
//                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                    finish();
                                }
                            }
                        });

            }
        });


    }

    private void redirectToLogin() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }
}