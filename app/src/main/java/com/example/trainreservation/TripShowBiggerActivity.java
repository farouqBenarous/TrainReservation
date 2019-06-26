package com.example.trainreservation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class TripShowBiggerActivity extends AppCompatActivity {
    String id_trip , n_b  , devarv , id_ticktet , dep ,arv  , depdate , arvdate;
    ImageView imageheader;
    TextView deparv ,date,nbplaces,traintype,triptype ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference ref , ref1 , ref2 , ref3 , ref4 , ref5;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser;
    ModelTrip modelTrip;
    ModelTicket modelTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_show_bigger);
        imageheader = (ImageView) findViewById(R.id.header_cover_image) ;
        deparv = (TextView) findViewById(R.id.deparv);
        date = (TextView) findViewById(R.id.date);
        nbplaces = (TextView) findViewById(R.id.nbplaces);
        traintype = (TextView) findViewById(R.id.traintype);
        triptype = (TextView) findViewById(R.id.triptype);


        Picasso.with(getApplicationContext())
                .load("https://www.industryweek.com/sites/industryweek.com/files/styles/article_featured_retina/public/uploads/2017/05/teamwork.jpg?itok=U5ZihnwP")
                .into(imageheader);

        Intent intent = getIntent();
        id_trip = intent.getStringExtra("id");

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference();
        ref1 = firebaseDatabase.getReference();
        ref2 = firebaseDatabase.getReference();
        ref3 = firebaseDatabase.getReference();
        ref4= firebaseDatabase.getReference();
        ref5 = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        ref.child("Trips").orderByChild("id").equalTo(id_trip).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelTrip = dataSnapshot.getValue(ModelTrip.class);
                n_b = modelTrip.getNumber_passanger();
                devarv =modelTrip.getDeparture() + "  --->  "+modelTrip.getArrival() ;
                dep = modelTrip.getDeparture();
                arv = modelTrip.getArrival() ;
                depdate = modelTrip.getDeparturedate();
                arvdate = modelTrip.getArrivaldate();

                deparv.setText(modelTrip.getDeparture() +"  --->  "+modelTrip.getArrival());
                date.setText(modelTrip.getDeparturedate() +"  --->  "+modelTrip.getArrivaldate());
                nbplaces.setText("Number of places : "+modelTrip.getNumber_passanger());
                traintype.setText("Train trype :"+modelTrip.getTraintype());
                triptype.setText("Trip type : "+modelTrip.getTriptype());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}}) ;
        ref4.child("tripsid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             id_ticktet = dataSnapshot.getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});

    }

    public  void  book (View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(TripShowBiggerActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogue_confirm_trip, null);

        final EditText place = (EditText) mView.findViewById(R.id.place);
        place.setHint("place left "+n_b);

        Button reserver = (Button) mView.findViewById(R.id.reserver);

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String placereserved = place.getText().toString();
                if (TextUtils.isEmpty(placereserved)) {
                    Toast.makeText(getApplicationContext(),"Enter Place Number !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Integer.parseInt(placereserved )>Integer.parseInt(n_b) ) {
                    Toast.makeText(getApplicationContext(),"Only   "+n_b+"  left",Toast.LENGTH_LONG).show();
                    return;
                }
                ref1.child("Trips").orderByChild("id").equalTo(id_trip).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ref2.child("Trips").child(dataSnapshot.getKey()).child("nbPlace")
                                .setValue(Integer.parseInt(n_b)-Integer.parseInt(placereserved)).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                modelTicket = new ModelTicket(id_ticktet,dep,arv,depdate,arvdate,n_b,"0");
                                ref3.child("Users").child(firebaseUser.getUid()).child("MyTickets").child(id_ticktet).setValue(modelTicket);
                                ref5.child("tripsid").setValue(String.valueOf(Integer.valueOf(id_ticktet) +1)) ;

                                android.support.v7.app.AlertDialog.Builder adb = new android.support.v7.app.AlertDialog.Builder(TripShowBiggerActivity.this);
                                adb.setTitle(" Trip successfully Reserved    ");
                                adb.setMessage("You Have Reserved  "+placereserved+" Place in The Trip  "+devarv+"+");
                                adb.setCancelable(true);

                                adb.setPositiveButton(" Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }});
                                adb.show();
                            }
                        }); }
                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {}
                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                    @Override
                    public void onCancelled(DatabaseError databaseError) {}});

            }
        });

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

    }
}
