package com.ppalacios.fito_beta;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Add extends AppCompatActivity {

    EditText nomCientific, localitzacio, partsUtils, propietats, formula, cultius;
    FirebaseFirestore db;
    Button save;
    String strNomCientific, strLocalitzacio, strPartsUtils, strPropietats, strFormula, strCultius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Map<String, Object> planta = new HashMap<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        nomCientific = findViewById(R.id.nomCientific);
        localitzacio = findViewById(R.id.localitzacio);
        partsUtils = findViewById(R.id.partsUtils);
        propietats = findViewById(R.id.propietats);
        formula = findViewById(R.id.formula);
        cultius = findViewById(R.id.cultius);

        save = findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strNomCientific = nomCientific.getText().toString();
                strLocalitzacio = localitzacio.getText().toString();
                strPartsUtils = partsUtils.getText().toString();
                strPropietats = propietats.getText().toString();
                strFormula = formula.getText().toString();
                strCultius = cultius.getText().toString();

                db.collection("plantes").document(strNomCientific).set(planta);
            }
        });
    }
}