package com.example.listadetarefas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TarefasApi {
    @GET("/tasks")
    Call<List<Tarefa>> getTarefas();
}
