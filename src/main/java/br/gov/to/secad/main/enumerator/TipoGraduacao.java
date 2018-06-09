/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.main.enumerator;

import java.io.Serializable;

/**
 * @author wandyer.silva
 */
public enum TipoGraduacao implements Serializable {

	GRADUACAOAREACURSO("Graduação na área do curso"),
	GRADUACAOAREADIFERENTE("Graduação em outra área diferente do curso");

	private final String label;

	private TipoGraduacao(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
