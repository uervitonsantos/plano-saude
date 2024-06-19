package com.cadastro.plano.saude.dtoFactory;

import com.cadastro.plano.saude.canonico.DocumentoCanonico;
import com.cadastro.plano.saude.dto.DocumentoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DocumentoDTOFactory {

    public DocumentoDTO builderDocumentoDto(DocumentoCanonico documento) {
        return Optional.ofNullable(documento).map(entidade -> {
            return DocumentoDTO.builder()
                    .documentoID(entidade.getDocumentoID())
                    .tipoDocumento(entidade.getTipoDocumento())
                    .numeroDocumento(entidade.getNumeroDocumento())
                    .descricao(entidade.getDescricao())
                    .dataInclusaoDocumento(entidade.getDataInclusaoDocumento())
                    .dataAtuazacaoDocumento(entidade.getDataAtuazacaoDocumento())
                    .build();
        }).orElse(null);
    }

    public List<DocumentoDTO> documentosDto(List<DocumentoCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderDocumentoDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }

    public DocumentoCanonico builderDocumentoCanonico(DocumentoDTO documento) {
        return Optional.ofNullable(documento).map(entidade -> {
            return DocumentoCanonico.builder()
                    .documentoID(entidade.getDocumentoID())
                    .tipoDocumento(entidade.getTipoDocumento())
                    .numeroDocumento(entidade.getNumeroDocumento())
                    .descricao(entidade.getDescricao())
                    .dataInclusaoDocumento(entidade.getDataInclusaoDocumento())
                    .dataAtuazacaoDocumento(entidade.getDataAtuazacaoDocumento())
                    .build();
        }).orElse(null);
    }

    public List<DocumentoCanonico> documentosCanonico(List<DocumentoDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderDocumentoCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
