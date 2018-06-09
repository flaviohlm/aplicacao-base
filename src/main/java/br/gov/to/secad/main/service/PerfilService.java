package br.gov.to.secad.main.service;

import br.gov.to.secad.main.repository.IPerfilRepository;
import br.gov.to.secad.main.domain.Perfil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;

/**
 *
 * @author wandyer.silva
 */
@SuppressWarnings("unchecked")
@Service
public class PerfilService {

    @Autowired
    public IPerfilRepository perfilRepository;

    public PerfilService(){

    }

    public void savePerfil(Perfil perfil){
        perfilRepository.save(perfil);
    }

    public void deletePerfil(Perfil perfil){
        perfilRepository.delete(perfil);
    }

    public List<Perfil> findAllPerfil(){
        return perfilRepository.findAll(where(specificationOrderNivel()));
    }

    public Perfil findOnePerfil(Integer codigo){
        return perfilRepository.findOne(codigo);
    }

    public Perfil findByNome(String nomePerfil){
        List<Perfil> aux = perfilRepository.findAll(where(specificationByNome(nomePerfil)));
        if (!aux.isEmpty()) {
            return aux.get(0);
        }

        return null;
    }

    private Specification<Perfil> specificationOrderNivel() {
        return new Specification<Perfil>() {
            @Override
            public Predicate toPredicate(Root<Perfil> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                query.orderBy(cb.asc(root.<Integer>get("nivel")));
                return query.getRestriction();
            }
        };
    }

    private Specification<Perfil> specificationByNome(final String nomePerfil) {
        return (root, query, cb) -> {
            Predicate nome = cb.equal(root.<String>get("nome"), nomePerfil);
            return cb.and(nome);
        };
    }
}
