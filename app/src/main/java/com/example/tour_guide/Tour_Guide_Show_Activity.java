package com.example.tour_guide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Tour_Guide_Show_Activity extends AppCompatActivity {


    ImageButton imgb;
    LinearLayoutManager layoutManager;
    private RecyclerView mBlogListview;
    private DatabaseReference sDatabase;
    private ArrayList<Guide> arrayList;
    private ArrayList<Guide> searchList;
    private FirebaseRecyclerOptions<Guide> options;
    private FirebaseRecyclerAdapter<Guide, UserViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour__guide__show_);
        sDatabase = FirebaseDatabase.getInstance().getReference().child("User").child("Guide");
        sDatabase.keepSynced(true);

        //sublist = findViewById(R.id.subs);
        //loclist = findViewById(R.id.locs);
        imgb = findViewById(R.id.ib);

        mBlogListview = findViewById(R.id.srecycleview);
        mBlogListview.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(this);  //,RecyclerView.HORIZONTAL,false
        mBlogListview.setLayoutManager(layoutManager);
        arrayList = new ArrayList<Guide>();

        String st;



        Query  searchQuery  = sDatabase.orderByChild("Guide");
       // Query searchQuery = sDatabase.orderByChild("guide_TourPlace").startAt(st).endAt(st + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<Guide>().setQuery(searchQuery, Guide.class).build();

        adapter = new FirebaseRecyclerAdapter<Guide, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull final Guide model) {

                holder.shname.setText(model.getGuide_Name());
                // holder.shnum.setText(model.getUnumb());
                // holder.shdomain.setText(model.getUdomain());
                holder.shsubj.setText(model.getGuide_TourPlace().toUpperCase());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Tour_Guide_Show_Activity.this, DetailsActivity.class);
                        intent.putExtra("sName", model.getGuide_Name());
                        intent.putExtra("sNum", model.getGuide_PhoneNumber());
                        intent.putExtra("sEmail",model.getGuide_Email());
                        intent.putExtra("sTP",model.getGuide_TourPlace());
                        intent.putExtra("sDt",model.getTour_Details());
                        startActivity(intent);
                    }
                });


            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new UserViewHolder(LayoutInflater.from(Tour_Guide_Show_Activity.this).inflate(R.layout.blog_row, parent, false));
            }
        };

        mBlogListview.setAdapter(adapter);




        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inttentll = new Intent(Tour_Guide_Show_Activity.this,LoginActivity.class);
                startActivity(inttentll);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseUserSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseUserSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void firebaseUserSearch(String st) {


        String str = st.toLowerCase();
        String query = str;
        Query fbaseSquery = sDatabase.orderByChild("guide_TourPlace").startAt(query).endAt(query+"\uf8ff");
        //fbaseSquery=sDatabase.orderByChild("udomain").startAt("teacher").endAt("teacher"+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<Guide>().setQuery(fbaseSquery,Guide.class).build();

        adapter = new FirebaseRecyclerAdapter<Guide, UserViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull final Guide mode) {

                holder.shname.setText(mode.getGuide_Name());
                // holder.shnum.setText(mode.getUnumb());
                // holder.shdomain.setText(mode.getUdomain());
                holder.shsubj.setText(mode.getGuide_TourPlace());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Tour_Guide_Show_Activity.this, DetailsActivity.class);
                        intent.putExtra("sName", mode.getGuide_Name());
                        intent.putExtra("sNum", mode.getGuide_PhoneNumber());
                        intent.putExtra("sEmail",mode.getGuide_Email());
                        intent.putExtra("sTP",mode.getGuide_TourPlace());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_row,parent,false);
                UserViewHolder viewHolder = new UserViewHolder(itemView);
                return viewHolder;
            }
        };
        mBlogListview.setLayoutManager(layoutManager);
        adapter.startListening();
        mBlogListview.setAdapter(adapter);


    }
}


