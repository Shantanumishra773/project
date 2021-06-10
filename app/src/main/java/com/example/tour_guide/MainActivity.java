package com.example.tour_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button gd,tu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gd = findViewById(R.id.gdbtn);
        tu = findViewById(R.id.tbtn);

        gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,LoginActivity.class);
                i1.putExtra(Mycons.USERTYPE,"G");
                startActivity(i1);
            }
        });

        tu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this,Tourist_Log_Activity.class);
                //i2.putExtra(Mycons.USERTYPE,"T");
                startActivity(i2);
            }
        });
    }
}
