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
public enum FormacaoAcademica implements Serializable {

	GRADUADO("Graduado"),
	ESPECIALISTA("Especialista"),
	MESTRE("Mestre"),
	DOUTOR("Doutor");

	private final String label;

	private FormacaoAcademica(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
