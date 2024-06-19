package com.cadastro.plano.saude.entidade;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "BENEFICIARIO")
public class Beneficiario implements Serializable {

    @Id
    @Column(name = "BENEFICIARIO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beneficiario_sequence")
    @SequenceGenerator(name = "beneficiario_sequence", sequenceName = "SEQ_BENEFICIARIO", allocationSize = 1)
    private Long beneficiarioID;

    @Column(name = "NOME")
    private String nomeBeneficiario;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate datanascimento;

    @Column(name = "DATA_INCLUSAO_BENEFICIARIO")
    private LocalDateTime dataInclusaoBeneficiario;

    @Column(name = "DATA_ATUALIZACAO_BENEFICIARIO")
    private LocalDateTime dataAtuazacaoBeneficiario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "beneficiario", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Documento> documentos;

    public void retemDocumento(List<Documento> documentos) {
        if (documentos == null) {
            return;
        }
        this.documentos.retainAll(documentos);
    }

    public Documento novoDocumento(Long documentoID) {
        Documento novo = new Documento();
        novo.setBeneficiarioID(beneficiarioID);
        novo.setDocumentoID(documentoID);
        Documento documento = documentos.stream().filter(el -> el.equals(novo)).findFirst().orElse(novo);
        documentos.add(documento);
        return documento;
    }
}
