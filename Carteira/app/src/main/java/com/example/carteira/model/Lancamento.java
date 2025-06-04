package com.example.carteira.model;

public class Lancamento {
    private int id;
    private String categoria;
    private String descricao;
    private String tipo;
    private Double valor;
    private int dia;
    private int mes;
    private int ano;

    public Lancamento(int id, String categoria, String descricao, String tipo, Double valor, int dia, int mes, int ano) {
        this.id = id;
        this.categoria = categoria;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
}
