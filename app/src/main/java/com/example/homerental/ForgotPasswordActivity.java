package com.example.homerental;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password); // Use the correct layout

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmail); // Correct ID for the EditText
        Button resetPassword = findViewById(R.id.btnResetPassword); // Correct ID for the Button

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's email input
                String email = etEmail.getText().toString().trim();

                // Validate email (e.g., check for empty input)
                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Email is required");
                    return;
                }

                // Send a password reset email
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Password reset email sent successfully
                                    Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                    finish(); // Close the activity
                                } else {
                                    // Failed to send password reset email
                                    Toast.makeText(ForgotPasswordActivity.this, "Failed to send email", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
