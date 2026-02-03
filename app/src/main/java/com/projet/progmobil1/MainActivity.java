package com.projet.progmobil1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST_HONOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnJava = findViewById(R.id.btnVersionJava);
        btnJava.setOnClickListener(v -> {    Intent intent = new Intent(MainActivity.this, JavaFormulaireActivity.class);
            startActivity(intent);
        });

        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
    }
}