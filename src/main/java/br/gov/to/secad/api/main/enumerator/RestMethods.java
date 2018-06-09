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
public enum RestMethods implements Serializable {
    TOKEN("token"),
    LOGIN("login"),
    USER("user");
    
    private final String label;

    private RestMethods(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
}
