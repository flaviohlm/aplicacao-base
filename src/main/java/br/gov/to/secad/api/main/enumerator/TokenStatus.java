package br.gov.to.secad.api.main.enumerator;

import java.io.Serializable;

/**
 *
 * @author wandyer.silva
 */
public enum TokenStatus implements Serializable{
    OK("ok"), 
    INVALIDO("invalido"),
    EXPIRADO("expirado");
    
    private final String label;

    private TokenStatus(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
}
