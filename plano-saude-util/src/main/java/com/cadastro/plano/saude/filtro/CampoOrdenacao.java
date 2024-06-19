package com.cadastro.plano.saude.filtro;

public enum CampoOrdenacao {

    //formatter:off
    BENEFICIARIO_ID("beneficiarioID"),
    ;
    private String valor;

    private CampoOrdenacao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
