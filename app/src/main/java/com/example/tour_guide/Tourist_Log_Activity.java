package com.example.tour_guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tourist_Log_Activity extends AppCompatActivity {

    EditText lmail,lpswd;
    Button lbtn;
    TextView nu;
    DatabaseReference databaseReferenceTourist;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist__log_);

        lmail = findViewById(R.id.loginmane);
        lpswd =findViewById(R.id.loginpswrd);
        lbtn = findViewById(R.id.loginbutton);
        nu = findViewById(R.id.newuser);

        databaseReferenceTourist = FirebaseDatabase.getInstance().getReference().child("User").child("Tourist");

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReferenceTourist.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String loginid = lmail.getText().toString().trim();
                        String loginpswd = lpswd.getText().toString().trim();

                        if(dataSnapshot.child(loginid).exists())
                        {
                            if(dataSnapshot.child(loginid).getValue(Tourist.class).getTourist_Password().equals(loginpswd))
                            {
                                Toast.makeText(Tourist_Log_Activity.this,"Login successful !",Toast.LENGTH_LONG).show();
                                Intent it2 = new Intent(Tourist_Log_Activity.this,Tour_Guide_Show_Activity.class);
                                it2.putExtra(Mycons.USERTYPE,data);
                                startActivity(it2);
                            }
                            else
                            {
                                Toast.makeText(Tourist_Log_Activity.this,"Your Password is wrong!",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Tourist_Log_Activity.this,"Your userId is invalid !",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Tourist_Log_Activity.this,Tourist_reg_Activity.class);
                in.putExtra(Mycons.USERTYPE,data);
                startActivity(in);
            }
        });

    }
}
