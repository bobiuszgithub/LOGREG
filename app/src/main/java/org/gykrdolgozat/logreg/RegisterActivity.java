package org.gykrdolgozat.logreg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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


        if (!teljesnev.contains(" "))
        {
            Toast.makeText(this, "Helytelen Teljes név", Toast.LENGTH_LONG).show();
            return;
        }

        boolean emailcheck = adatbazis.EmailCheck(email);
        if (emailcheck) {
            Toast.makeText(this, "foglalt E-mail", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            boolean sikeres = adatbazis.adatFelvetel(email, felhnev, jelszo, teljesnev);
            if (sikeres) {
                Toast.makeText(this, "Sikeres regisztráció", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Sikertelen regisztráció", Toast.LENGTH_LONG).show();
            }

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
        btnRegister.setEnabled(false);
        editTextemail.addTextChangedListener(regisztraciomezok);
        editTextfelhnev.addTextChangedListener(regisztraciomezok);
        editTextjelszo.addTextChangedListener(regisztraciomezok);
        editTextteljesnev.addTextChangedListener(regisztraciomezok);

    }
    private TextWatcher regisztraciomezok = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String email = editTextemail.getText().toString().trim();
            String felhnev = editTextfelhnev.getText().toString().trim();
            String jelszo = editTextjelszo.getText().toString().trim();
            String teljesnev = editTextteljesnev.getText().toString().trim();

            btnRegister.setEnabled(!email.isEmpty() && !felhnev.isEmpty() && !jelszo.isEmpty() && !teljesnev.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {
            String email = editTextemail.getText().toString().trim();
            String felhnev = editTextfelhnev.getText().toString().trim();
            if (adatbazis.EmailCheck(email) || !email.contains("@")){
                editTextemail.setTextColor(Color.rgb(255,0,0));
            }else{
                editTextemail.setTextColor(Color.rgb(0,255,0));
            }
            if (adatbazis.FelhnevCheck(felhnev)){
                editTextfelhnev.setTextColor(Color.rgb(255,0,0));
            }else{
                editTextfelhnev.setTextColor(Color.rgb(0,255,0));
            }
        }
    };
}
