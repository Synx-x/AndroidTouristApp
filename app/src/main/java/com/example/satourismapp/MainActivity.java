package com.example.satourismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String place;
    String nPlaces;
    String nProvince;
    String madness;

    String[] provinces;
    String[] places;
    String[] placees;




  private TextView uiDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceDetails placeObj = new PlaceDetails();

        placees =  placeObj.getPlace();

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.province, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        uiDesc = findViewById(R.id.descriptionView);
        database sql;
        sql = new database(MainActivity.this);
        sql.open();
        sql.addPlaces();
         nProvince = sql.getProvince();
         nPlaces = sql.getPlaceDetails();

        List<String> convertedProvince = Arrays.asList(nProvince.split("\\s*,\\s*"));
        List<String> convertedPlace = Arrays.asList(nPlaces.split("\\s*,\\s*"));;

        String [] array1 = new String[convertedProvince.size()];

        for(int i = 0; i < 9; i++){
            array1[i] = convertedProvince.get(i);
        }

        String [] array3 = new String[convertedPlace.size()];

        for(int i = 0; i < 9; i++){
            array3[i] = convertedPlace.get(i);
        }

        provinces = Arrays.copyOf(array1, 9, String[].class);

        places = Arrays.copyOf(array3, 9, String[].class);


        madness= Arrays.toString(provinces);
        place = Arrays.toString(places);

        sql.close();

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();

      if(selectedItem.equals(provinces[0])){
          uiDesc.setText(places[0]);
      }else if(selectedItem.equals(provinces[1])){
          uiDesc.setText(places[1]);
      }else if(selectedItem.equals(provinces[2])){
          uiDesc.setText(places[2]);
      }else if(selectedItem.equals(provinces[3])){
          uiDesc.setText(places[3]);
      }else if(selectedItem.equals(provinces[4])){
          uiDesc.setText(places[4]);
      }else if(selectedItem.equals(provinces[5])){
          uiDesc.setText(places[5]);
      }else if(selectedItem.equals(provinces[6])){
          uiDesc.setText(places[6]);
      }else if(selectedItem.equals(provinces[7])){
          uiDesc.setText(places[7]);
      }else if(selectedItem.equals(provinces[8])){
          uiDesc.setText(places[8]);
      }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}