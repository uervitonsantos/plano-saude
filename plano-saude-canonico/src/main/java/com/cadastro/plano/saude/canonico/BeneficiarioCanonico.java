package com.cadastro.plano.saude.canonico;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiarioCanonico {

    private Long beneficiarioID;
    private String nomeBeneficiario;
    private String telefone;
    private LocalDate datanascimento;
    private LocalDateTime dataInclusaoBeneficiario;
    private LocalDateTime dataAtuazacaoBeneficiario;
    private List<DocumentoCanonico> documentos;
}
