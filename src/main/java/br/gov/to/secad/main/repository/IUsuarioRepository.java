package br.gov.to.secad.main.repository;

import br.gov.to.secad.main.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author flavio.madureira
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Serializable>, JpaSpecificationExecutor {

	@Query(value = "SELECT * FROM sigef.usuario usuario INNER JOIN sigef.pessoa pessoa " +
			"ON usuario.pessoa_id = pessoa.id WHERE pessoa.cpf = ?1 AND usuario.password = ?2", nativeQuery = true)
	Usuario findByCpfSenha(String cpf, String senha);

	@Query(value = "SELECT * FROM sigef.usuario usuario INNER JOIN sigef.pessoa pessoa " +
			"ON usuario.pessoa_id = pessoa.id WHERE pessoa.cpf = ?1", nativeQuery = true)
	Usuario findByCpf(String cpf);

}
