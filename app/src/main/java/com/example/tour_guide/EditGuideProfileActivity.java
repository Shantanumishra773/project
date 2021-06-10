package com.example.tour_guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditGuideProfileActivity extends AppCompatActivity {

    EditText eName,eEmail,ePassword,ePhoneNumber,ePlaceofTour,sub_plc;
    Button update,addp;
    DatabaseReference updateReference,tourPlaceReference;
    String id,name,email,paswd,plct,placeD;
    //List<String> placeList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_guide_profile);
        eName = findViewById(R.id.etName);
        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        ePhoneNumber =  findViewById(R.id.etPhno);
        ePlaceofTour = findViewById(R.id.etPlaceT);


        update = findViewById(R.id.updateb);
        addp = findViewById(R.id.adpbtn);


        sub_plc = findViewById(R.id.subplc);

        //placeList = new ArrayList<>();

        updateReference = FirebaseDatabase.getInstance().getReference().child("User").child("Guide");


       id = getIntent().getExtras().get("idu").toString();
        //Toast.makeText(EditGuideProfileActivity.this,id,Toast.LENGTH_LONG).show();

        updateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                name = dataSnapshot.child(id).getValue(Guide.class).getGuide_Name();
                eName.setText(name);

                email = dataSnapshot.child(id).getValue(Guide.class).getGuide_Email();
                eEmail.setText(email);

                paswd = dataSnapshot.child(id).getValue(Guide.class).getGuide_Password();
                ePassword.setText(paswd);

                //String phno = dataSnapshot.child(id).getValue(Guide.class).getGuide_PhoneNumber();
                ePhoneNumber.setText(id);

                plct = dataSnapshot.child(id).getValue(Guide.class).getGuide_TourPlace();
                ePlaceofTour.setText(plct);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String G_Name = eName.getText().toString().trim();
                String G_Email = eEmail.getText().toString().trim();
                String G_Password = ePassword.getText().toString().trim();
                String G_PhoneNumber =ePhoneNumber.getText().toString().trim();
                String G_TourPlace = ePlaceofTour.getText().toString().trim();


                updateReference.child(id).child("guide_Name").setValue(G_Name);
                updateReference.child(id).child("guide_Email").setValue(G_Email);
                updateReference.child(id).child("guide_Password").setValue(G_Password);
               // updateReference.child(id).setValue(name);
                updateReference.child(id).child("guide_TourPlace").setValue(G_TourPlace);





            }
        });

        addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String plc = sub_plc.getText().toString().trim();
               // placeList.add(plc);


                updateReference.child(id).child("tour_Details").setValue(plc);
                //tourPlaceReference = FirebaseDatabase.getInstance().getReference().child("User").child("Guide").child(id).child("tour_Details");




                tourPlaceReference.setValue(plc);
                sub_plc.setText(plc);

            }
        });

    }
}
