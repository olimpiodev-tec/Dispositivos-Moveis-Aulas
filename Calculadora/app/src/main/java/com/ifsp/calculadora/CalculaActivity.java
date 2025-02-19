package com.ifsp.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculaActivity extends AppCompatActivity implements View.OnClickListener {

    private String titulo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcula);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(this);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        setarTitulo();
    }

    public void setarTitulo() {
        Intent telaHome = getIntent();
        titulo = telaHome.getStringExtra("parametro");

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        tvTitulo.setText(titulo);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnVoltar) {
            finish();
        } else if (v.getId() == R.id.btnCalcular) {
            EditText etPrimeiroNumero = findViewById(R.id.etPrimeiroNumero);
            EditText etSegundoNumero = findViewById(R.id.etSegundoNumero);

            int n1 = Integer.parseInt(etPrimeiroNumero.getText().toString());
            int n2 = Integer.parseInt(etSegundoNumero.getText().toString());
            int resultado = 0;

            if (titulo.equals("Somar Números")) {
                resultado = n1 + n2;
            } else if (titulo.equals("Subtrair Números")) {
                resultado = n1 - n2;
            } else if (titulo.equals("Multiplicar Números")) {
                resultado = n1 * n2;
            } else if (titulo.equals("Dividir Números")) {
                resultado = n1 / n2;
            }

            Toast.makeText(CalculaActivity.this,
                    "O resultado é: " + resultado,
                    Toast.LENGTH_SHORT).show();
        }
    }
}







