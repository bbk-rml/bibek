package com.example.bibek.shopmobile.Activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bibek.shopmobile.R;

public class CartActivity extends AppCompatActivity {

    TextView txtProductName,txtProductDescription,txtProductPrice,txtQuantity;
    Button btnAddCart,btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        txtProductName = findViewById(R.id.txtProductName);
        txtProductDescription = findViewById(R.id.txtProductDescription);
        txtProductPrice = findViewById(R.id.txtProductPrice);

        btnAddCart = findViewById(R.id.btnAddCart);
        btnEdit = findViewById(R.id.btnEdit);

        Intent in = getIntent();

        final String pn = in.getStringExtra("pName");
        txtProductName.setText(pn);
        txtProductName.setEnabled(false);

        final String pd = in.getStringExtra("pDescription");
        txtProductDescription.setText(pd);
        txtProductDescription.setEnabled(false);

        final String pp = in.getStringExtra("pPrice");
        txtProductPrice.setText(pp);
        txtProductPrice.setEnabled(false);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CartActivity.this,EditProduct.class);
                startActivity(in);
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CartActivity.this,ItemCartActivity.class);
                startActivity(in);
            }
        });
    }
}
