package com.cadastro.plano.saude.dtoFactory;

import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.dto.BeneficiarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BeneficiarioDTOFactory {


    @Autowired
    private DocumentoDTOFactory documentoDTOFactory;

    public BeneficiarioDTO builderBeneficiarioDto(BeneficiarioCanonico beneficiario) {
        return Optional.ofNullable(beneficiario).map(entidade -> {
            return BeneficiarioDTO.builder()
                    .beneficiarioID(entidade.getBeneficiarioID())
                    .nomeBeneficiario(entidade.getNomeBeneficiario())
                    .telefone(entidade.getTelefone())
                    .datanascimento(entidade.getDatanascimento())
                    .dataInclusaoBeneficiario(entidade.getDataInclusaoBeneficiario())
                    .dataAtuazacaoBeneficiario(entidade.getDataAtuazacaoBeneficiario())
                    .documentos(documentoDTOFactory.documentosDto(entidade.getDocumentos()))
                    .build();
        }).orElse(null);
    }

    public List<BeneficiarioDTO> beneficiariosDto(List<BeneficiarioCanonico> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderBeneficiarioDto(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }


    public BeneficiarioCanonico builderBeneficiarioCanonico(BeneficiarioDTO beneficiario) {
        return Optional.ofNullable(beneficiario).map(entidade -> {
            return BeneficiarioCanonico.builder()
                    .beneficiarioID(entidade.getBeneficiarioID())
                    .nomeBeneficiario(entidade.getNomeBeneficiario())
                    .telefone(entidade.getTelefone())
                    .datanascimento(entidade.getDatanascimento())
                    .documentos(documentoDTOFactory.documentosCanonico(entidade.getDocumentos()))
                    .build();
        }).orElse(null);
    }

    public List<BeneficiarioCanonico> beneficiariosCanonico(List<BeneficiarioDTO> resultList) {
        return Optional.ofNullable(resultList).map(lista -> {
            return lista.stream().map(el -> builderBeneficiarioCanonico(el)).collect(Collectors.toList());
        }).orElse(new ArrayList<>());

    }
}
