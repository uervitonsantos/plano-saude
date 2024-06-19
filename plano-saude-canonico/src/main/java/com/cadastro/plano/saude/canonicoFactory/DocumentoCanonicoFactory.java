package com.cadastro.plano.saude.canonicoFactory;

import com.cadastro.plano.saude.canonico.DocumentoCanonico;
import com.cadastro.plano.saude.entidade.Documento;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentoCanonicoFactory {

    public DocumentoCanonico builderDocumento(Documento documento) {
        return Optional.ofNullable(documento).map(entidade -> {
            return DocumentoCanonico.builder()
                    .documentoID(entidade.getDocumentoID())
                    .beneficiarioID(entidade.getBeneficiarioID())
                    .tipoDocumento(entidade.getTipoDocumento())
                    .numeroDocumento(entidade.getNumeroDocumento())
                    .descricao(entidade.getDescricao())
                    .dataInclusaoDocumento(entidade.getDataInclusaoDocumento())
                    .dataAtuazacaoDocumento(entidade.getDataAtuazacaoDocumento())
                    .build();
        }).orElse(null);
    }

    public List<DocumentoCanonico> documentosCanonico(List<Documento> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderDocumento(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
