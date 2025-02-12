package com.ifsp.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAdicao = findViewById(R.id.btnAdicao);
        btnAdicao.setOnClickListener(this);

        Button btnSubtracao = findViewById(R.id.btnSubtracao);
        btnSubtracao.setOnClickListener(this);

        Button btnMultiplicacao = findViewById(R.id.btnMultiplicacao);
        btnMultiplicacao.setOnClickListener(this);

        Button btnDivisao = findViewById(R.id.btnDivisao);
        btnDivisao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent telaCalcula = new Intent(this, CalculaActivity.class);

        if (v.getId() == R.id.btnAdicao) {
            telaCalcula.putExtra("parametro", "Somar Números");
        } else if (v.getId() == R.id.btnSubtracao) {
            telaCalcula.putExtra("parametro", "Subtrair Números");
        } else if (v.getId() == R.id.btnMultiplicacao) {
            telaCalcula.putExtra("parametro", "Multiplicar Números");
        } else if (v.getId() == R.id.btnDivisao) {
            telaCalcula.putExtra("parametro", "Dividir Números");
        }
        startActivity(telaCalcula);
    }
}









