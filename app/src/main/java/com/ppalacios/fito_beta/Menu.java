package com.ppalacios.fito_beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.ppalacios.fito_beta.databinding.ActivityMainBinding;

public class Menu extends AppCompatActivity {

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nav = findViewById(R.id.bottomAppBar);

        nav.setSelectedItemId(R.id.item1);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.item1:
                        Toast.makeText(Menu.this, "Search", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.item2:
                        Toast.makeText(Menu.this, "Receptes", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.item3:
                        Toast.makeText(Menu.this, "Camara", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.item4:
                        Toast.makeText(Menu.this, "Google Maps", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item5:
                        Toast.makeText(Menu.this, "Afegir", Toast.LENGTH_SHORT).show();

                        break;

                    default:
                }

                return false;
            }
        });

    }
}