package com.example.datapushapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TicketsActivity extends AppCompatActivity {

    String museumname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        museumname= getIntent().getStringExtra("name");

    }
}