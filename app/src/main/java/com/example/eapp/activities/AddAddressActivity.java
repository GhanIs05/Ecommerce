package com.example.eapp.activities;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.eapp.R;
import com.example.eapp.utils.AddressClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText etFullName;
    EditText etAddressLine1;
    EditText etCity;
    EditText etState;
    EditText etZipCode;
    EditText etPhone;

    Button confirmBtn;

    FirebaseDatabase database;
    DatabaseReference reference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_address);

        etFullName = findViewById(R.id.et_full_name);
        etAddressLine1 = findViewById(R.id.et_address_line1);
        etCity = findViewById(R.id.e_city);
        etState = findViewById(R.id.et_state);
        etZipCode = findViewById(R.id.et_zip_code);
        etPhone = findViewById(R.id.et_phone);

        confirmBtn = findViewById(R.id.btn_confirm_address);
        etFullName. setGravity(Gravity. CENTER_HORIZONTAL);
        etCity. setGravity(Gravity. CENTER_HORIZONTAL);
        etAddressLine1. setGravity(Gravity. CENTER_HORIZONTAL);
        etState. setGravity(Gravity. CENTER_HORIZONTAL);
        etZipCode. setGravity(Gravity. CENTER_HORIZONTAL);
        etPhone. setGravity(Gravity. CENTER_HORIZONTAL);



        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString();
                String addressLine1 = etAddressLine1.getText().toString();
                String city = etCity.getText().toString();
                String state = etState.getText().toString();
                String zipCode = etZipCode.getText().toString();
                String phone = etPhone.getText().toString();

                if(!fullName.isEmpty() && !addressLine1.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zipCode.isEmpty() && !phone.isEmpty()) {

                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("Address");

                    AddressClass addressClass = new AddressClass(fullName, addressLine1, city, state, zipCode, phone);
                    reference.child(fullName).setValue(addressClass);
                    Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddAddressActivity.this, MainActivity.class));

                } else {
                    Toast.makeText(AddAddressActivity.this, "Kindly Fill All Field", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }

}