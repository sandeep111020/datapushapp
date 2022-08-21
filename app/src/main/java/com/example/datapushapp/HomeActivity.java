package com.example.datapushapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    ImageView mustsee, events, tickets, quicktour,arts,stores;
    CircleImageView profile;
    String userid;
    TextView name,city;
    String Museumname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mustsee=findViewById(R.id.mustseeid);
        events=findViewById(R.id.eventsid);
        tickets=findViewById(R.id.ticketsid);
        quicktour=findViewById(R.id.quicktourid);
        arts=findViewById(R.id.artsid);
        stores=findViewById(R.id.storesid);
        profile=findViewById(R.id.profileid);
        name=findViewById(R.id.name);
        city=findViewById(R.id.city);
        userid= FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query checkuser = FirebaseDatabase.getInstance().getReference("Museums").child(userid);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String adress1 = snapshot.child("adress").getValue(String.class);
                String city1 = snapshot.child("city").getValue(String.class);
                String name1 = snapshot.child("name").getValue(String.class);


                if(name1!=null) {
                    String image = snapshot.child("img").getValue().toString();
                    name.setText(name1);
                    city.setText(city1);
                    Museumname = name1;
                    Picasso.get().load(image).placeholder(R.drawable.profile).into(profile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        mustsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MustSee.class);
                i.putExtra("name",Museumname);
                startActivity(i);
            }
        });
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,EventsActivity.class);
                i.putExtra("name",Museumname);

                startActivity(i);
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,TicketsActivity.class);
                i.putExtra("name",Museumname);

                startActivity(i);
            }
        });
        quicktour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,QuickTour.class);
                i.putExtra("name",Museumname);

                startActivity(i);
            }
        });
        arts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                i.putExtra("name",Museumname);

                startActivity(i);
            }
        });
        stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,StoresActivity.class);
                i.putExtra("name",Museumname);

                startActivity(i);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,ProfileActivity.class);
                i.putExtra("name",Museumname);

                startActivity(i);
            }
        });


    }
}