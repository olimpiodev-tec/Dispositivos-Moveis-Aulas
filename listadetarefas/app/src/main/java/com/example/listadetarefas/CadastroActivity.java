package com.example.listadetarefas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTitulo;
    private EditText etDescricao;
    private Switch swFinalizada;
    private int tarefaId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton ibVoltar = findViewById(R.id.ibVoltar);
        ibVoltar.setOnClickListener(this);

        Button btSalvar = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(this);

        etTitulo = findViewById(R.id.etTitulo);
        etDescricao = findViewById(R.id.etDescricao);
        swFinalizada = findViewById(R.id.swFinalizada);

        // Recuperando os dados da tarefa para atualizar
        if (getIntent().hasExtra("tarefa")) {
            Tarefa tarefa = (Tarefa) getIntent().getSerializableExtra("tarefa");

            etTitulo.setText(tarefa.getTitulo());
            etDescricao.setText(tarefa.getDescricao());
            swFinalizada.setChecked(tarefa.isConcluido());
            tarefaId =  tarefa.getId();

            TextView tvTituloToolbar = findViewById(R.id.tvTituloToolbar);
            tvTituloToolbar.setText("Editar Tarefa");
            btSalvar.setText("Atualizar");
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ibVoltar) {
            finish();
        } else if (v.getId() == R.id.btSalvar) {
            etTitulo = findViewById(R.id.etTitulo);
            etDescricao = findViewById(R.id.etDescricao);
            swFinalizada = findViewById(R.id.swFinalizada);

            Tarefa tarefa = new Tarefa(
                    etTitulo.getText().toString(),
                    etDescricao.getText().toString(),
                    swFinalizada.isChecked()
            );

            if (tarefaId > 0) {
                atualizarTarefa(tarefa);
            } else if (tarefaId == 0) {
                cadastrarTarefa(tarefa);
            }
        }
    }

    private void atualizarTarefa(Tarefa tarefa) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TarefasApi tarefasApi = retrofit.create(TarefasApi.class);

        Call<Void> atualizarTarefa = tarefasApi.atualizarTarefa(tarefaId, tarefa);
        atualizarTarefa.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Tarefa atualizada!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CadastroActivity.this, "Erro ao atualizar tarefa!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(CadastroActivity.this, "Erro de comunicação com Api", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void cadastrarTarefa(Tarefa tarefa) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TarefasApi tarefasApi = retrofit.create(TarefasApi.class);

        Call<Tarefa> criarTarefaServer = tarefasApi.criarTarefa(tarefa);
        criarTarefaServer.enqueue(new Callback<Tarefa>() {
            @Override
            public void onResponse(Call<Tarefa> call, Response<Tarefa> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this, "Tarefa Cadastrada!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CadastroActivity.this, "Erro ao salvar tarefa", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Tarefa> call, Throwable throwable) {
                Toast.makeText(CadastroActivity.this, "Erro de comunicação API", Toast.LENGTH_LONG).show();
            }
        });
    }


}






















