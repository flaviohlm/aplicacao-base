/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.main.util;


import br.gov.to.secad.main.util.FiltroEspecification;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author alex.santos
 */
public abstract class GenericSpecification<T> implements Serializable {

    private Class<T> entityClass;

    private String fildeSQL;

    public Specification<T> especificantionFiltros(final FiltroEspecification filtro) {
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //query.distinct(filtro.isDistinct());

                try {
                    if ((!filtro.getValor1().equals(0) && !filtro.getValor1().equals("")) || filtro.getTipoComparacao().equals("isNull")) {
                        Expression<Character> fildeExpressao = feildExpression(filtro, root);

                        switch (filtro.getTipoComparacao()) {
                            case "equal":
                                return cb.equal((fildeExpressao), filtro.getValor1());

                            case "or":
                                return cb.notEqual((fildeExpressao), filtro.getValor1());

                            case "isNull":
                                return cb.isNull(root.get(filtro.getFildeNivel1()));

                            case "notEqual":
                                return cb.notEqual((fildeExpressao), filtro.getValor1());

                            case "like":

                                return cb.like(cb.lower(cb.toString(fildeExpressao)), filtro.getValor1().toString() != null ? getLikePattern(filtro.getValor1().toString()) : null);

                            case "likeUpper":
                                return cb.like(cb.upper(cb.toString(fildeExpressao)), filtro.getValor1().toString() != null ? getLikePattern(filtro.getValor1().toString()) : null);

                        }
                        switch (filtro.getTipo()) {
                            case "Data":
                                return comparacaoDatas(cb, root);
                        }
                        System.out.println("Este não é um valor Válido!");
                    }

                } catch (Exception ex) {
                    System.out.println("Erro ao montar Specification ->CursoService: " + ex.getMessage());
                }
                return null;
            }

            private Predicate comparacaoDatas(CriteriaBuilder cb, Root<T> root) {
                switch (filtro.getTipoComparacao()) {
                    case "menorIgual":
                        return cb.lessThanOrEqualTo(root.<Date>get(fildeSQL), (Date) filtro.getValor1());
                    case "maiorIgual":
                        return cb.greaterThanOrEqualTo(root.<Date>get(fildeSQL), (Date) filtro.getValor1());
                    case "entre":
                        return cb.between(root.<Date>get(fildeSQL), (Date) filtro.getValor1(), (Date) filtro.getValor2());
                }
                return null;
            }

        };
    }

    public Expression feildExpression(FiltroEspecification filtro, Root<T> root) {
        if (!filtro.getFilderNivel2().equals("")) {
            if (!filtro.getFilderNivel3().equals("")) {
                fildeSQL = filtro.getFilderNivel3();
                return root.get(filtro.getFildeNivel1()).get(filtro.getFilderNivel2()).get(filtro.getFilderNivel3());
            } else {
                fildeSQL = filtro.getFilderNivel2();
                return root.get(filtro.getFildeNivel1()).get(filtro.getFilderNivel2());
            }
        } else {
            fildeSQL = filtro.getFildeNivel1();
            return root.<Character>get(filtro.getFildeNivel1());
        }
    }

    public String getLikePattern(final String searchTerm) {
        StringBuilder pattern = new StringBuilder();
        pattern.append("%");
        pattern.append(searchTerm.toUpperCase());
        pattern.append("%");
        return pattern.toString();
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

}
