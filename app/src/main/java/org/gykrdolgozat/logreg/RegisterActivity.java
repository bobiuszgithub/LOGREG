package org.gykrdolgozat.logreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {


    EditText editTextemail, editTextfelhnev, editTextjelszo, editTextteljesnev;
    Button btnRegister, btnVissza;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        init();
        inClickLisener();


    }

    private void inClickLisener() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatRogzites();
            }
        });

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();

            }
        });

    }

    private void adatRogzites() {
        String email = editTextemail.getText().toString().trim();
        String felhnev = editTextfelhnev.getText().toString().trim();
        String jelszo = editTextjelszo.getText().toString().trim();
        String teljesnev = editTextteljesnev.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Email meg adása kötelező", Toast.LENGTH_LONG).show();
            return;
        }
        if (felhnev.isEmpty()) {
            Toast.makeText(this, "Felhasználónév meg adása kötelező", Toast.LENGTH_LONG).show();
            return;
        }

        if (jelszo.isEmpty()) {
            Toast.makeText(this, "Jelszó meg adása kötelező", Toast.LENGTH_LONG).show();
            return;
        }
        if (teljesnev.isEmpty()) {
            Toast.makeText(this, "A teljesnév meg adása kötelező", Toast.LENGTH_LONG).show();
            return;
        }

        boolean sikeres = adatbazis.adatFelvetel(email, felhnev, jelszo, teljesnev);
        if (sikeres) {
            Toast.makeText(this, "Sikeres adat rog", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Sikertelen adat rog", Toast.LENGTH_LONG).show();
        }


    }

    private void init() {
        editTextemail = (EditText) findViewById(R.id.edittext_register_email);
        editTextfelhnev = (EditText) findViewById(R.id.edittext_register_fhnev);
        editTextjelszo = (EditText) findViewById(R.id.edittext_register_jelszo);
        editTextteljesnev = (EditText) findViewById(R.id.edittext_register_teljesnev);
        btnRegister = (Button) findViewById(R.id.btn_register_register);
        btnVissza = (Button) findViewById(R.id.btn_register_vissza);
        adatbazis = new DBHelper(RegisterActivity.this);

    }
}
