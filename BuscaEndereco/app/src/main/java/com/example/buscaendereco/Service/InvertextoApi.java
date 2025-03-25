package com.example.buscaendereco.Service;

import com.example.buscaendereco.model.Logradouro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InvertextoApi {
    @GET("/v1/cep/{numero}")
    Call<Logradouro> getEndereco(@Path("numero") String numero,
                                 @Query("token") String token);

}

