package com.cadastro.plano.saude.canonico;

import com.cadastro.plano.saude.enumerated.TipoDocumentoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoCanonico {

    private Long documentoID;
    private Long beneficiarioID;
    private TipoDocumentoEnum tipoDocumento;
    private String numeroDocumento;
    private String descricao;
    private LocalDateTime dataInclusaoDocumento;
    private LocalDateTime dataAtuazacaoDocumento;
}
