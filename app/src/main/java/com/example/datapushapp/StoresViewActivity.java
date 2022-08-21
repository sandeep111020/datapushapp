package com.example.datapushapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.datapushapp.Adapters.StoresAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StoresViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    String museumname,passvalue;
    private StoresAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores_view);
        recyclerView=findViewById(R.id.recycler_menu);
        museumname= getIntent().getStringExtra("name");
        passvalue= getIntent().getStringExtra("pass");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<Storesuploadmodel> options =
                new FirebaseRecyclerOptions.Builder<Storesuploadmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(passvalue).child(museumname), Storesuploadmodel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new StoresAdapter(options,getApplicationContext(),passvalue,museumname);
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