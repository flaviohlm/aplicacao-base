package br.gov.to.secad.main.enumerator;

import java.io.Serializable;

public enum TipoInscricao implements Serializable {

	COMUNIDADEGERAL("Comunidade Geral"),
	DEFENSORIA("Defensoria Pública"),
	MINISTERIOPUBLICOESTADUAL("Ministério Público Estadual"),
	PODEREXECUTIVO("Poder Executivo"),
	TECNICOADMINISTRATIVOUFT("Técnico Administrativo da UFT"),
	UFT("Universidade Federal do Tocantins"),
	UNITINS("Universidade Estadual do Tocantins");

	private final String label;

	private TipoInscricao(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
