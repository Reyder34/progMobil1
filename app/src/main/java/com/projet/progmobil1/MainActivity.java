package com.projet.progmobil1;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView monTextView = findViewById(R.id.mon_texte);

        monTextView.setText("Hello world ajouter par le code");

        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
    }
}