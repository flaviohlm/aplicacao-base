package br.gov.to.secad.main.repository;

import java.io.Serializable;
import java.util.List;
import br.gov.to.secad.main.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wandyer.silva
 */
@Repository
public interface IMenuRepository extends JpaRepository<Menu, Serializable>, JpaSpecificationExecutor {

	@Query("SELECT m FROM Menu m JOIN FETCH m.perfis mp WHERE mp.id = :id AND m.nivel > 0 AND m.ativo = true ORDER BY m.ordem, m.url")
	List<Menu> findByPerfil(@Param("id") Integer id);

	@Query("SELECT m FROM Menu m WHERE m.ativo = true")
	List<Menu> findByAtivo();

	@Query("SELECT m FROM Menu m ORDER BY m.nivel, m.ordem, m.id")
	List<Menu> findAllMenu();

	@Query("SELECT m FROM Menu m WHERE m.nivel = :nivel AND m.ativo = true ORDER BY m.ordem, m.id")
	List<Menu> findByNivel(@Param("nivel") Integer nivel);

	@Query("SELECT m FROM Menu m WHERE m.nivel = :nivel AND m.ativo = false ORDER BY m.ordem, m.id")
	List<Menu> findByNivelDesativados(@Param("nivel") Integer nivel);

	@Query("SELECT m FROM Menu m WHERE m.pai.id = :idPai AND m.ordem IS NOT NULL ORDER BY m.ordem DESC")
	List<Menu> findByPai(@Param("idPai") Integer idPai);

	@Query("SELECT m FROM Menu m WHERE m.pai.id = :idPai AND m.ordem = :ordem")
	List<Menu> findByPaiOrdem(@Param("idPai") Integer idPai, @Param("ordem") Integer ordem);

	@Query(value = "SELECT mo.* FROM sigef.Menu as mo, sigef.menu_perfil mu\n" +
			"WHERE mo.id = mu.menu_id AND mo.ativo=TRUE AND mu.perfil_id = 1 ORDER BY mo.ordem, mo.pai", nativeQuery = true)
	List<Menu> findByMenuNivel(Integer id);

}
