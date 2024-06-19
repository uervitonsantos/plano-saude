package com.cadastro.plano.saude.service;

import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.canonico.DocumentoCanonico;
import com.cadastro.plano.saude.entidade.Documento;
import com.cadastro.plano.saude.enumerated.TipoDocumentoEnum;
import com.cadastro.plano.saude.repository.DocumentoRepository;
import com.cadastro.plano.saude.util.PlanoSaudeMensagens;
import com.cadastro.plano.saude.util.exception.ValidacaoException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    private Documento buscarDocumento(Long documentoID) {
        return Optional.ofNullable(documentoRepository.busca(Documento.class, documentoID))
                .orElseThrow(() -> new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_COD_DOCUMENTO_NAO_EXISTE.getValor()));
    }

    public void validaDadosDocumento(DocumentoCanonico documentoCanonico) {
        if (documentoCanonico == null) {
            throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_DOCUMENTO_OBRIGATORIO.getValor());
        } else {
            TipoDocumentoEnum tipo = documentoCanonico.getTipoDocumento();
            if (tipo == null) {
                throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_TIPO_DOCUMENTO_OBRIGATORIO.getValor());
            }
            if (!tipo.equals(TipoDocumentoEnum.CPF) && !tipo.equals(TipoDocumentoEnum.RG) && !tipo.equals(TipoDocumentoEnum.CNH)) {
                throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_TIPO_DOCUMENTO_INVALIDO.getValor());
            }
            if (Strings.isNullOrEmpty(documentoCanonico.getNumeroDocumento())) {
                throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_NUMERO_DOCUMENTO_OBRIGATORIO.getValor());
            }
            if (Strings.isNullOrEmpty(documentoCanonico.getDescricao())) {
                throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_DESCRICAO_DOCUMENTO_OBRIGATORIO.getValor());
            }
        }
    }

    public void validaNumDocumento(BeneficiarioCanonico canonico) {
        Documento documento = documentoRepository.buscarDocumentoPorNumero(canonico.getDocumentos());
        if (documento != null) {
            throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_NUM_DOCUMENTO_JA_EXISTE.getValor());
        }
    }

    public void removeDocumento(Long documentoID) {
        Documento documento = buscarDocumento(documentoID);
        documentoRepository.remove(documento);
    }

}
