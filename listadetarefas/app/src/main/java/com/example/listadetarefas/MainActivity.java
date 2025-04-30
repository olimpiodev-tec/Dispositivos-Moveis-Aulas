package com.example.listadetarefas;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        editarTarefa();

        ImageView ivCadastrar = findViewById(R.id.ivAdicionarTarefa);
        ivCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastrar = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastrar);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarTarefas();
    }

    private void editarTarefa() {
        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Escolha uma opção")
                        .setMessage("O que deseja fazer com a tarefa?")
                        .setPositiveButton("Atualizar", ((dialog, which) -> {
                            if (tarefa.isConcluido()) {
                                Toast.makeText(MainActivity.this, "Tarefa concluída não pode ser editada!", Toast.LENGTH_LONG).show();
                            } else {
                                Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                                telaCadastro.putExtra("tarefa", tarefa);
                                startActivity(telaCadastro);

                            }
                        }))
                        .setNegativeButton("Remover", ((dialog, which) -> {
                            removerTarefa(tarefa.getId());
                        }))
                        .setNeutralButton("Cancelar", null)
                        .show();

                return true;
            }
        });
    }

    private void removerTarefa(int tarefaId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TarefasApi tarefasApi = retrofit.create(TarefasApi.class);

        Call<Void> deletarTarefa = tarefasApi.deletarTarefa(tarefaId);
        deletarTarefa.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Tarefa excluída com sucesso!", Toast.LENGTH_LONG).show();
                    buscarTarefas();
                } else {
                    Toast.makeText(MainActivity.this, "Houve um erro ao excluir tarefa", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Erro ao comunicar com a Api", Toast.LENGTH_LONG).show();
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







