package com.example.trainreservation;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import at.markushi.ui.CircleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookTripFragment extends Fragment {
    DatePickerDialog.OnDateSetListener MdateSet1,MdateSet2;
    String [] wilaya = {"ADRAR ","AIN DEFLA ","AIN TEMOUCHENT ","AL TARF ","ALGER ","ANNABA ","B.B.ARRERIDJ ","BATNA "
            ,"BECHAR ","BEJAIA ","BISKRA ","BLIDA ","BOUIRA","BOUMERDES ","CHLEF ","CONSTANTINE ","DJELFA "
            ,"EL BAYADH ","EL OUED ","GHARDAIA ","GUELMA ","ILLIZI ","JIJEL ","KHENCHELA ","LAGHOUAT " ,
            "MASCARA ","MEDEA ","MILA ","MOSTAGANEM ","M’SILA","NAAMA ","ORAN ","OUARGLA ","OUM ELBOUAGHI ","RELIZANE ",
            "SAIDA","SETIF","SIDI BEL ABBES ","SKIKDA ","SOUKAHRAS ","TAMANGHASSET ","TEBESSA ","TIARET ","TINDOUF",
            "TIPAZA ","TISSEMSILT","TIZI OUZOU","TLEMCEN"};

   Button btndeparturedate,btnarrivaldate ;
   Button btnsearch ;
   int DayD , YearD , MonthD , DayA , YearA , MonthA ;
   AutoCompleteTextView depart , arrival ;
   NiceSpinner Traintypes,TripTypes;
   List<String> datasetTraintypes , datasetTripTypes ;
   int TrainPosition , TripPosition ;
   EditText passanger ;
   CircleButton btnplus , btnminus ;
   String depdate  ;


    public BookTripFragment() {}


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_book_trip, container, false);

        btnplus = view.findViewById(R.id.plus);
        btnminus = view.findViewById(R.id.minus);
        passanger = view.findViewById(R.id.passanger) ;
        passanger.setText("0");


        Traintypes = view.findViewById(R.id.traintype) ;
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


        TripTypes = view.findViewById(R.id.triptype) ;
        datasetTripTypes = new LinkedList<>(Arrays.asList("Round Trip ", "One Way "));
        TripTypes.attachDataSource(datasetTripTypes);
        TripTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TripPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}});




        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getContext(),android.R.layout.select_dialog_item,wilaya);

        btndeparturedate = view.findViewById(R.id.departuredate) ;
        btnarrivaldate = view.findViewById(R.id.arivaldate);
        btnsearch = view.findViewById(R.id.search);

        depart = view.findViewById(R.id.position);
        depart.setThreshold(1);
        depart.setAdapter(adapter);

        arrival = view.findViewById(R.id.destination);
        arrival.setThreshold(1);
        arrival.setAdapter(adapter);

        btndeparturedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                YearD  = cal.get(Calendar.YEAR);
                MonthD  = cal.get(Calendar.MONTH);
                DayD  = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                depdate = DayD + "/" + MonthD + "/" + YearD ;
                btndeparturedate.setText(DayD + "/" + MonthD + "/" + YearD );
                Toast.makeText(getContext(), DayD + "/" + MonthD + "/" + YearD , Toast.LENGTH_SHORT).show();
            }};



        btnarrivaldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                YearA  = cal.get(Calendar.YEAR);
                MonthA  = cal.get(Calendar.MONTH);
                DayA  = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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


        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int nm = Integer.valueOf(passanger.getText().toString()) + 1 ;
                    passanger.setText(String.valueOf(nm));

            }
        });

        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nm = Integer.valueOf(passanger.getText().toString()) -1;
                passanger.setText(String.valueOf(nm));
            }
        });


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), SearchResultActivity.class);
                myIntent.putExtra("depdate", depdate );
                myIntent.putExtra("nbplaces",  passanger.getText() );
                myIntent.putExtra("traintype", datasetTraintypes.get(TrainPosition));
                myIntent.putExtra("dep",depart.getText() );
                myIntent.putExtra("arv", arrival.getText());
                startActivity(myIntent);
            }
        });

        // Inflate the layout for this fragment
        return  view;
    }

}
