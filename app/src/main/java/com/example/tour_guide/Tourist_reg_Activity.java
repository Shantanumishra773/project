package com.example.tour_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tourist_reg_Activity extends AppCompatActivity {

    EditText eName,eEmail,ePassword,ePhoneNumber,ePlaceofTour;
    Button reg;
    DatabaseReference databaseReference;
    String data;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_reg_);
        eName = findViewById(R.id.etName);
        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        ePhoneNumber =  findViewById(R.id.etPhno);
        reg = findViewById(R.id.regb);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Tourist_Name = eName.getText().toString().trim();
                String Tourist_Email = eEmail.getText().toString().trim();
                String Tourist_Password = ePassword.getText().toString().trim();
                String Tourist_PhoneNumber =ePhoneNumber.getText().toString().trim();


                if(Tourist_Name.isEmpty() || Tourist_Email.isEmpty() || Tourist_Password.isEmpty()|| Tourist_PhoneNumber.isEmpty())
                {
                    Toast.makeText(Tourist_reg_Activity.this,"Enter all fields !!",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    //String id = databaseReference.push().getKey();

                    Tourist tourist = new Tourist(Tourist_Name,Tourist_Email,Tourist_Password);

                    databaseReference.child("Tourist").child(Tourist_PhoneNumber).setValue(tourist);
                    Toast.makeText(Tourist_reg_Activity.this,"Your account is created",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Tourist_reg_Activity.this,Tour_Guide_Show_Activity.class); //should be changed to editActivity
                    startActivity(intent);
                }

            }
        });

    }
}
