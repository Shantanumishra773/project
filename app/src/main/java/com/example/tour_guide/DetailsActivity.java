package com.example.tour_guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {

    TextView dname,dnum,dadd,dsub,ddtails;
    ImageButton msg,cll;
    String s,n,id;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        dname = findViewById(R.id.dtextView);
        dnum = findViewById(R.id.dtextView2);
        dadd = findViewById(R.id.textView);
        dsub = findViewById(R.id.textView2s);
        ddtails = findViewById(R.id.tvDetails);


        db = FirebaseDatabase.getInstance().getReference().child("User").child("Guide");

        msg = findViewById(R.id.message_btn);
        cll = findViewById(R.id.call_btn);


        Intent iin = getIntent();
        Bundle b = iin.getExtras();






        if (b != null) {
            String j = (String) b.get("sName");
            dname.setText(j);
            n=j;
            String i = (String) b.get("sNum");
            dnum.setText(i);
            s="tel:"+i;
            id = i;
            String k = (String) b.get("sEmail");
            dadd.setText(k);
            String l = (String) b.get("sTP");
            dsub.setText(l);
            // String tDetails = (String)b.get("sDt");
            // ddtails.setText(tDetails);

        }



        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String dt = dataSnapshot.child(id).getValue(Guide.class).getTour_Details();
                ddtails.setText(dt);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









        cll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(Intent.ACTION_CALL);
                ic.setData(Uri.parse(s));
                startActivity(ic);
                Toast.makeText(DetailsActivity.this,"Calling...",Toast.LENGTH_LONG).show();
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(s,null,"Hi, I want to talk to you about tution",null,null);
                Toast.makeText(DetailsActivity.this,"Request has been sent",Toast.LENGTH_LONG).show();

            }
        });

    }
}
