// Dans SuccessActivity.java
package com.projet.progmobil1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SuccessActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        TextView tvNumero = findViewById(R.id.tvNumero);
        Button btnAppeler = findViewById(R.id.btnAppeler);

        String tel = getIntent().getStringExtra("EXTRA_TEL");
        tvNumero.setText(tel);

        btnAppeler.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + tel));
            startActivity(callIntent);
        });
    }
}