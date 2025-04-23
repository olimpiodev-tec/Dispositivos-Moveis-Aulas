package com.example.listadetarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView lvTarefas;
    private TarefaAdapter tarefaAdapter;

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

        lvTarefas = findViewById(R.id.lvTarefas);
        buscarTarefas();

        ImageView ivCadastrar = findViewById(R.id.ivAdicionarTarefa);
        ivCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastrar);
            }
        });
    }

    private void buscarTarefas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TarefasApi tarefasApi = retrofit.create(TarefasApi.class);

        Call<List<Tarefa>> getTarefasServer = tarefasApi.getTarefas();
        getTarefasServer.enqueue(new Callback<List<Tarefa>>() {
            @Override
            public void onResponse(Call<List<Tarefa>> call, Response<List<Tarefa>> response) {
                if (response.isSuccessful()) {
                    List<Tarefa> tarefas = response.body();
                    tarefaAdapter = new TarefaAdapter(MainActivity.this, tarefas);
                    lvTarefas.setAdapter(tarefaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Tarefa>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro ao buscar tarefas", Toast.LENGTH_LONG).show();
            }
        });

    }
}







