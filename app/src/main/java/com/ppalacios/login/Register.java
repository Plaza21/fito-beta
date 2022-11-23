package com.ppalacios.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private EditText firstName;
    private EditText email;
    private EditText password;
    private EditText password2;
    private Button confirmar, bttnLogin;
    boolean checkerror = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Mappeig dels elements
        firstName = findViewById(R.id.textPersonId);
        email = findViewById(R.id.emailadressId);
        password = findViewById(R.id.password1Id);
        password2 = findViewById(R.id.password2Id);
        confirmar = (Button) findViewById(R.id.button3);
        bttnLogin = (Button) findViewById(R.id.bttnLogin);

        confirmar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                checkDataEntered();

                if (checkerror == false) {
                    startActivity(new Intent(Register.this, Login.class));
                }

            }
        });

        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty (EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    void checkDataEntered() {

        if (isEmpty(firstName)) {
            firstName.setError("El nom d'usuari no pot estar buit!");

            checkerror = true;

        }

        if (isEmail(email) == false) {
            email.setError("Correu invalid");

            checkerror = true;
        }

        if (isEmpty(password) || (password.length() < 8)) {
            password.setError("Contrasenya invalida");

            checkerror = true;
        }

        String strPass1 = password.getText().toString();
        String strPass2 = password2.getText().toString();

        if (!strPass1.equals(strPass2)) {

            checkerror = true;
            /* Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_LONG);
            t.show(); */


            password2.setError("Les contrasenyas no són iguals");
        }
        if (strPass1.equals(strPass2) && !strPass2.isEmpty()) {

            checkerror = false;
            /* Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_LONG);
            t.show(); */

        }


    }


}