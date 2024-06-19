package com.cadastro.plano.saude.entidade;

import com.cadastro.plano.saude.enumerated.TipoDocumentoEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENTO")
@NamedQuery(name = "Documento.buscaPorNumeroDocumento", query = "SELECT p FROM Documento p WHERE p.numeroDocumento = :pnumeroDocumento")
public class Documento implements Serializable {

    @Id
    @Column(name = "DOCUMENTO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documento_sequence")
    @SequenceGenerator(name = "documento_sequence", sequenceName = "SEQ_DOCUMENTO", allocationSize = 1)
    private Long documentoID;

    @Column(name = "BENEFICIARIO_ID")
    private Long beneficiarioID;

    @Column(name = "TIPO_DOCUMENTO")
    private TipoDocumentoEnum tipoDocumento;

    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Column(name = "DESCRICAO_DOCUMENTO")
    private String descricao;

    @Column(name = "DATA_INCLUSAO_DOCUMENTO")
    private LocalDateTime dataInclusaoDocumento;

    @Column(name = "DATA_ATUALIZACAO_DOCUMENTO")
    private LocalDateTime dataAtuazacaoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BENEFICIARIO_ID", referencedColumnName = "BENEFICIARIO_ID", insertable = false, updatable = false)
    private Beneficiario beneficiario;
}
