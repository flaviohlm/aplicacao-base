package br.gov.to.secad.main.repository;

import br.gov.to.secad.main.domain.Perfil;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Serializable>, JpaSpecificationExecutor {
     
}
