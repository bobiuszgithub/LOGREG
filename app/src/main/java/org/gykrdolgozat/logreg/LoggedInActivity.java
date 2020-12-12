package org.gykrdolgozat.logreg;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoggedInActivity extends AppCompatActivity {


    TextView textNev;
    Button btnKijelentkezes;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        init();
        onClickLisener();
        adat();
    }

    private void adat() {
  /*      Cursor adatok= adatbazis.adatLekerdezes();
        StringBuffer stringBuffer = new StringBuffer();
        while (adatok.moveToNext())
        {
            Bundle bundle = getIntent().getExtras();
            String szoveg=bundle.getString("data1");
            textView =  (TextView)findViewById(R.id.teljesnev);
            textView.setText(""+adatbazis.EgyLeKerdezes(szoveg));
        }
*/      Bundle bundle = getIntent().getExtras();
        String szoveg=bundle.getString("data1");
        Cursor adatok= adatbazis.EgyLeKerdezes(szoveg);

        StringBuffer stringBuffer= new StringBuffer();
        while (adatok.moveToNext())
        {
            stringBuffer.append("Üdvözöllek "+adatok.getString(0));
        }
        textNev.setText(stringBuffer.toString());




    }
    private void onClickLisener(){
        btnKijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(vissza);
                finish();

            }
        });


    }



    private void init() {
        textNev = (TextView) findViewById(R.id.text_logged_in);
        btnKijelentkezes = findViewById(R.id.btn_loggedin_logout);
        adatbazis = new DBHelper(LoggedInActivity.this);


    }
}
