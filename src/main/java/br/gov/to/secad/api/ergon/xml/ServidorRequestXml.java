/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.ergon.xml;

import br.gov.to.secad.main.domain.Usuario;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wandyer.silva
 */
@XmlRootElement
public class ServidorRequestXml {

   private String cpf;
    
    private Integer numFunc;

    private Integer numVinc;


    public ServidorRequestXml() {
        
    }
    
    public ServidorRequestXml(Usuario usuario) {
        this.cpf = usuario.getPessoa().getCpf();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getNumFunc() {
        return numFunc;
    }

    public void setNumFunc(Integer numFunc) {
        this.numFunc = numFunc;
    }

    public Integer getNumVinc() {
        return numVinc;
    }

    public void setNumVinc(Integer numVinc) {
        this.numVinc = numVinc;
    }
    
    

    
}
