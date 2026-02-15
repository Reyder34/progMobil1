package com.projet.progmobil1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText nom, prenom, age, secteur, telephone;
    private Button btnValider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.editTextText5);
        prenom = findViewById(R.id.editTextText4);
        age = findViewById(R.id.editTextText3);
        secteur = findViewById(R.id.editTextText2);
        telephone = findViewById(R.id.editTextPhone);
        btnValider = findViewById(R.id.button2);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifierEtEnvoyer();
            }
        });
    }

    private void verifierEtEnvoyer() {
        String sNom = nom.getText().toString().trim();
        String sPrenom = prenom.getText().toString().trim();
        String sAge = age.getText().toString().trim();
        String sSecteur = secteur.getText().toString().trim();
        String sTel = telephone.getText().toString().trim();

        if (sNom.isEmpty() || sPrenom.isEmpty() || sAge.isEmpty() || sSecteur.isEmpty() || sTel.isEmpty()) {
            Toast.makeText(this, "Champs manquants", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Envoi des données", Toast.LENGTH_SHORT).show();

        }
    }
}