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

//    private boolean Adatmodositas() {
//
//        String jelszo = txtJelszo.getText().toString();
//        boolean bennevan = true;
//
//        if (!adatbazis.NevEllen(felhnev)) {
//            Toast.makeText(this, "van ilyen Felhasználó", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "nincs ilyen Felhasználó", Toast.LENGTH_SHORT).show();
//            bennevan = false;
//        }
//
//        return bennevan;
//    }

    private void onClickLisener() {
        btnBejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String felhnev = txtJelszo.getText().toString();
                txtJelszo.setText(adatbazis.NevEllen(felhnev)+ "");
//                if ()){
//                    Intent bejelentkezes = new Intent(getApplicationContext(), LoggedInActivity.class);
//                    startActivity(bejelentkezes);
//                    finish();
//                }

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