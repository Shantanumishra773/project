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

public class LoginActivity extends AppCompatActivity {

    EditText lmail,lpswd;
    Button lbtn;
    TextView nu;
    DatabaseReference databaseReferenceGuide;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lmail = findViewById(R.id.loginmane);
        lpswd =findViewById(R.id.loginpswrd);
        lbtn = findViewById(R.id.loginbutton);
        nu = findViewById(R.id.newuser);

        databaseReferenceGuide = FirebaseDatabase.getInstance().getReference().child("User").child("Guide");
       // databaseReferenceTourist = FirebaseDatabase.getInstance().getReference().child("User").child("Tourist");

        data = getIntent().getExtras().get(Mycons.USERTYPE).toString();
        Toast.makeText(LoginActivity.this,data,Toast.LENGTH_SHORT).show();

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.equals("G"))
                {
                    databaseReferenceGuide.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String loginid = lmail.getText().toString().trim();
                            String loginpswd = lpswd.getText().toString().trim();

                            if(dataSnapshot.child(loginid).exists())
                            {
                                if(dataSnapshot.child(loginid).getValue(Guide.class).getGuide_Password().equals(loginpswd))
                                {
                                    Toast.makeText(LoginActivity.this,"Login successful !",Toast.LENGTH_LONG).show();
                                   // Intent it1 = new Intent(LoginActivity.this,EditGuideProfileActivity.class);
                                    Intent it1 = new Intent(LoginActivity.this,EditGuideProfileActivity.class);
                                    it1.putExtra(Mycons.USERTYPE,data);
                                    it1.putExtra("idu",loginid);
                                    startActivity(it1);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"Your Password is wrong!",Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Your userId is invalid !",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }



              /*  if (data.equals("T"))
                {
                    databaseReferenceTourist.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String loginid = lmail.getText().toString().trim();
                            String loginpswd = lpswd.getText().toString().trim();

                            if(dataSnapshot.child(loginid).exists())
                            {
                                if(dataSnapshot.child(loginid).getValue(Guide.class).getGuide_Password().equals(loginpswd))
                                {
                                    Toast.makeText(LoginActivity.this,"Login successful !",Toast.LENGTH_LONG).show();
                                    Intent it2 = new Intent(LoginActivity.this,Tour_Guide_Show_Activity.class);
                                    it2.putExtra(Mycons.USERTYPE,data);
                                    startActivity(it2);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,"Your Password is wrong!",Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Your userId is invalid !",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }*/


            }
        });
        nu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this,RegisterActivity.class);
                in.putExtra(Mycons.USERTYPE,data);
                startActivity(in);
            }
        });

    }
}
