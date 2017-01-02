package com.example.fujimiya.farmmart;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapterBerita extends RecyclerView.Adapter<RecycleViewHolderBerita> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> Glist_dari_judul = new ArrayList();
    public static List<String> Glist_dari_isi = new ArrayList();
    Firebase Gref;


    //dekalrasi buat List nya
    String[] nama ={"Berita 1 ","Berita 2","Berita 3"};

    
    //int ic_aja = R.drawable.greencircle;

    public RecycleAdapterBerita(final Context context) {

        inflater = LayoutInflater.from(context);
        this.context = context;
        Firebase.setAndroidContext(this.context);

        Gref = new Firebase("https://farmart-8d3e5.firebaseio.com/berita");
        Gref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Glist_dari_judul.clear();
                Glist_dari_isi.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String judul = (String) child.child("judul").getValue();
                    Glist_dari_judul.add(judul);
                    String isi = (String) child.child("isi").getValue();
                    Glist_dari_isi.add(isi);

                }
                notifyDataSetChanged();
                Toast.makeText(context.getApplicationContext(), "Data berhasil diambil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        //Glist_dari_berita.add("Berita di arraylist1");
    }


    @Override
    public RecycleViewHolderBerita onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderBerita viewHolderBerita= new RecycleViewHolderBerita(v);
       // list_dari_berita.add("Berita di arraylist1");


        return viewHolderBerita;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderBerita holder, int position) {


        //holder.checkBoxJudul.setText(nama[position]);
        //holder.txtNamaOutlet.setText(nama[position]);
        holder.txtNamaOutlet.setText(Glist_dari_judul.get(position).toString());
        holder.txtIsiBerita.setText(Glist_dari_isi.get(position).toString());

        holder.txtNamaOutlet.setOnClickListener(clicklistener);
        holder.txtIsiBerita.setOnClickListener(clicklistener);

        holder.txtNamaOutlet.setTag(holder);
        holder.txtIsiBerita.setTag(holder);

    }

    View.OnClickListener clicklistener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            RecycleViewHolderBerita vHolder = (RecycleViewHolderBerita) v.getTag();
            int position = vHolder.getPosition();

        }
    };


    public int getItemCount() {
        //return nama.length;

        return Glist_dari_judul == null ? 0 : Glist_dari_judul.size();
    }
}
