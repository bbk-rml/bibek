package com.example.bibek.shopmobile.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bibek.shopmobile.Product;
import com.example.bibek.shopmobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class HomeActivity extends AppCompatActivity {

    ImageView imgCamera;
    final Activity activity=this;
    EditText txtBarcode,txtProductName,txtProductDescription,txtProductPrice,txtQuantity;
    Button btnAdd;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        imgCamera = (ImageView) findViewById(R.id.imgCamera);
        txtBarcode = (EditText) findViewById(R.id.txtBarcode);
        txtProductName = findViewById(R.id.txtProductName);
        txtProductDescription = findViewById(R.id.txtProductDescription);
        txtProductPrice = findViewById(R.id.txtProductPrice);
        txtQuantity = findViewById(R.id.txtProductQuantity);
        btnAdd = findViewById(R.id.btnAdd);



        Intent in = getIntent();
        final String pn = in.getStringExtra("txtC");
        txtBarcode.setText(pn);


        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        reference = db.getReference("All Barcodes");


        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator in = new IntentIntegrator(activity);
                in.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                in.setPrompt("scan");
                in.setCameraId(0);
                in.setBeepEnabled(true);
                in.initiateScan();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String txtB = pn;
                String txtPN = txtProductName.getText().toString();
                String txtPD = txtProductDescription.getText().toString();
                String txtP = txtProductPrice.getText().toString();
                String txtPQ = txtQuantity.getText().toString();



                if(txtB.isEmpty() || txtPN.isEmpty() || txtP.isEmpty() || txtPD.isEmpty() || txtPQ.isEmpty())
                {
                    showMessage("Please Insert all Fields");


                }
                else {



                        Product pro = new Product(txtB, txtPN, txtPD, txtP, txtPQ);

                        Toast.makeText(getApplicationContext(), "Already Have the similar Barcode", Toast.LENGTH_LONG).show();

                        reference.child(txtB).setValue(pro);
                        showMessage("Data Successfully Saved");

                        txtBarcode.setText("");
                        txtProductName.setText("");
                        txtProductDescription.setText("");
                        txtProductPrice.setText("");
                        txtQuantity.setText("");
                    }
                }



        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result !=null)
        {
            if(result.getContents()==null)
            {
                Log.d("MainActivity","Cancelled Scan");
                showMessage("Cancelled");
            }
            else
            {
                Log.d("MainActivity","scanned");
                txtBarcode.setText(result.getContents());
                showMessage("Scanned");

            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);

        }
    }
    public void onRequestPermissionResult(int requestCode, String permission[],int[] grantResults)
    {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {


                    showMessage("Permission denied to access Camera");
                }
                return;
            }


        }

    }
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
