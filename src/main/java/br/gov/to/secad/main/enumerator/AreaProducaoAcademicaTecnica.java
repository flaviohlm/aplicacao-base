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
public enum AreaProducaoAcademicaTecnica implements Serializable {

	EDUCACAO("Educação"),
	POLITICAS_PUBLICAS("Políticas Públicas"),
	GESTAO("Gestão do Conhecimento e de Tecnologias"),
	ADMINISTRACAO("Administração"),
	COMUNICACAO_SOCIAL("Comunicação Social");

	private final String label;

	private AreaProducaoAcademicaTecnica(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
