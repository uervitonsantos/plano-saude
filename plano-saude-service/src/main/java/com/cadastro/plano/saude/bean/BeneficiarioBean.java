package com.cadastro.plano.saude.bean;

import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.filtro.FiltroWrapper;
import com.cadastro.plano.saude.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeneficiarioBean {

    @Autowired
    private BeneficiarioService service;

    public BeneficiarioCanonico buscaBeneficiario(Long beneficiarioID) {
        return service.buscaBeneficiario(beneficiarioID);
    }

    public List<BeneficiarioCanonico> Beneficiarios(FiltroWrapper filtro) {
        return service.buscaBeneficiarios(filtro);
    }

    public BeneficiarioCanonico criaBeneficiario(BeneficiarioCanonico canonico) {
        return service.criaBeneficiario(canonico);
    }

    public BeneficiarioCanonico editaBeneficiario(BeneficiarioCanonico canonico) {
        Long editaBeneficiario = service.editaBeneficiario(canonico);
        return buscaBeneficiario(editaBeneficiario);
    }

    public void removeBeneficiario(Long beneficiarioID) {
        service.removeBeneficiario(beneficiarioID);
    }
}
