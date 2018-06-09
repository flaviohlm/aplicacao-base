package br.gov.to.secad.main.repository;

import java.io.Serializable;
import java.util.ArrayList;

import br.gov.to.secad.main.domain.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author flavio.madureira
 */
@Repository
public interface IPerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Serializable>, JpaSpecificationExecutor {
    
    @Query(value = "SELECT t1.id, t1.perfil_id, t1.usuario_id " +
                   "FROM bcoproducao.siga_unicet.usuario t0, siga_unicet.perfil_usuario t1 " +
                   "WHERE t0.cpf = ?1 " +
                   "AND t0.id = t1.usuario_id " +
                   "AND t0.dtfim IS NULL ", nativeQuery = true)    
    ArrayList<PerfilUsuario> findAtivosByCpf(String cpf);

    @Query(value = "SELECT pu FROM siga_unicet.perfil_usuario pu " +
            "INNER JOIN siga_unicet.usuario usuario ON usuario.id = pu.usuario_id " +
            "WHERE pu.usuario_id = ?1", nativeQuery = true)
    ArrayList<PerfilUsuario> findByCpf(String cpf);
}
