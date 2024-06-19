package com.cadastro.plano.saude.filterDTO;

import com.cadastro.plano.saude.filtro.BeneficiarioFiltro;
import com.cadastro.plano.saude.filtro.FiltroWrapper;
import com.cadastro.plano.saude.filtro.PaginacaoFactory;
import com.cadastro.plano.saude.filtro.Paginacao;
import com.cadastro.plano.saude.util.adapter.LocalDateAdapter;
import com.cadastro.plano.saude.util.adapter.LocalDateTimeAdapter;
import com.cadastro.plano.saude.util.constates.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Schema(name = "Filtro", description = "Filtro para os Dados do Beneficiário")
@XmlRootElement(name = "Filtro")
@XmlAccessorType(XmlAccessType.FIELD)
public class BeneficiarioFiltroDTO {

    @QueryParam(Resource.P_QUANTIDADE_REGISTROS)
    @Schema(description = "Quantidade de registros", example = "10")
    @XmlElement(nillable = false)
    private Integer quantidadeRegistros;

    @QueryParam(Resource.P_PAGINA)
    @Schema(description = "Pagina", example = "1")
    @XmlElement(nillable = false)
    private Integer pagina;

    @QueryParam(Resource.P_BENEFICIARIO_ID)
    @Schema(description = "Identificador único do beneficiario", example = "1")
    @XmlElement(nillable = false)
    private List<Long> beneficiarioID;

    @QueryParam(Resource.P_NOME_BENEFICIARIO)
    @Schema(description = "Nome do beneficiario", example = "Ariano Suassuna")
    @XmlElement(nillable = false)
    private String nomeBeneficiario;

    @QueryParam(Resource.P_DATA_NASCIMENTO)
    @Schema(description = "Data de nascimento do beneficiario", example = "22-06-1932")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate datanascimento;

    @QueryParam(Resource.P_DATA_INCLID_BENEFICIARIO)
    @Schema(description = "Data de inclusão do beneficiario", example = "10-06-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataInclusaoBeneficiario;

    @QueryParam(Resource.P_DOCUMENTO_ID)
    @Schema(description = "Identificador único do documento", example = "1")
    @XmlElement(nillable = false)
    private Long documentoID;

    @QueryParam(Resource.P_NUM_DOCUMENTO)
    @Schema(description = "Numero do documento", example = "654.987.321-59")
    @XmlElement(nillable = false)
    private String numeroDocumento;

    @QueryParam(Resource.P_DATA_INCLID_DOCUMENTO)
    @Schema(description = "Data de inclusão do documento", example = "10-06-2024")
    @XmlElement(nillable = false)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime dataInclusaoDocumento;

    public FiltroWrapper filtroWrapper() {
        BeneficiarioFiltro filtro = BeneficiarioFiltro.builder()
                .beneficiarioID(beneficiarioID)
                .nomeBeneficiario(nomeBeneficiario)
                .datanascimento(datanascimento)
                .dataInclusaoBeneficiario(dataInclusaoBeneficiario)
                .documentoID(documentoID)
                .numeroDocumento(numeroDocumento)
                .dataInclusaoDocumento(dataInclusaoDocumento)
                .build();
        return new FiltroWrapper(filtro, geraPaginacao());

    }

    private Optional<Paginacao> geraPaginacao() {
        return PaginacaoFactory.cria(pagina, quantidadeRegistros);
    }
}
