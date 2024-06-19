package com.cadastro.plano.saude.filtro;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioFiltro implements Filtro{

    private List<Long> beneficiarioID;
    private String nomeBeneficiario;
    private LocalDate datanascimento;
    private LocalDateTime dataInclusaoBeneficiario;
    private Long documentoID;
    private String numeroDocumento;
    private LocalDateTime dataInclusaoDocumento;
}
