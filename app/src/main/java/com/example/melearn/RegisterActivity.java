package com.example.melearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText registerUsername, registerEmail, registerPassword, registerPasswordconfirm;
    Button btn_register, btn_gotoLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    Map<String, Object> newUser = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsername = findViewById(R.id.registerUsername);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerPasswordconfirm = findViewById(R.id.registerPasswordconfirm);
        btn_register = findViewById(R.id.btn_register);
        btn_gotoLogin = findViewById(R.id.btn_gotoLogin);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btn_gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        btn_gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = registerUsername.getText().toString();
                String Email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String passwordconfirm = registerPasswordconfirm.getText().toString();

                if (username.isEmpty()) {
                    registerUsername.setError("Username is required");
                    return;
                }
                if (Email.isEmpty()) {
                    registerEmail.setError("Email is required");
                    return;
                }
                if (password.isEmpty()) {
                    registerPassword.setError("Password is required");
                    return;
                }
                if (passwordconfirm.isEmpty()) {
                    registerPasswordconfirm.setError("Confirm Password is required");
                    return;
                }
                if (!password.equals(passwordconfirm)) {
                    registerPasswordconfirm.setError("Passwords do not match");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(Email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        newUser.put("name", username);
                        newUser.put("email", registerEmail);

                        db.collection("Users").document(user.getUid()).set(newUser);

                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                );
            }
        });
    }
}