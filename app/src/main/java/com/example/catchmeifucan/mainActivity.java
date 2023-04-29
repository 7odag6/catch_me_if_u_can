package com.example.catchmeifucan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button button;
    TextView textview;
    FirebaseUser user;
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        button=findViewById(R.id.logout);
        textview=findViewById(R.id.user_details);
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
        }
        else {
            textview.setText(user.getEmail());
        }
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });







    }

}
