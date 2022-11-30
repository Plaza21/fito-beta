package com.ppalacios.fito_beta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ppalacios.fito_beta.R;

public class Camera extends AppCompatActivity {

    //static final int REQUEST_IMAGE_CAPTURE = 1;
    //Button btn_camera;

    ImageView imageView;
    Button clickbtn;

    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.image_view);
        clickbtn = findViewById(R.id.click_btn);

        nav = findViewById(R.id.bottomAppBar);

        nav.setSelectedItemId(R.id.item1);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.item1:
                        Toast.makeText(Camera.this, "Search", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item2:
                        Toast.makeText(Camera.this, "Receptes", Toast.LENGTH_SHORT).show();

                        break;

                    case R.id.item3:
                        Toast.makeText(Camera.this, "Camara", Toast.LENGTH_SHORT).show();

                        return true;

                    case R.id.item4:
                        Toast.makeText(Camera.this, "Google Maps", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item5:
                        Toast.makeText(Camera.this, "Afegir", Toast.LENGTH_SHORT).show();

                        break;

                    default:
                }

                return false;
            }
        });

        if (ContextCompat.checkSelfPermission(Camera.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Camera.this,
                    new String[]{Manifest.permission.CAMERA}, 101);
        }

        clickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    /*
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    */


}