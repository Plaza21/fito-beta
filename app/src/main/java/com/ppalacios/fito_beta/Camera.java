package com.ppalacios.fito_beta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ppalacios.fito_beta.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class Camera extends AppCompatActivity {

    private static int REQUEST_CODE = 100;

    ImageView imageView;
    Button clickbtn;

    BottomNavigationView nav;
    Bitmap bitmap;

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

        } else if (ContextCompat.checkSelfPermission(Camera.this,

                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Camera.this,

                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                //Toast.makeText(this, "Si us plau, garanteix els permisos per poder emmagatzemar l'imatge", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean saveImage(String imgName, Bitmap bmp) {

        Uri imageCollection = null;
        ContentResolver resolver = getContentResolver();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            imageCollection = MediaStore.Images.Media.getContentUri((MediaStore.VOLUME_EXTERNAL_PRIMARY));

        } else {

            imageCollection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, imgName + ".jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri imageUri = resolver.insert(imageCollection, contentValues);

        try {

            OutputStream outputStream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Objects.requireNonNull(outputStream);
            Toast.makeText(this, "Imatge Guardada Exitosament!", Toast.LENGTH_SHORT).show();
            return true;

        } catch (Exception e){
            Toast.makeText(this, "Imatge no guardada", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 ) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

            saveImage(UUID.randomUUID().toString(), bitmap);
        }
    }
}