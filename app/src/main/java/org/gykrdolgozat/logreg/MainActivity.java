package org.gykrdolgozat.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnBejelentkezes, btnRegisztracio;
    EditText txtFelhasznalo, txtJelszo;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ini();
        onClickLisener();
    }


    private void belepes() {
        String nev = txtFelhasznalo.getText().toString().trim();
        String jelsz = txtJelszo.getText().toString().trim();

        if (nev.isEmpty() || jelsz.isEmpty()) {
            Toast.makeText(MainActivity.this, "Nem adott meg nevet vagy jelszót.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean felhell = adatbazis.Logincheck(nev, jelsz);

        if (felhell) {
            String datrt = txtFelhasznalo.getText().toString().trim();
            Intent passdat_int = new Intent(MainActivity.this, LoggedInActivity.class);
            passdat_int.putExtra("data1", datrt);
            startActivity(passdat_int);
            finish();
        } else {
            Toast.makeText(MainActivity.this, "Hibás User vagy jelszó.", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    private void onClickLisener() {
        btnBejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belepes();
            }
        });
        btnRegisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
                finish();
            }
        });


        // textAdatok.setMovementMethod(new ScrollingMovementMethod());

    }


    private void ini() {

        btnBejelentkezes = findViewById(R.id.btn_main_login);
        btnRegisztracio = findViewById(R.id.btn_main_register);
        txtFelhasznalo = findViewById(R.id.edittext_main_fhnev);
        txtJelszo = findViewById(R.id.edittext_main_jelszo);
        adatbazis = new DBHelper(MainActivity.this);

    }
}