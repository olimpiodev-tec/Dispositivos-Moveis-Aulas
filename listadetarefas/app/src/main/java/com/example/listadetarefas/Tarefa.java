package com.example.listadetarefas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tarefa implements Serializable {
    private int id;

    @SerializedName("title")
    private String titulo;

    @SerializedName("description")
    private String descricao;

    @SerializedName("done")
    private boolean concluido;

    public Tarefa(String titulo, String descricao, boolean concluido) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = concluido;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluido() {
        return concluido;
    }
}
