package com.example.bus_trail.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bus_trail.Helper_Classes.SessionManager;
import com.example.bus_trail.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity {

    TextView toSignup,ForgotPass;
    Button Login;
    TextInputEditText email,pass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.dark_blue));
        }

        setContentView(R.layout.activity_login);

        toSignup = findViewById(R.id.toSignUp);
        ForgotPass = findViewById(R.id.forgotPass);
        Login = findViewById(R.id.btnLogIn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        sessionManager = new SessionManager(getApplicationContext(),SessionManager.TYPE_LOGINSESSION);



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
                                sessionManager.setLogin(true);

                                Toast.makeText(LoginActivity.this, Boolean.toString(sessionManager.isLogin()), Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(getApplicationContext(), ShowBusLocation.class);
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