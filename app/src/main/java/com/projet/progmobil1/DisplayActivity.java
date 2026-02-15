package com.projet.progmobil1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autre_page);

        TextView tvRecap = findViewById(R.id.tvRecap);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnRetour = findViewById(R.id.btnRetour);

        // Récupération des données passées par l'Intent
        Intent intent = getIntent();
        String displayString = "Nom: " + intent.getStringExtra("EXTRA_NOM") + " \n"
                + "Prénom: " + intent.getStringExtra("EXTRA_PRENOM") + "\n"
                + "Age: " + intent.getStringExtra("EXTRA_AGE") + "\n"
                + "Secteur: " + intent.getStringExtra("EXTRA_SECTEUR") + "\n"
                + "Tel: " + intent.getStringExtra("EXTRA_TEL");

        tvRecap.setText(displayString);

        // Bouton OK -> 3ème activité
        btnOk.setOnClickListener(v -> {
            Intent intentNext = new Intent(DisplayActivity.this, SuccessActivity.class);
            String tel = getIntent().getStringExtra("EXTRA_TEL");
            intentNext.putExtra("EXTRA_TEL", tel);
            startActivity(intentNext);
        });

        // Bouton Retour -> Ferme l'activité actuelle pour revenir à la précédente
        btnRetour.setOnClickListener(v -> finish());
    }
}