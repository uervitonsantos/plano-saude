package com.cadastro.plano.saude.enumerated;

public enum TipoDocumentoEnum {

    CPF("CPF"), RG("RG"), CNH("CNH");

    private final String valor;

    private TipoDocumentoEnum(String valor) {
        this.valor = valor;
    }

    public static TipoDocumentoEnum findByCodigo(String valor) {
        for (TipoDocumentoEnum value : TipoDocumentoEnum.values()) {
            if (value.getValor().equalsIgnoreCase(valor.trim())) {
                return value;
            }
        }
        return null;
    }

    public String getValor() {
        return valor;
    }
}
