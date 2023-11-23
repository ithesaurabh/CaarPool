package com.example.caarpool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        MaterialButton login = findViewById(R.id.login);
        MaterialButton createAccount = findViewById(R.id.createAccount);
        TextInputLayout mailLayout = findViewById(R.id.mailLayout);
        TextInputLayout passwordLayout = findViewById(R.id.passwordLayout);
        TextInputEditText mailET = findViewById(R.id.mailET);
        TextInputEditText passwordET = findViewById(R.id.passwordET);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.requireNonNull(mailET.getText()).toString().isEmpty()) {
                    mailLayout.setError("This field is required");
                } else if (Objects.requireNonNull(passwordET.getText()).toString().isEmpty()) {
                    passwordLayout.setError("This field is required");
                } else {
                    ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Logging in...");
                    progressDialog.show();

                    auth.signInWithEmailAndPassword(mailET.getText().toString(), passwordET.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, HomepageActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "There was an error while logging in", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });
    }
}