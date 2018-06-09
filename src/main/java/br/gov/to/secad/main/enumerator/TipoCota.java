package br.gov.to.secad.main.enumerator;

import java.io.Serializable;

public enum TipoCota implements Serializable {

	AMPLACONCORRENCIA("Ampla Concorrência"),
	DEFICIENTE("Deficiente"),
	INDIGENA("Indígena"),
	QUILOMBOLA("Quilombola"),
	TECNICOADMUFT("Técnico Administrativo da UFT"),
	COOPERACAOSECAD("Termo de Cooperação Técnica da SECAD");

	private final String label;

	private TipoCota(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
