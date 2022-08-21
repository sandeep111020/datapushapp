package com.example.datapushapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.datapushapp.Adapters.ArtsAdapter;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ArtViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArtsAdapter adapter1;
    String museumname,passvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_view);
        recyclerView=findViewById(R.id.recycler_menu);
        museumname= getIntent().getStringExtra("name");
        passvalue= getIntent().getStringExtra("pass");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<Vendoritemmodel> options =
                new FirebaseRecyclerOptions.Builder<Vendoritemmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(passvalue).child(museumname), Vendoritemmodel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new ArtsAdapter(options,getApplicationContext(),passvalue,museumname);
        recyclerView.setAdapter(adapter1);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }
}