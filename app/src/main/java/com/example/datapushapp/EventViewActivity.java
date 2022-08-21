package com.example.datapushapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.datapushapp.Adapters.ArtsAdapter;

import com.example.datapushapp.Adapters.EventsAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class EventViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventsAdapter adapter1;
    String museumname,passvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        recyclerView=findViewById(R.id.recycler_menu);
        museumname= getIntent().getStringExtra("name");
        passvalue= getIntent().getStringExtra("pass");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<Eventupoadmodel> options =
                new FirebaseRecyclerOptions.Builder<Eventupoadmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(passvalue).child(museumname), Eventupoadmodel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new EventsAdapter(options,getApplicationContext(),passvalue,museumname);
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