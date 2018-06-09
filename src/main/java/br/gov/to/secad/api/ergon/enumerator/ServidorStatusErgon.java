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
public enum ServidorStatusErgon implements Serializable{
    ATIVO("ativo"), 
    INATIVO("inativo"),
    INEXISTENTE("inexistente");
    
    private final String label;

    private ServidorStatusErgon(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
}
