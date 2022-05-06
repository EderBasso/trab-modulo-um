package com.letscode;

public enum TipoCliente {
    PF("Cliente PF", 0.0),
    PJ("Cliente PJ", 0.05),
    VIP("Cliente VIP", 0.15),
    COMUM ("Cliente sem cadastro", 0.0);

    TipoCliente(String descricao, double desconto){
        this.descricao = descricao;
        this.desconto = desconto;
    }

    private String descricao;
    private double desconto;

    public String getDescricao(){
        return this.descricao;
    }

    public double getDesconto(){
        return this.desconto;
    }

}
