package com.cadastro.plano.saude.util;

public enum PlanoSaudeMensagens {

    ERRO_VALIDACAO_COD_BENEFICIARIO_NAO_EXISTE("Código do benefiário não existe na base de dados"),
    ERRO_VALIDACAO_COD_BENEFICIARIO_JA_EXISTE("Código do benefiário já existe na base de dados"),
    ERRO_VALIDACAO_NOME_OBRIGATORIO("Obrigatório informar o nome do benefiário"),
    ERRO_VALIDACAO_DATA_NASCIMENTO_OBRIGATORIO("Obrigatório informar a data de nascimento do benefiário"),
    ERRO_VALIDACAO_TELEFONE_OBRIGATORIO("Obrigatório informar um telefone"),
    ERRO_VALIDACAO_DOCUMENTO_OBRIGATORIO("Obrigatório informar os dados do documento"),
    ERRO_VALIDACAO_TIPO_DOCUMENTO_OBRIGATORIO("Obrigatório informar o tipo de documento"),
    ERRO_VALIDACAO_TIPO_DOCUMENTO_INVALIDO("Erro ao validar o tipo de documento"),
    ERRO_VALIDACAO_NUMERO_DOCUMENTO_OBRIGATORIO("Obrigatório informar o número de documento"),
    ERRO_VALIDACAO_DESCRICAO_DOCUMENTO_OBRIGATORIO("Obrigatório informar a descrição do documento"),
    ERRO_VALIDACAO_NUM_DOCUMENTO_JA_EXISTE("Número do documento já cadastrada na base de dados"),
    ERRO_VALIDACAO_COD_DOCUMENTO_NAO_EXISTE("ID do documento não existe");


    private final String valor;

    private PlanoSaudeMensagens(String valor) {
        this.valor = valor;
    }

    public static PlanoSaudeMensagens findByCodigo(String valor) {
        for (PlanoSaudeMensagens value : PlanoSaudeMensagens.values()) {
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
