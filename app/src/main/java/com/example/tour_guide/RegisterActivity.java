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

public class RegisterActivity extends AppCompatActivity {

    EditText eName,eEmail,ePassword,ePhoneNumber,ePlaceofTour;
    Button reg;
    DatabaseReference databaseReference;
    String data;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        eName = findViewById(R.id.etName);
        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        ePhoneNumber =  findViewById(R.id.etPhno);
        ePlaceofTour = findViewById(R.id.etPlaceT);

        data = getIntent().getExtras().get(Mycons.USERTYPE).toString();
        Toast.makeText(RegisterActivity.this,data,Toast.LENGTH_SHORT).show();


        reg = findViewById(R.id.regb);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");


        try {



                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String Guide_Name = eName.getText().toString().trim();
                        String Guide_Email = eEmail.getText().toString().trim();
                        String Guide_Password = ePassword.getText().toString().trim();
                        String Guide_PhoneNumber = ePhoneNumber.getText().toString().trim();
                        String Guide_TourPlace = ePlaceofTour.getText().toString().trim();
                        String Tour_Details = "";
                        if (Guide_Name.isEmpty() || Guide_Email.isEmpty() || Guide_Password.isEmpty() || Guide_PhoneNumber.isEmpty() || Guide_TourPlace.isEmpty()) {
                            Toast.makeText(RegisterActivity.this, "Enter all fields !!", Toast.LENGTH_SHORT).show();

                        } else {
                            //String id = databaseReference.push().getKey();

                            Guide guide = new Guide(Guide_Name, Guide_Password, Guide_Email, Guide_PhoneNumber, Guide_TourPlace, Tour_Details);

                            Toast.makeText(RegisterActivity.this, "Your", Toast.LENGTH_LONG).show();

                            databaseReference.child("Guide").child(Guide_PhoneNumber).setValue(guide);

                            Toast.makeText(RegisterActivity.this, "Your account is created", Toast.LENGTH_LONG).show();
                            Intent inte = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(inte);
                        }
                    }


                });
            }catch(Exception e)
        {
            Toast.makeText(RegisterActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
