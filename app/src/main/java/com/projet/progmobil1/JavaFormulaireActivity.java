package com.projet.progmobil1;

import android.os.Bundle;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class JavaFormulaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView title = new TextView(this);
        title.setText("Version JAVA (Code)");
        title.setTextSize(20);
        layout.addView(title);

        layout.addView(creerChamp("Nom", "Saisir votre nom", InputType.TYPE_CLASS_TEXT));
        layout.addView(creerChamp("Prénom", "Saisir votre prénom", InputType.TYPE_CLASS_TEXT));
        layout.addView(creerChamp("Age", "Saisir votre âge", InputType.TYPE_CLASS_NUMBER));
        layout.addView(creerChamp("Téléphone", "06...", InputType.TYPE_CLASS_PHONE));

        Button btnValider = new Button(this);
        btnValider.setText("Valider");
        layout.addView(btnValider);

        setContentView(layout);
    }

    private LinearLayout creerChamp(String label, String hint, int inputType) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(this);
        tv.setText(label);

        EditText et = new EditText(this);
        et.setHint(hint);
        et.setInputType(inputType);

        container.addView(tv);
        container.addView(et);
        return container;
    }
}