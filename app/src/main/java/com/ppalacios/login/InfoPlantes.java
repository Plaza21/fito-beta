package com.ppalacios.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import android.os.Bundle;

public class InfoPlantes extends AppCompatActivity {

    ListView plantList;
    SearchView SearchView;
    String[] list = {"Aloe Vera", "Romaní", "Camamilla"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_plantes);

        //SearchView = findViewById(R.id.SearchView);
        plantList = findViewById(R.id.plantList);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        plantList.setAdapter(arrayAdapter);
        plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(InfoPlantes.this,WikiPlantes
                        .class);
                InfoPlantes.this.startActivity(intent);
            };

        });

        /*
        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                InfoPlantes.this.arrayAdapter.getFilter().filter(query);

                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                InfoPlantes.this.arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });
        */
    }

}