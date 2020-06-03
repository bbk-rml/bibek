package com.example.bibek.shopmobile.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

import com.example.bibek.shopmobile.R;

public class IndexActivity extends AppCompatActivity {

    CardView cvLogin,cvRegister,cvFAQ,cvAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        cvLogin = findViewById(R.id.cvLogin);
        cvRegister = findViewById(R.id.cvRegister);
        cvAboutUs = findViewById(R.id.cvAboutUs);
        cvFAQ = findViewById(R.id.cvFAQ);

        cvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IndexActivity.this,MainActivity.class);
                startActivity(in);
            }
        });

        cvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(IndexActivity.this,RegisterActivity.class);
                startActivity(in);
            }
        });

        /*cvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cvFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
}
