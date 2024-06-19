package com.cadastro.plano.saude.util.constates;


import com.cadastro.plano.saude.filtro.FiltroWrapper;
import com.cadastro.plano.saude.filtro.Paginacao;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

import java.util.Optional;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION;

public interface Resource {
    public static final String RESPONSE_OK = "200";
    public static final String RESPONSE_BAD_REQUEST = "400";
    public static final String RESPONSE_UNAUTHORIZED = "401";
    public static final String RESPONSE_FORBIDDEN = "403";
    public static final String RESPONSE_NO_CONTENT = "204";
    public static final String RESPONSE_CREATED = "201";
    public static final String RESPONSE_PARCIAL = "206";
    public static final String APPLICATION_PDF = "application/pdf";
    public static final String P_QUANTIDADE_TOTAL = "quantidadeTotal";
    public static final String ATTACHMENT = "attachment; filename=%s";
    public static final String P_QUANTIDADE_REGISTROS = "quantidadeRegistros";
    public static final String P_PAGINA = "pagina";
    public static final String P_BENEFICIARIO_ID = "beneficiarioID";
    public static final String P_NOME_BENEFICIARIO = "nomeBeneficiario";
    public static final String P_DATA_NASCIMENTO = "datanascimento";
    public static final String P_DATA_INCLID_BENEFICIARIO = "dataInclusaoBeneficiario";
    public static final String P_DOCUMENTO_ID = "documentoID";
    public static final String P_NUM_DOCUMENTO = "numeroDocumento";
    public static final String P_DATA_INCLID_DOCUMENTO = "dataInclusaoDocumento";


    default ResponseBuilder respostaPaginada(FiltroWrapper wrapper) {
        return respostaPaginada(wrapper.getPaginacaoOptional());
    }

    default ResponseBuilder respostaPaginada(Optional<Paginacao> paginacao) {
        ResponseBuilder builder = Response.ok();
        if (paginacao.isPresent()) {
            if (paginacao.get().hasMais()) {
                builder.status(Status.PARTIAL_CONTENT);
            }
            builder.header(P_QUANTIDADE_TOTAL, paginacao.get().getTotalRegistros());
        }
        return builder;
    }

    default ResponseBuilder respostaArquivo(byte[] conteudo, String nomeArquivo) {
        return Response.ok(conteudo).header(CONTENT_DISPOSITION, String.format(ATTACHMENT, nomeArquivo));
    }
}
