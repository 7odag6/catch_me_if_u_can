package com.example.catchmeifucan;


import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class register extends Activity {
    TextInputEditText editTextEmail,EditTextPassword,EditTextName;
    Button buttonReg;
    ProgressBar progressBar;
    TextView textView;
    FirebaseAuth mAuth;
    FirebaseUser currentUser = mAuth.getCurrentUser();
    public void onStart() {
        super.onStart();

        if(currentUser != null){
            Intent intent= new Intent(getApplicationContext(), register.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth =FirebaseAuth.getInstance();
        editTextEmail=findViewById(R.id.email);
        EditTextPassword=findViewById(R.id.password);
        EditTextName=findViewById(R.id.name);
        buttonReg=findViewById(R.id.btn_register);
        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.logInNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password,name;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(EditTextPassword.getText());
                name=String.valueOf(EditTextName.getText());

                progressBar.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(register.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(register.this,"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(register.this,"Enter name",Toast.LENGTH_SHORT).show();
                    return;
                }




                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                }
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "Account Created.",
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                });
            }
        });
    }

}


