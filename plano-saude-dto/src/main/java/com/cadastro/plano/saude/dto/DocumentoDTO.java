package com.cadastro.plano.saude.dto;

import com.cadastro.plano.saude.enumerated.TipoDocumentoEnum;
import com.cadastro.plano.saude.util.adapter.LocalDateTimeAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Documento", description = "Dados do Documento")
@XmlRootElement(name = "Documento")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentoDTO {

    @Schema(description = "Identificador único do documento", example = "1")
    @XmlElement(nillable = false)
    private Long documentoID;

    @Schema(description = "Tipo do documento (CPF, RG, CNH)", example = "CPF")
    @XmlElement(nillable = false)
    private TipoDocumentoEnum tipoDocumento;

    @Schema(description = "Numero do documento", example = "654.987.321-59")
    @XmlElement(nillable = false)
    private String numeroDocumento;

    @Schema(description = "Descrição do documento", example = "Descrição...")
    @XmlElement(nillable = false)
    private String descricao;

    @Schema(description = "Data de inclusão do documento", example = "10-06-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataInclusaoDocumento;

    @Schema(description = "Data de inclusão do documento", example = "11-06-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataAtuazacaoDocumento;
}
