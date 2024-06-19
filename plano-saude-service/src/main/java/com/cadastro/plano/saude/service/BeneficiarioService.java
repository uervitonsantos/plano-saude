package com.cadastro.plano.saude.service;

import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.canonico.DocumentoCanonico;
import com.cadastro.plano.saude.entidade.Beneficiario;
import com.cadastro.plano.saude.entidade.Documento;
import com.cadastro.plano.saude.filtro.FiltroWrapper;
import com.cadastro.plano.saude.repository.BeneficiarioRepository;
import com.cadastro.plano.saude.util.PlanoSaudeMensagens;
import com.cadastro.plano.saude.util.exception.ValidacaoException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoService documentoService;

    LocalDateTime dataAtual = LocalDateTime.now();

    public BeneficiarioCanonico buscaBeneficiario(Long beneficiarioID) {
        return Optional.ofNullable(beneficiarioRepository.buscaBeneficiario(beneficiarioID)).orElseThrow(
                () -> new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_COD_BENEFICIARIO_NAO_EXISTE.getValor()));
    }

    public Beneficiario buscarBeneficiario(Long beneficiarioID) {
        return Optional.ofNullable(beneficiarioRepository.busca(beneficiarioID)).orElseThrow(
                () -> new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_COD_BENEFICIARIO_NAO_EXISTE.getValor()));
    }

    public List<BeneficiarioCanonico> buscaBeneficiarios(FiltroWrapper filtro) {
        return beneficiarioRepository.buscaBeneficiarios(filtro);
    }

    public BeneficiarioCanonico criaBeneficiario(BeneficiarioCanonico canonico) {
        validaIDBeneficiario(canonico);
        documentoService.validaNumDocumento(canonico);
        validaDadosBeneficiario(canonico);
        Long beneficiario = salvaBeneficiario(canonico);
        return buscaBeneficiario(beneficiario);
    }

    private void validaIDBeneficiario(BeneficiarioCanonico canonico) {
        BeneficiarioCanonico beneficiario = beneficiarioRepository.buscaBeneficiario(canonico.getBeneficiarioID());
        if (beneficiario != null) {
            throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_COD_BENEFICIARIO_JA_EXISTE.getValor());
        }
    }

    public Long editaBeneficiario(BeneficiarioCanonico canonico) {
        Beneficiario beneficiario = buscarBeneficiario(canonico.getBeneficiarioID());
        validaBeneficiario(beneficiario, canonico);
        popularBeneficiario(beneficiario, canonico);
        beneficiario = beneficiarioRepository.merge(beneficiario);
        return beneficiario.getBeneficiarioID();
    }

    private void validaBeneficiario(Beneficiario beneficiario, BeneficiarioCanonico canonico) {
        validaDadosBeneficiario(canonico);
    }

    private void popularBeneficiario(Beneficiario beneficiario, BeneficiarioCanonico canonico) {
        beneficiario.setNomeBeneficiario(canonico.getNomeBeneficiario());
        beneficiario.setTelefone(canonico.getTelefone());
        beneficiario.setDatanascimento(canonico.getDatanascimento());
        beneficiario.setDataAtuazacaoBeneficiario(dataAtual);
        popularDocumento(beneficiario, canonico.getDocumentos());
    }

    private void popularDocumento(Beneficiario beneficiario, List<DocumentoCanonico> documentosCanonicos) {
        List<Documento> documentosExistentes = new ArrayList<>(beneficiario.getDocumentos());

        Map<Long, Documento> mapaDocumentosExistentes = documentosExistentes.stream()
                .collect(Collectors.toMap(Documento::getDocumentoID, doc -> doc));

        // Set de IDs de documentos canônicos para controle de exclusão
        Set<Long> idsDocumentosCanonicos = documentosCanonicos.stream()
                .map(DocumentoCanonico::getDocumentoID).collect(Collectors.toSet());

        // Atualiza os documentos existentes ou adiciona novos
        for (DocumentoCanonico documentoCanonico : documentosCanonicos) {
            Documento documentoExistente = mapaDocumentosExistentes.get(documentoCanonico.getDocumentoID());
            if (documentoExistente != null) {
                // Atualiza o documento existente
                documentoExistente.setTipoDocumento(documentoCanonico.getTipoDocumento());
                documentoExistente.setNumeroDocumento(documentoCanonico.getNumeroDocumento());
                documentoExistente.setDescricao(documentoCanonico.getDescricao());
                documentoExistente.setDataAtuazacaoDocumento(dataAtual);
            } else {
                // Adiciona novo documento
                Documento novoDocumento = new Documento();
                novoDocumento.setBeneficiarioID(beneficiario.getBeneficiarioID());
                novoDocumento.setTipoDocumento(documentoCanonico.getTipoDocumento());
                novoDocumento.setNumeroDocumento(documentoCanonico.getNumeroDocumento());
                novoDocumento.setDescricao(documentoCanonico.getDescricao());
                novoDocumento.setDataInclusaoDocumento(dataAtual);
                novoDocumento.setDataAtuazacaoDocumento(dataAtual);
                documentosExistentes.add(novoDocumento);
            }
        }

        // Lista para armazenar os IDs dos documentos removidos
        List<Long> idsDocumentosRemovidos = new ArrayList<>();

        // Remove os documentos que não estão mais na lista canônica e armazena
        // os IDs dos documentos removidos
        documentosExistentes.removeIf(documento -> {
            if (!idsDocumentosCanonicos.contains(documento.getDocumentoID())) {
                idsDocumentosRemovidos.add(documento.getDocumentoID());
                return true; // Remove o documento da lista
            }
            return false; // Mantém o documento na lista
        });

        // Remove os documentos e limpa a lista de documentos
        // do beneficiario, se houver algum documento removido
        if (!idsDocumentosRemovidos.isEmpty()) {
            beneficiario.getDocumentos().clear();
            idsDocumentosRemovidos.forEach(documentoID -> {
                documentoService.removeDocumento(documentoID);
            });
        } else {
            // Atualiza a lista de documentos do beneficiário
            beneficiario.setDocumentos(documentosExistentes);
        }
    }

    private void validaDadosBeneficiario(BeneficiarioCanonico canonico) {
        List<DocumentoCanonico> documentoCanonico = canonico.getDocumentos();

        if (Strings.isNullOrEmpty(canonico.getNomeBeneficiario())) {
            throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_NOME_OBRIGATORIO.getValor());
        }

        if (Strings.isNullOrEmpty(canonico.getTelefone())) {
            throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_TELEFONE_OBRIGATORIO.getValor());
        }

        if (canonico.getDatanascimento() == null) {
            throw new ValidacaoException(PlanoSaudeMensagens.ERRO_VALIDACAO_DATA_NASCIMENTO_OBRIGATORIO.getValor());
        }

        for (DocumentoCanonico documentos : documentoCanonico) {
            documentoService.validaDadosDocumento(documentos);
        }
    }

    private Long salvaBeneficiario(BeneficiarioCanonico canonico) {
        Beneficiario beneficiario = geraBeneficario(canonico);
        beneficiario.setNomeBeneficiario(canonico.getNomeBeneficiario());
        beneficiario.setTelefone(canonico.getTelefone());
        beneficiario.setDatanascimento(canonico.getDatanascimento());
        beneficiario.setDataInclusaoBeneficiario(dataAtual);
        beneficiario.setDataAtuazacaoBeneficiario(dataAtual);
        Beneficiario beneficiarioSalvo = beneficiarioRepository.salvaBeneficiario(beneficiario);
        salvaDocumento(canonico, beneficiarioSalvo);
        return beneficiarioSalvo.getBeneficiarioID();
    }

    private void salvaDocumento(BeneficiarioCanonico canonico, Beneficiario beneficiarioSalvo) {
        Optional.ofNullable(canonico.getDocumentos()).ifPresent(doc -> {
            persisteDocumentoss(beneficiarioSalvo, doc);
        });
    }

    private void persisteDocumentoss(Beneficiario beneficiarioSalvo, List<DocumentoCanonico> doc) {
        List<Documento> documentos = doc.stream().map(documento -> montaDocumento(beneficiarioSalvo, documento)).collect(Collectors.toList());
        beneficiarioSalvo.retemDocumento(documentos);
    }

    private Documento montaDocumento(Beneficiario beneficiarioSalvo, DocumentoCanonico documento) {
        LocalDateTime dataAtual = LocalDateTime.now();
        Documento d = beneficiarioSalvo.novoDocumento(documento.getDocumentoID());
        d.setTipoDocumento(documento.getTipoDocumento());
        d.setNumeroDocumento(documento.getNumeroDocumento());
        d.setDescricao(documento.getDescricao());
        d.setDataInclusaoDocumento(dataAtual);
        d.setDataAtuazacaoDocumento(dataAtual);
        return d;
    }

    private Beneficiario geraBeneficario(BeneficiarioCanonico canonico) {
        if (canonico.getBeneficiarioID() == null) {
            return new Beneficiario();
        }
        return beneficiarioRepository.busca(canonico.getBeneficiarioID());
    }

    public void removeBeneficiario(Long beneficiarioID) {
        Beneficiario beneficiario = buscarBeneficiario(beneficiarioID);
        beneficiarioRepository.remove(beneficiario);
    }
}
