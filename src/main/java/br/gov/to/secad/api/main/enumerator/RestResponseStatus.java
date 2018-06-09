/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.main.enumerator;

import java.io.Serializable;

/**
 *
 * @author wandyer.silva
 */
public enum RestResponseStatus implements Serializable {
    SUCESSO("sucesso"), 
    NAO_ENCONTRADO("usuario_nao_encontrado"),
    ERRO("erro");
    
    private final String label;

    private RestResponseStatus(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
}
