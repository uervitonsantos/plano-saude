package com.cadastro.plano.saude.canonicoFactory;

import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.entidade.Beneficiario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BeneficiarioCanonicoFactory {

    @Autowired
    private DocumentoCanonicoFactory documentoCanonicoFactory;

    public BeneficiarioCanonico builderBeneficiario(Beneficiario beneficiario) {
        return Optional.ofNullable(beneficiario).map(entidade -> {
            return BeneficiarioCanonico.builder()
                    .beneficiarioID(entidade.getBeneficiarioID())
                    .nomeBeneficiario(entidade.getNomeBeneficiario())
                    .telefone(entidade.getTelefone())
                    .datanascimento(entidade.getDatanascimento())
                    .dataInclusaoBeneficiario(entidade.getDataInclusaoBeneficiario())
                    .dataAtuazacaoBeneficiario(entidade.getDataAtuazacaoBeneficiario())
                    .documentos(documentoCanonicoFactory.documentosCanonico(entidade.getDocumentos()))
                    .build();
        }).orElse(null);
    }

    public List<BeneficiarioCanonico> beneficiariosCanonico(List<Beneficiario> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderBeneficiario(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
