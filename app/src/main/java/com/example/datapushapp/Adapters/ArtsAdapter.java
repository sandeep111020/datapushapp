package com.example.datapushapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.datapushapp.R;
import com.example.datapushapp.Vendoritemmodel;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ArtsAdapter extends FirebaseRecyclerAdapter<Vendoritemmodel, ArtsAdapter.myviewholder> {

    String date,taskid,empid;

    Context context;
    DatabaseReference databaseRef;
    String passvalue,name;

    public ArtsAdapter(@NonNull FirebaseRecyclerOptions<Vendoritemmodel> options, Context context, String passvalue,String name) {
        super(options);
        this.context = context;
        this.passvalue=passvalue;
        this.name=name;

    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Vendoritemmodel model) {

        String chek;

        holder.artname.setText(""+model.getAname());
        holder.artistname.setText("Artist: "+model.getArtist());
        holder.idnum.setText("ID NO: "+model.getNumber());
        holder.desc.setText("Description: "+model.getDesc());
        holder.loc.setText("Location: "+model.getLocation());

        Picasso.get().load(model.getImg()).into(holder.img);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(passvalue).child(name);
        final DatabaseReference itemRef = getRef(position);
        final String myKey = itemRef.getKey();

        holder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                databaseReference.child(myKey).removeValue();
                Toast.makeText(context,model.getAname()+"  is Deleted From Your Favorites",Toast.LENGTH_SHORT).show();

                return false;
            }
        });








    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artviewlayout, parent, false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView artname,artistname,idnum,desc,loc;
        ImageView img;




        public myviewholder(@NonNull View itemView) {
            super(itemView);



            artname=itemView.findViewById(R.id.artname);
            artistname=itemView.findViewById(R.id.artistname);
            idnum=itemView.findViewById(R.id.idnum);
            desc=itemView.findViewById(R.id.desc);
            loc=itemView.findViewById(R.id.loc);
            img=itemView.findViewById(R.id.img);








        }
    }



}