package com.example.bus_trail.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bus_trail.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView toSignup,ForgotPass;
    Button Login;
    TextInputEditText email,pass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toSignup = findViewById(R.id.toSignUp);
        ForgotPass = findViewById(R.id.forgotPass);
        Login = findViewById(R.id.btnLogIn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);


        toSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
            }
        });

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPassActivity.class));
                finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateUserEmial() | !validateUserPassword())
                    return;

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful())
                        {
                            if (firebaseAuth.getCurrentUser().isEmailVerified())
                            {
                                Intent myIntent = new Intent(getApplicationContext(), ForgotPassActivity.class);
                                startActivity(myIntent);
                                finish();
                            }
                            else
                                Toast.makeText(getApplicationContext(), "Verify Your Email First!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }


    private boolean validateUserEmial() {
        CharSequence target = email.getText().toString().trim();
        if (TextUtils.isEmpty(target))
        {
            email.setError("Field Cannot Be Empty!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(target).matches())
        {
            email.setError("Invalid Email!");
            return false;
        }
        else
        {
            email.setError(null);
            return true;
        }
    }

    private boolean validateUserPassword() {
        String val = pass.getText().toString().trim();

        if (val.isEmpty())
        {
            pass.setError("Field Cannot Be Empty!");
            return false;
        }
        else if(val.length() < 8)
        {
            pass.setError("Password Should Contain 8 Characters!");
            return false;
        }
        else
        {
            pass.setError(null);
            return true;
        }
    }


}