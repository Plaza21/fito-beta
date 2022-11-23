package com.ppalacios.fito_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.ppalacios.fito_beta.R;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;


public class SearchActivity extends AppCompatActivity {

    Button bttnMaps, bttnCamera;
    ListView plantList;
    SearchView SearchView;
    String[] list = {"Aloe Vera", "Romaní", "Camamilla"};

    //private DatabaseReference DB;

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //DB = FirebaseDatabase.getInstance().getReference();

        SearchView = findViewById(R.id.SearchView);
        plantList = findViewById(R.id.plantList);

        bttnMaps = findViewById(R.id.bttnMaps);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        plantList.setAdapter(arrayAdapter);
        plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(SearchActivity.this,WikiPlantes.class);
                SearchActivity.this.startActivity(intent);

                };

        });


        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.this.arrayAdapter.getFilter().filter(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchActivity.this.arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });

        bttnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, MapsActivity.class));
            }
        });

       /* arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.listview_textcolor, list);

        plantList.setAdapter(arrayAdapter);*/
    }

}