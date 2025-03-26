package com.example.buscaendereco;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.buscaendereco.Service.InvertextoApi;
import com.example.buscaendereco.model.Logradouro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pgBar;
    private TextView tvInformacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etCep = findViewById(R.id.etCep);
                String numero = etCep.getText().toString();

                pgBar = findViewById(R.id.pgBar);
                pgBar.setVisibility(View.VISIBLE);

                tvInformacoes = findViewById(R.id.tvInformacoes);
                tvInformacoes.setText("Aguarde");

                consultarCep(numero);
            }
        });
    }

    private void consultarCep(String numero) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InvertextoApi service = retrofit.create(InvertextoApi.class);

        Call<Logradouro> buscarEnderecoService = service.getEndereco(numero, Constantes.TOKEN);

        buscarEnderecoService.enqueue(new Callback<Logradouro>() {
            @Override
            public void onResponse(Call<Logradouro> call, Response<Logradouro> response) {
                if (response.isSuccessful()) {
                    Logradouro endereco = response.body();
                    pgBar.setVisibility(View.GONE);
                    tvInformacoes.setText(endereco.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Erro na resposta da API", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Logradouro> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro de comunicação com API", Toast.LENGTH_LONG).show();
            }
        });
    }



}








