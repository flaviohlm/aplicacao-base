package br.gov.to.secad.main.service;

import br.gov.to.secad.main.domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;
import br.gov.to.secad.main.repository.IPessoaRepository;

/**
 * @author flavio.madureira
 */
@Service
public class PessoaService {
	@Autowired
	public IPessoaRepository repository;

	public PessoaService() {
	}

	public void savePessoa(Pessoa objeto) {
		repository.save(objeto);
	}

	public void savePessoa(List<Pessoa> objeto) {
		repository.save(objeto);
	}

	public void deletePessoa(Pessoa objeto) {
		repository.delete(objeto);
	}

	public Pessoa findOnePessoa(Integer codigo) {
		return repository.findOne(codigo);
	}

	public Pessoa findOnePessoa(String cpf) {
		List<Pessoa> listAux = repository.findAll(where(specificationByCpf(cpf)));
		if (!listAux.isEmpty()) {
			return listAux.get(0);
		}

		return null;
	}

	public List<Pessoa> findAllPessoa() {
		return repository.findAll(where(specificationOrder()));
	}


	//SPECIFICATIONS====================================================================================================
	public Specification<Pessoa> specificationOrder() {
		return new Specification<Pessoa>() {
			@Override
			public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				query.orderBy(cb.asc(root.<Integer>get("id")));
				return query.getRestriction();
			}
		};
	}

	public Specification<Pessoa> specificationByCpf(final String cpf) {
		return new Specification<Pessoa>() {
			@Override
			public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				query.orderBy(cb.asc(root.<Integer>get("id")));

				Predicate cpfp = cb.equal(root.<String>get("cpf"), cpf);

				return cb.and(cpfp);
			}
		};
	}
}
