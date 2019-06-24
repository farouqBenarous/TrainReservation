package com.example.trainreservation;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.angmarch.views.NiceSpinner;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import at.markushi.ui.CircleButton;

public class AddtripActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference ref , ref1,ref2;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    ModelTrip  modelTrip ;

    DatePickerDialog.OnDateSetListener MdateSet1 , MdateSet2;
    String [] wilaya = {"ADRAR ","AIN DEFLA ","AIN TEMOUCHENT ","AL TARF ","ALGER ","ANNABA ","B.B.ARRERIDJ ","BATNA "
            ,"BECHAR ","BEJAIA ","BISKRA ","BLIDA ","BOUIRA","BOUMERDES ","CHLEF ","CONSTANTINE ","DJELFA "
            ,"EL BAYADH ","EL OUED ","GHARDAIA ","GUELMA ","ILLIZI ","JIJEL ","KHENCHELA ","LAGHOUAT " ,
            "MASCARA ","MEDEA ","MILA ","MOSTAGANEM ","M’SILA","NAAMA ","ORAN ","OUARGLA ","OUM ELBOUAGHI ","RELIZANE ",
            "SAIDA","SETIF","SIDI BEL ABBES ","SKIKDA ","SOUKAHRAS ","TAMANGHASSET ","TEBESSA ","TIARET ","TINDOUF",
            "TIPAZA ","TISSEMSILT","TIZI OUZOU","TLEMCEN"};

    TextView btndeparturedate,btnarrivaldate ;
    int DayD , YearD , MonthD , DayA , YearA , MonthA ;
    AutoCompleteTextView depart , arrival ;
    NiceSpinner Traintypes,TripTypes;
    List<String> datasetTraintypes , datasetTripTypes ;
    int TrainPosition , TripPosition ;
    EditText passanger ;
    CircleButton btnplus , btnminus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtrip);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference();
        ref1 = firebaseDatabase .getReference();
        ref2 = firebaseDatabase .getReference();
        firebaseAuth = FirebaseAuth.getInstance() ;
        firebaseUser = firebaseAuth.getCurrentUser();

        btnarrivaldate = (TextView) findViewById(R.id.arivaldate);
        btndeparturedate = (TextView) findViewById(R.id.departuredate) ;
        depart = (AutoCompleteTextView) findViewById(R.id.position);
        arrival = (AutoCompleteTextView) findViewById(R.id.destination);
        passanger = (EditText) findViewById(R.id.passanger) ;
        passanger.setText("0");

        Traintypes = (NiceSpinner) findViewById(R.id.traintype);
        TripTypes =(NiceSpinner) findViewById(R.id.triptype);





        datasetTraintypes = new LinkedList<>(Arrays.asList("Coradia", "Clasical"));
        Traintypes.attachDataSource(datasetTraintypes);
        Traintypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TrainPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        datasetTripTypes = new LinkedList<>(Arrays.asList("Round Trip ", "One Way "));
        TripTypes.attachDataSource(datasetTripTypes);
        TripTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TripPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.select_dialog_item,wilaya);

        depart.setThreshold(1);
        depart.setAdapter(adapter);

        arrival.setThreshold(1);
        arrival.setAdapter(adapter);



        btndeparturedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                YearD  = cal.get(Calendar.YEAR);
                MonthD  = cal.get(Calendar.MONTH);
                DayD  = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddtripActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        MdateSet1,YearD,MonthD,DayD);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }});
        MdateSet1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                DayD = day;
                YearD = year;
                MonthD = month+1;
                btndeparturedate.setText(DayD + "/" + MonthD + "/" + YearD );

            }};



        btnarrivaldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                YearA  = cal.get(Calendar.YEAR);
                MonthA  = cal.get(Calendar.MONTH);
                DayA  = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddtripActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        MdateSet2,YearA,MonthA,DayA);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }});
        MdateSet2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                DayA = day;
                YearA = year;
                MonthA = month+1;
                btnarrivaldate.setText(DayA + "/" + MonthA + "/" + YearA );

            }};


    }

    public  void plus(View view) {
        int nm = Integer.valueOf(passanger.getText().toString()) + 1 ;
        passanger.setText(String.valueOf(nm));
    }
    public  void minus(View view) {
        int nm = Integer.valueOf(passanger.getText().toString()) - 1 ;
        passanger.setText(String.valueOf(nm));
    }


    public void addtrip(View view) {
        String departure =  String.valueOf(depart.getText());
        String arival = String.valueOf(arrival.getText());
        String triptype =datasetTripTypes.get(TripPosition);
        String departuredate =  DayD + "/" + MonthD + "/" + YearD ;
        String arrivaldate = DayA + "/" + MonthA + "/" + YearA  ;
        String number_of_ppl = String.valueOf(passanger.getText());
        String traintype = datasetTraintypes.get(TrainPosition);

        modelTrip = new ModelTrip(departure , arival , triptype , departuredate , arrivaldate , number_of_ppl , traintype);
        ref.child("tripsid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int tripid = Integer.valueOf(String.valueOf(dataSnapshot.getValue())) ;

                ref1.child("Trips").child(String.valueOf(tripid +1)).setValue(modelTrip) ;

                ref2.child("tripsid").setValue(String.valueOf(tripid +1));

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}}) ;

    }
}
