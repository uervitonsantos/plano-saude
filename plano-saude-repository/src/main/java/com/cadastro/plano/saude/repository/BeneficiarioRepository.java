package com.cadastro.plano.saude.repository;

import com.cadastro.plano.saude.canonico.BeneficiarioCanonico;
import com.cadastro.plano.saude.canonicoFactory.BeneficiarioCanonicoFactory;
import com.cadastro.plano.saude.entidade.Beneficiario;
import com.cadastro.plano.saude.filtro.BeneficiarioFiltro;
import com.cadastro.plano.saude.filtro.FiltroWrapper;
import com.google.common.collect.Lists;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BeneficiarioRepository extends PlanoSaudeRepositorio {

    @Autowired
    private BeneficiarioCanonicoFactory beneficiarioCanonicoFactory;

    public Beneficiario busca(Long beneficiarioID) {
        return busca(Beneficiario.class, beneficiarioID);
    }

    public BeneficiarioCanonico buscaBeneficiario(Long beneficiarioID) {
        Beneficiario beneficiario = busca(beneficiarioID);
        return Optional.ofNullable(beneficiario).map(e -> {
            return beneficiarioCanonicoFactory.builderBeneficiario(e);
        }).orElse(null);
    }

    public List<BeneficiarioCanonico> buscaBeneficiarios(FiltroWrapper filtro) {
        BeneficiarioFiltro beneficiarioFiltro = (BeneficiarioFiltro) filtro.getFiltro();
        if (filtro.hasPaginacao()) {
            Long qtdRegistros = countRegistros(beneficiarioFiltro);
            if (qtdRegistros == 0) {
                return Lists.newArrayList();
            }
            filtro.getPaginacao().setTotalRegistros(qtdRegistros);
            CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
            Root<Beneficiario> root = criteria.from(Beneficiario.class);
            criteria.distinct(true).select(root.get("beneficiarioID"))
                    .where(aplicaFiltros(root, beneficiarioFiltro, false)).orderBy(asc(root.get("beneficiarioID")));
            List<Long> resultado = paginarResultado(criteria, filtro);
            if (resultado.isEmpty()) {
                return Lists.newArrayList();
            }
            beneficiarioFiltro.setBeneficiarioID(resultado);
        }
        return buscaPassoas(beneficiarioFiltro);
    }

    private List<BeneficiarioCanonico> buscaPassoas(BeneficiarioFiltro beneficiarioFiltro) {
        CriteriaQuery<Beneficiario> criteria = criteriaQuery(Beneficiario.class);
        Root<Beneficiario> root = criteria.from(Beneficiario.class);
        criteria.distinct(true).select(root).where(aplicaFiltros(root, beneficiarioFiltro, true))
                .orderBy(asc(root.get("beneficiarioID")));
        TypedQuery<Beneficiario> query = typedQuery(criteria);
        return beneficiarioCanonicoFactory.beneficiariosCanonico(query.getResultList());
    }

    private Long countRegistros(BeneficiarioFiltro beneficiarioFiltro) {
        CriteriaQuery<Long> criteria = criteriaQuery(Long.class);
        Root<Beneficiario> root = criteria.from(Beneficiario.class);
        criteria.select(criteriaBuilder().countDistinct(root.get("beneficiarioID")))
                .where(aplicaFiltros(root, beneficiarioFiltro, false));
        return typedQuery(criteria).getSingleResult();
    }

    private Predicate[] aplicaFiltros(Root<Beneficiario> root, BeneficiarioFiltro beneficiarioFiltro, boolean fetch) {
        CriteriaBuilder builder = criteriaBuilder();
        List<Predicate> predicates = Lists.newArrayList();

        // Implementar filtros

        return predicates.stream().toArray(Predicate[]::new);
    }

    public Beneficiario salvaBeneficiario(Beneficiario beneficiario) {
        return merge(beneficiario);
    }
}
