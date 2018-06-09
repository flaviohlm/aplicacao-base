package br.gov.to.secad.main.service;

import br.gov.to.secad.api.ergon.config.ErgonRestClient;
import br.gov.to.secad.api.ergon.enumerator.ServidorStatusErgon;
import br.gov.to.secad.api.ergon.json.DadosCompletosResponse;
import br.gov.to.secad.api.ergon.json.DadosSimplesResponse;
import br.gov.to.secad.api.ergon.json.ServidorRequest;
import br.gov.to.secad.main.domain.Usuario;
import br.gov.to.secad.main.repository.IUsuarioRepository;
import br.gov.to.secad.main.security.util.SpringSecurityUtil;
import br.gov.to.secad.main.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.MaskFormatter;

import java.text.ParseException;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * @author flavio.madureira
 */
@Service
public class UsuarioService {

	@Autowired
	public IUsuarioRepository usuarioRepository;

	@Autowired
	private ErgonRestClient ergonWsClient;

	private static Logger logger = LogManager.getLogger();

	public UsuarioService() {
	}

	public void saveUsuario(Usuario usuario) {
		try {
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		usuarioRepository.saveAndFlush(usuario);
	}

	public Usuario findOneUsuario(Integer codigo) {
		return usuarioRepository.findOne(codigo);
	}

	public Usuario findByHashValidacao(String hashValidacao) {

		List<Usuario> aux = usuarioRepository.findAll(where(specificationHashValidacao(hashValidacao)));


		Usuario usuarios = ((JpaSpecificationExecutor<Usuario>) usuarioRepository).findOne(where(specificationHashValidacao(hashValidacao)));

		return ((JpaSpecificationExecutor<Usuario>) usuarioRepository).findOne(where(specificationHashValidacao(hashValidacao)));
	}

	public List<Usuario> findAllUsuarios() {
		try {
			return usuarioRepository.findAll(where(specificationAll()));
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	private Specification<Usuario> specificationHashValidacao(String hashValidacao) {
		return (root, query, cb) -> {
			Predicate hash = cb.equal(root.<String>get("hashValidacao"), hashValidacao);
			return cb.and(hash);
		};
	}

	private Specification<Usuario> specificationAll() {
		return new Specification<Usuario>() {
			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				query.orderBy(cb.asc(root.<Integer>get("nome")));
				return query.getRestriction();
			}
		};
	}

	public Usuario findByCpf(String cpf) {
		try {
			Usuario c = usuarioRepository.findByCpf(cpf);
			return c;
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getLocalizedMessage());
			return null;
		}
	}

	public boolean findByCpfSenha(String cpf, String senha) {
		try {
			Usuario usu = usuarioRepository.findByCpfSenha(cpf, senha);

			if (usu == null) {
				return false;
			}

			return true;
		} catch (Exception e) {
			System.out.println("Erro " + e.getLocalizedMessage());
			return false;
		}
	}

	public Usuario findByCpfSenhaa(String cpf, String senha) {
		try {
			return usuarioRepository.findByCpfSenha(cpf, senha);
		} catch (Exception e) {
			logger.error(e);
			logger.error(SpringSecurityUtil.getCPF());
			if (cpf != null) {
				logger.error(cpf);
			}
			return null;
		}
	}

	public Usuario preencherDadosUsuario(Usuario usuario) {
		ServidorRequest sr = new ServidorRequest(usuario);
		DadosSimplesResponse ds = ergonWsClient.findDadosSimples(sr);

		if (!ds.getServidorStatus().equalsIgnoreCase(ServidorStatusErgon.INEXISTENTE.toString())) {
//			usuario.getPessoaPos().setNome(ds.getNome());
//			usuario.getPessoaPos().setEmail(ds.getEmail());
//			usuario.getPessoaPos().setServidorEstado(true);
		} else {
			return null;
		}

		return usuario;
	}

	public Usuario preencherDadosUsuarioCompleto(Usuario usuario) {
		ServidorRequest sr = new ServidorRequest(usuario);
		DadosCompletosResponse ds = ergonWsClient.findDadosCompletos(sr);

		if (!ds.getServidorStatus().equalsIgnoreCase(ServidorStatusErgon.INEXISTENTE.toString())) {
			usuario.getPessoa().setNome(ds.getNome());
                        usuario.getPessoa().setEmail(ds.getEmail());
                        usuario.getPessoa().setNumFunc(ds.getNumFunc());
                        usuario.getPessoa().setNumVinc(ds.getNumVinc());
                        usuario.getPessoa().setOrgao(ds.getOrgaoSigla());
                        usuario.getPessoa().setQuadro(ds.getQuadro());
                        usuario.getPessoa().setCargo(ds.getCargo());
                        usuario.getPessoa().setLotacao(ds.getSetor());
		} else {
			return null;
		}

		return usuario;
	}

	public static String formatString(String value, String pattern) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter(pattern);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(value);
		} catch (ParseException ex) {
			return value;
		}
	}

}
