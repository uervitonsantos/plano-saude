package com.cadastro.plano.saude.controllerImplements;

import com.cadastro.plano.saude.bean.BeneficiarioBean;
import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.dto.BeneficiarioDTO;
import com.cadastro.plano.saude.dtoFactory.BeneficiarioDTOFactory;
import com.cadastro.plano.saude.filterDTO.BeneficiarioFiltroDTO;
import com.cadastro.plano.saude.filtro.FiltroWrapper;
import com.cadastro.plano.saude.recurso.BeneficiarioController;
import com.cadastro.plano.saude.util.constates.Resource;
import jakarta.ws.rs.BeanParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/beneficiario")
public class BeneficiarioControllerImpl implements BeneficiarioController {

    private static final String PATH_ID = "/{beneficiarioID}";

    @Autowired
    private BeneficiarioBean beneficiarioBean;

    @Autowired
    private BeneficiarioDTOFactory beneficiarioDTOFactory;

    @GetMapping(PATH_ID)
    @Override
    public ResponseEntity<BeneficiarioDTO> getBeneficiario(@PathVariable(Resource.P_BENEFICIARIO_ID) Long beneficiarioID) {
        BeneficiarioDTO dto = beneficiarioDTOFactory.builderBeneficiarioDto(beneficiarioBean.buscaBeneficiario(beneficiarioID));
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<BeneficiarioDTO>> getBeneficiarios(@BeanParam BeneficiarioFiltroDTO filtro) {
        FiltroWrapper wrapper = filtro.filtroWrapper();
        List<BeneficiarioCanonico> beneficiarios = beneficiarioBean.Beneficiarios(wrapper);
        return ResponseEntity.ok(beneficiarioDTOFactory.beneficiariosDto(beneficiarios));
    }

    @PostMapping
    @Override
    public ResponseEntity<BeneficiarioDTO> criaBeneficiario(@RequestBody BeneficiarioDTO dto) {
        BeneficiarioCanonico canonico = beneficiarioDTOFactory.builderBeneficiarioCanonico(dto);
        BeneficiarioCanonico beneficiarioSalva = beneficiarioBean.criaBeneficiario(canonico);
        return criaResponse(beneficiarioSalva);
    }

    @PutMapping(PATH_ID)
    @Override
    public ResponseEntity atualizaBeneficiario(@PathVariable(Resource.P_BENEFICIARIO_ID) Long beneficiarioID, @RequestBody BeneficiarioDTO dto) {
        dto.setBeneficiarioID(beneficiarioID);
        BeneficiarioCanonico canonico = beneficiarioDTOFactory.builderBeneficiarioCanonico(dto);
        BeneficiarioCanonico beneficiarioAtualizado = beneficiarioBean.editaBeneficiario(canonico);
        return criaResponse(beneficiarioAtualizado);
    }

    private ResponseEntity criaResponse(@RequestBody BeneficiarioCanonico beneficiario) {
        BeneficiarioDTO beneficiarioDTO = beneficiarioDTOFactory.builderBeneficiarioDto(beneficiarioBean.buscaBeneficiario(beneficiario.getBeneficiarioID()));
        return ResponseEntity.ok().body(beneficiarioDTO);
    }

    @DeleteMapping(PATH_ID)
    @Override
    public ResponseEntity removeBeneficiario(@PathVariable(Resource.P_BENEFICIARIO_ID) Long beneficiarioID) {
        beneficiarioBean.removeBeneficiario(beneficiarioID);
        return ResponseEntity.noContent().build();
    }
}
