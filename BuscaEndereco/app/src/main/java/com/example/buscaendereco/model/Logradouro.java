package com.example.buscaendereco.model;

import androidx.annotation.NonNull;

public class Logradouro {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String complement;
    private String ibge;

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getIbge() {
        return ibge;
    }

    @NonNull
    @Override
    public String toString() {
        return "Endereço: " + this.getStreet() + "\n" +
               "Bairro: " + this.getNeighborhood() + "\n" +
               "Cidade: " + this.getCity() + "\n" +
               "CEP: " + this.getCep() + "\n" +
               "Complemento: " + this.getComplement() + "\n" +
               "IBGE: " + this.getIbge();
    }
}
