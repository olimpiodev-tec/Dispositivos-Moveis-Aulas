package com.example.listadetarefas;

public class Tarefa {
    private String titulo;
    private String descricao;
    private boolean concluido;

    public Tarefa(String titulo, String descricao, boolean concluido) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluido = concluido;
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
