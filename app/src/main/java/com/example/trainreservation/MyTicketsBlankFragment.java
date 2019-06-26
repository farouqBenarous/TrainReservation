package com.example.trainreservation;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTicketsBlankFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<ModelTicket,TicketHolder > firebaseRecyclerAdapter;
    FirebaseDatabase database;
    DatabaseReference reference,reference1,reference2,reference3,reference4;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String depdate , nbplaces , traintype , dep,arv;


    public MyTicketsBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_tickets_blank, container, false);


        recyclerView = view.findViewById(R.id.mytickets);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("User");
        reference3 = database.getReference("User");
        reference2 = database.getReference("User");
        reference1 = database.getReference("User");
        reference4 = database.getReference("User");
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();



        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).child("MyTickets").getRef();
        FirebaseRecyclerOptions<ModelTicket> options = new FirebaseRecyclerOptions.Builder<ModelTicket>()
                .setQuery(query,ModelTicket.class)
                .build();
        firebaseRecyclerAdapter = new
                FirebaseRecyclerAdapter<ModelTicket,TicketHolder>(options) {
                    @NonNull
                    @Override
                    public TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        // Create a new instance of the ViewHolder, in this case we are using a custom
                        // layout called R.layout.message for each item
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_tickets, parent, false);
                        return new TicketHolder(view);
                    }

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    protected void onBindViewHolder(@NonNull TicketHolder viewHolder, final int position, @NonNull final ModelTicket model) {


                        viewHolder.setdeparv(model.getDep(), model.getArv());
                        viewHolder.setdate(model.getDepdate(), model.getArvdate());
                        viewHolder.setnbplaces(model.getNbplaces());
                        //viewHolder.settprice(model.getPrice());


                    }
                } ;



        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();





    }

    public class TicketHolder extends RecyclerView.ViewHolder {
        View mView;
        CardView cardView ;

        public TicketHolder(View itemView) {
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
            nb.setText("You have Reserved "+nbplaces);
        }

        public void settprice(String price) {
            TextView p = mView.findViewById(R.id.traintype);
            p.setText(price);
        }

        public  void  hide () {
            cardView = mView.findViewById(R.id.cardviewticket);
            cardView.setLayoutParams(new FrameLayout.LayoutParams(0,0));

        }

    }



}
