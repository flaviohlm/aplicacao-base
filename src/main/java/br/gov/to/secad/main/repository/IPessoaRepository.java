package br.gov.to.secad.main.repository;

import br.gov.to.secad.main.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 *
 * @author flavio.madureira
 */
@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Serializable>, JpaSpecificationExecutor {
    
}
