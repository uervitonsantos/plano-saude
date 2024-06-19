package com.cadastro.plano.saude.repository;

import com.cadastro.plano.saude.entidade.Documento;
import com.cadastro.plano.saude.canonico.DocumentoCanonico;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentoRepository extends PlanoSaudeRepositorio {

    public Documento buscarDocumentoPorNumero(List<DocumentoCanonico> documentos) {
        TypedQuery<Documento> query = getEntityManager().createNamedQuery("Documento.buscaPorNumeroDocumento", Documento.class);
        for(DocumentoCanonico numDocumento : documentos){
            query.setParameter("pnumeroDocumento", numDocumento.getNumeroDocumento());
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
        return null;
    }
}
