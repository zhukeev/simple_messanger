package com.example.quicklist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quicklist.R;
import com.example.quicklist.model.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListEventAdapter extends RecyclerView.Adapter<ListEventAdapter.MyViewHolder> {

    private static final String TAG = "ListEventAdapter";
    DatabaseReference listsRef;

    Context context;
    List<Event> events = new ArrayList<>();

    public ListEventAdapter(Context context, List<Event> events) {
//        this.events = events;
        this.context = context;
        listChildEventListener();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.bind(position);
        holder.setIcon(Uri.fromFile(new File(context.getCacheDir() + events.get(position).getIcon().replace("%20"," "))));
        holder.setTitle(events.get(position).getTitle());
        holder.setDateTime(events.get(position).getDatetime());
        holder.setColor(events.get(position).getColor());
    }


    @Override
    public int getItemCount() {
        return events.size();
    }


    private void listChildEventListener() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        listsRef = FirebaseDatabase.getInstance().getReference().child("lists").child(currentUser.getUid());

        listsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<Event> events = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Event event = snapshot.getValue(Event.class);

                    event.setKey(snapshot.getKey());
                    events.add(event);

                    Log.e(TAG, "onDataChange: "+event.toString() );
                }

                setEvenList(events);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void setEvenList(List<Event> evenList) {
        this.events = evenList;
        notifyDataSetChanged();
    }

    public void removeList(int pos) {

        Log.e(TAG, "removeList: "+pos );
        Log.e(TAG, "removeList: "+events.get(pos) );

        listsRef.child(events.get(pos).getKey()).removeValue().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
        });
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDateTime;
        CardView cardView;
        View roundView;
        FrameLayout front_fl;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.event_icon);
            textViewDateTime = itemView.findViewById(R.id.datetime_event_item);
            textViewTitle = itemView.findViewById(R.id.title_event_item);
            cardView = itemView.findViewById(R.id.event_card);
            roundView = itemView.findViewById(R.id.round_holder_fl);
            front_fl = itemView.findViewById(R.id.front);

        }

        public void setTitle(String title) {
            textViewTitle.setText(title);
        }

        public void setDateTime(String dateTime) {
            textViewDateTime.setText(dateTime);
        }

        public void setIcon(Uri icon) {
            imageView.setImageURI(icon);
        }

        public void setColor(String color) {
            front_fl.setBackgroundColor(Color.parseColor(color));
        }


        public void bind(final int position) {
//            textViewTitle.setText(events.get(position).getTitle());
//            textViewDateTime.setText(events.get(position).getDatetime());
//            imageView.setImageResource(events.get(position).getImage_url());
//            front_fl.setBackgroundColor(Color.parseColor(events.get(position).getColor()));
        }
    }

}
