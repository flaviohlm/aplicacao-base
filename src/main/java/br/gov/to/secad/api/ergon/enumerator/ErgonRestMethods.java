/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.ergon.enumerator;

import java.io.Serializable;

/**
 *
 * @author wandyer.silva
 */
public enum ErgonRestMethods implements Serializable{
    PESSOAIS("pessoais"),
    FUNCIONAIS("funcionais"),
    SIMPLES("simples"),
    COMPLETOS("completos"),
    PESSOAIS_LISTA("pessoaisLista"),
    FUNCIONAIS_LISTA("funcionaisLista"),
    SIMPLES_LISTA("simplesLista"),
    COMPLETOS_LISTA("completosLista"),
    LISTA_SECRETARIOS("listaSecretariosPorPasta"),
    VERIFICAR_SERVIDOR("verificarServidor"),
    LISTA_VINCULOS("listaVinculos");
    
    private final String label;

    private ErgonRestMethods(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
}
