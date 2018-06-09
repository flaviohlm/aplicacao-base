/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.main.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author flavio.madureira
 */
@Entity
@Table(name = "pessoa", schema="sigef")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findById", query = "SELECT p FROM Pessoa p WHERE p.id = :id")
    , @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf")
    , @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome")
    , @NamedQuery(name = "Pessoa.findByNumFunc", query = "SELECT p FROM Pessoa p WHERE p.numFunc = :numFunc")
    , @NamedQuery(name = "Pessoa.findByNumVinc", query = "SELECT p FROM Pessoa p WHERE p.numVinc = :numVinc")
    , @NamedQuery(name = "Pessoa.findByContato", query = "SELECT p FROM Pessoa p WHERE p.contato = :contato")
    , @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email")
    , @NamedQuery(name = "Pessoa.findByOrgao", query = "SELECT p FROM Pessoa p WHERE p.orgao = :orgao")
    , @NamedQuery(name = "Pessoa.findByLotacao", query = "SELECT p FROM Pessoa p WHERE p.lotacao = :lotacao")
    , @NamedQuery(name = "Pessoa.findByCargo", query = "SELECT p FROM Pessoa p WHERE p.cargo = :cargo")
    , @NamedQuery(name = "Pessoa.findByQuadro", query = "SELECT p FROM Pessoa p WHERE p.quadro = :quadro")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "contato")
    private String contato;
    
    @Column(name = "num_func")
    private Integer numFunc;
    
    @Column(name = "num_vinc")
    private Integer numVinc;
    
    @Column(name = "orgao")
    private String orgao;
    
    @Column(name = "lotacao")
    private String lotacao;
    
    @Column(name = "cargo")
    private String cargo;
    
    @Column(name = "quadro")
    private String quadro;      
    
    @OneToOne(mappedBy = "pessoa")    
    private Usuario usuario;
    
    @Transient
    private String matricula;
    
    
    public Pessoa() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        //Primeira letra maiuscula
        this.nome = WordUtils.capitalizeFully(nome);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("\\.", "").replace("-", "");
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @JsonIgnore
    public String getCpfFormatado() {
//        if (cpf == null) {
//            return "";
//        } else {
//            return CpfUtil.imprimeCPF(CpfUtil.cpfConverteString(cpf));
//        }
        return cpf;
    }
    
    public String getMatricula() {
        if(this.getNumFunc() != null && this.getNumVinc() != null){
            matricula = this.getNumFunc() + "-" +this.getNumVinc();
        }
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getQuadro() {
        return quadro;
    }

    public void setQuadro(String quadro) {
        this.quadro = quadro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "br.gov.to.secad.pep.domain.Pessoa[ id=" + id + " ]";
    }
    
}
