package com.letscode;

public enum TipoProduto {

    ALM("Alimentos", 1.2),
    BEB("Bebidas", 2.3),
    HIG("Higiene", 1.5);

    TipoProduto(String descricao, double markup){
        this.descricao = descricao;
        this.markup = markup;
    }

    private String descricao;
    private double markup;

    public String getDescricao(){
        return this.descricao;
    }

    public double getMarkup(){
        return this.markup;
    }

    public static boolean contemEnum(String tipo){
        for (TipoProduto cadaTipo:TipoProduto.values()) {
            if(cadaTipo.toString().equals(tipo)){
                return true;
            }
        }
        return false;
    }
}
