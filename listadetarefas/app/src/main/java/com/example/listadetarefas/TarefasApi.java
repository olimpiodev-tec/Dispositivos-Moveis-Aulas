package com.example.listadetarefas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TarefasApi {
    @GET("/tasks")
    Call<List<Tarefa>> getTarefas();

    @POST("/tasks")
    Call<Tarefa> criarTarefa(@Body Tarefa tarefa);

    @DELETE("/tasks/{id}")
    Call<Void> deletarTarefa(@Path("id") int id);

    @PUT("/tasks/{id}")
    Call<Void> atualizarTarefa(@Path("id") int id, @Body Tarefa tarefa);
}










