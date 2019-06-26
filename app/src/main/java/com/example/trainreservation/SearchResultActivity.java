package com.example.trainreservation;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.Objects;

public class SearchResultActivity<modelNotifications> extends AppCompatActivity {
    RecyclerView recyclerView ;
    FirebaseRecyclerAdapter<ModelTrip, TripHolder> firebaseRecyclerAdapter;
    FirebaseDatabase database;
    DatabaseReference reference,reference1,reference2,reference3,reference4;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String depdate , nbplaces , traintype , dep,arv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        depdate = intent.getStringExtra("depdate");
        nbplaces = intent.getStringExtra("nbplaces");
        traintype = intent.getStringExtra("traintype");
        dep = intent.getStringExtra("dep");
        arv = intent.getStringExtra("arv");

        recyclerView = (RecyclerView) findViewById(R.id.results);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    @Override
    protected void onStart() {
        super.onStart();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");
        reference3 = database.getReference("User");
        reference2 = database.getReference("User");
        reference1 = database.getReference("User");
        reference4 = database.getReference("User");
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();



        Query query = FirebaseDatabase.getInstance().getReference().child("Trips").getRef();
        FirebaseRecyclerOptions<ModelTrip> options = new FirebaseRecyclerOptions.Builder<ModelTrip>()
                .setQuery(query,ModelTrip.class)
                .build();
        firebaseRecyclerAdapter = new
                FirebaseRecyclerAdapter<ModelTrip,TripHolder>(options) {
                    @NonNull
                    @Override
                    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        // Create a new instance of the ViewHolder, in this case we are using a custom
                        // layout called R.layout.message for each item
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_trip, parent, false);
                        return new TripHolder(view);
                    }

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    protected void onBindViewHolder(@NonNull TripHolder viewHolder, final int position, @NonNull final ModelTrip model) {


                        viewHolder.setdeparv(model.getDeparture(), model.getArrival());
                        viewHolder.setdate(model.getDeparturedate(), model.getArrivaldate());
                        viewHolder.setnbplaces(model.getNumber_passanger());
                        viewHolder.settraintype(model.getTraintype());
/*

                        if (!Objects.equals(model.getDeparture(),dep ) ) {
                            viewHolder.hide();
                        }

                        if (!Objects.equals(model.getArrival(),arv ) ) {
                            viewHolder.hide();
                        }

                        if (!Objects.equals(model.getDeparturedate(), depdate) ) {
                            viewHolder.hide();
                        }

                        if (!Objects.equals(model.getDeparturedate(), depdate) ) {
                            viewHolder.hide();
                        }

                        if (Integer.valueOf(model.getNumber_passanger()) < Integer.valueOf(nbplaces))  {
                            viewHolder.hide();
                        }

                    if (!Objects.equals(model.getTraintype(), traintype) )  {
                           viewHolder.hide();
                       }
*/

                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myIntent = new Intent(getApplicationContext(), TripShowBiggerActivity.class);
                                myIntent.putExtra("id", model.getID());
                                startActivity(myIntent);

                            }
                        });



                    }
        } ;



        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }

    public class TripHolder extends RecyclerView.ViewHolder {
        View mView;
        CardView cardView ;

        public TripHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }

        public void setdeparv(String dep ,String arv ) {
            TextView deparv = mView.findViewById(R.id.deparv);
            deparv.setText(dep + " ---> "+arv );
        }

        public void setdate(String date1 , String date2) {
            TextView Date = mView.findViewById(R.id.date);
            Date.setText("Dep Date : "+date1 +" Arrival Date : "+date2);
        }

        public void setnbplaces(String nbplaces) {
            TextView nb = mView.findViewById(R.id.nbplaces);
            nb.setText("Only "+nbplaces+" Places left");
        }

        public void settraintype(String traintype) {
            TextView train = mView.findViewById(R.id.traintype);
            train.setText(traintype);
        }

        public  void  hide () {
            cardView = mView.findViewById(R.id.cardview);
            cardView.setLayoutParams(new FrameLayout.LayoutParams(0,0));

    }

}


        }
