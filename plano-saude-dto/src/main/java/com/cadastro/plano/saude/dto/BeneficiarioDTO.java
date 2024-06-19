package com.cadastro.plano.saude.dto;

import com.cadastro.plano.saude.util.adapter.LocalDateAdapter;
import com.cadastro.plano.saude.util.adapter.LocalDateTimeAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Beneficiario", description = "Dados do Beneficiario")
@XmlRootElement(name = "Beneficiario")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeneficiarioDTO {

    @Schema(description = "Identificador único do beneficiario", example = "1")
    @XmlElement(nillable = false)
    private Long beneficiarioID;

    @Schema(description = "Nome do beneficiario", example = "Ariano Suassuna")
    @XmlElement(nillable = false)
    private String nomeBeneficiario;

    @Schema(description = "Telefone do beneficiario", example = "11958651432")
    @XmlElement(nillable = false)
    private String telefone;

    @Schema(description = "Data de nascimento do beneficiario", example = "22-06-1932")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate datanascimento;

    @Schema(description = "Data de inclusão do beneficiario", example = "10-06-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataInclusaoBeneficiario;

    @Schema(description = "Data de atualização do beneficiario", example = "11-06-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataAtuazacaoBeneficiario;

    @XmlElement(nillable = false)
    private List<DocumentoDTO> documentos;
}
