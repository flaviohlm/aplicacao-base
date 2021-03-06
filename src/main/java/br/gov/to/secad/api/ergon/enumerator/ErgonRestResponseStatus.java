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
public enum ErgonRestResponseStatus implements Serializable{
    SUCESSO("sucesso"), 
    NAO_ENCONTRADO("servidor_nao_encontrado"),
    NAO_ENCONTRADOS("servidores_nao_encontrados"),
    ERRO("erro");
    
    private final String label;

    private ErgonRestResponseStatus(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
}
