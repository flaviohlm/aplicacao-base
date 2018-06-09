package br.gov.to.secad.main.domain;

import br.gov.to.secad.api.main.xml.UsuarioResponseXml;
import br.gov.to.secad.main.util.ConvertePasswordParaMD5;
import org.apache.commons.lang3.text.WordUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author flavio.madureira
 */
@Entity
@Table(name = "usuario", schema="sigef")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "excluido")
    private Boolean excluido = false;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<PerfilUsuario> perfilUsuarioList;

    @ManyToMany
    @JoinTable(schema = "qualifica", name = "perfil_usuario", joinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "perfil_id", referencedColumnName = "ID")})
    private List<Perfil> listaPerfis = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id") 
    private Pessoa pessoa;    

    @Column(name = "data_hora_ultimo_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraUltimoLogin;
    
    @Column(name = "ultimo_login_perfil_id")
    private Integer ultimoLoginPerfilId;    
    
    @Transient
    private String password;
    
    @Transient
    private Perfil perfilAtual;
    
    @Transient
    private String orgaos;
            
    public Usuario() {
        pessoa = new Pessoa();
    }
    
    public Usuario(UsuarioResponseXml usuarioResponse) {
        this.pessoa = new Pessoa();
        this.pessoa.setNome(usuarioResponse.getNome());
        this.pessoa.setCpf(usuarioResponse.getCpf());
        this.pessoa.setEmail(usuarioResponse.getEmail());
    }

    public Usuario(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
//    public String hashPassword() {
//        try {
//            return HashPassword.md5(this.password);
//        } catch (Exception ex) {
//            System.err.println("Erro ao Criptografar Senha " + ex.getLocalizedMessage());
//        }
//        return null;
//    }

    @XmlTransient
    public List<PerfilUsuario> getPerfilUsuarioList() {
        return perfilUsuarioList;
    }

    public void setPerfilUsuarioList(List<PerfilUsuario> perfilUsuarioList) {
        this.perfilUsuarioList = perfilUsuarioList;
    }

    public List<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    public Perfil getPerfilAtual() {
        return perfilAtual;
    }

    public void setPerfilAtual(Perfil perfilAtual) {
        this.perfilAtual = perfilAtual;
    }

    public Date getDataHoraUltimoLogin() {
        return dataHoraUltimoLogin;
    }

    public void setDataHoraUltimoLogin(Date dataHoraUltimoLogin) {
        this.dataHoraUltimoLogin = dataHoraUltimoLogin;
    }

    public Integer getUltimoLoginPerfilId() {
        return ultimoLoginPerfilId;
    }

    public void setUltimoLoginPerfilId(Integer ultimoLoginPerfilId) {
        this.ultimoLoginPerfilId = ultimoLoginPerfilId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = ConvertePasswordParaMD5.convertePasswordParaMD5(password);
    }

    public String getOrgaos() {
        return orgaos;
    }

    public void setOrgaos(String orgaos) {
        this.orgaos = orgaos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getExcluido() {
        return excluido;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
