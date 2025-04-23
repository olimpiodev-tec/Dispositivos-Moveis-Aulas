package com.example.listadetarefas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TarefasApi {
    @GET("/tasks")
    Call<List<Tarefa>> getTarefas();

    @POST("/tasks")
    Call<Tarefa> criarTarefa(@Body Tarefa tarefa);
}
