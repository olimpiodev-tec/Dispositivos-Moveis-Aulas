package com.example.listadetarefas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ibVoltar) {
            finish();
        } else if (v.getId() == R.id.btSalvar) {
            EditText etTitulo = findViewById(R.id.etTitulo);
            EditText etDescricao = findViewById(R.id.etDescricao);
            Switch swFinalizada = findViewById(R.id.swFinalizada);

            Tarefa tarefa = new Tarefa(
                    etTitulo.getText().toString(),
                    etDescricao.getText().toString(),
                    swFinalizada.isChecked()
            );
            cadastrarTarefa(tarefa);
        }
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






















