/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.main.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wandyer.silva
 */
@XmlRootElement
public class UsuarioRequestXml {

    private String cpf;
    
    private String senhaHash;

    public UsuarioRequestXml() {
        
    }
    
    public UsuarioRequestXml(String cpf) {
        this.cpf = cpf;
    }
    
    public UsuarioRequestXml(String cpf, String senhaHash) {
        this.cpf = cpf;
        this.senhaHash = senhaHash;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    
}
