package br.gov.to.secad.api.main.xml;

import br.gov.to.secad.main.domain.Usuario;

/**
 * @author wandyer.silva
 */
public class UsuarioResponseXml {

    private Integer id;

    private String cpf;

    private String email;

    private String nome;

    private boolean ativo;

    public UsuarioResponseXml() {
    }

    public UsuarioResponseXml(Usuario usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getPessoa().getCpf();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id : " + this.id + '\n');
        stringBuilder.append("CPF : " + this.cpf + '\n');
        stringBuilder.append("E-mail : " + this.email + '\n');
        stringBuilder.append("Nome : " + this.nome + '\n');
        stringBuilder.append("Ativo : " + this.ativo + '\n');
        
        return stringBuilder.toString();
    }
    
    
}