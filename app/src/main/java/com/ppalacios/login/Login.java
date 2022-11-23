package com.ppalacios.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button buttonLogin, buttonRegister;
    EditText txtEmail, txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        txtEmail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.pssw);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                //String UserEmail = "@";
                //String UserPassword = "1";

                String UserEmail = "prova@gmail.com";
                String UserPassword = "12345678";

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El Email o el Password está buit!", Toast.LENGTH_SHORT).show();
                }
                else if (!email.equals(UserEmail) || !password.equals(UserPassword)){
                    Toast.makeText(getApplicationContext(), "Email o Password erronis!", Toast.LENGTH_SHORT).show();
                }
                else if (email.equals(UserEmail) && password.equals(UserPassword)) {

                    startActivity(new Intent(Login.this, SearchActivity.class));
                    Toast.makeText(getApplicationContext(), "Sessió iniciada correctament!", Toast.LENGTH_SHORT).show();
                }

                if (!email.contains("@")) {
                    txtEmail.setError("Correu Invalid");
                };
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }

    //private void mailCorrecte() {

        //String email = txtEmail.getText().toString();
        //String password = txtPassword.getText().toString();

        // if (!email.contains("@")) {
            //txtEmail.setError("Correu Invalid");
            //};
    //}
}