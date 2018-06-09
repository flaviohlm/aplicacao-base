package br.gov.to.secad.api.ergon.json;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author wandyer.silva
 */
public class DadosPessoaisResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numFunc;

    private Integer numVinc;

    private String cpf;

    private String nome;

    private Date dataNascimento;

    private String sexo;

    private String numeroRg;

    private String orgaoRg;

    private String contato;

    private String email;

    private String endereco;

    private String bairro;

    private Integer cep;

    private String cidade;

    private String uf;

    private String escolaridade;

    private String estadoCivil;

    private String mae;

    private String pai;
    
    private String servidorStatus;

    public DadosPessoaisResponse() {
    }

    //Getters & Setters
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNumeroRg() {
        return numeroRg;
    }

    public void setNumeroRg(String numeroRg) {
        this.numeroRg = numeroRg;
    }

    public String getOrgaoRg() {
        return orgaoRg;
    }

    public void setOrgaoRg(String orgaoRg) {
        this.orgaoRg = orgaoRg;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServidorStatus() {
        return servidorStatus;
    }

    public void setServidorStatus(String servidorStatus) {
        this.servidorStatus = servidorStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosPessoaisResponse that = (DadosPessoaisResponse) o;
        return Objects.equals(numFunc, that.numFunc) &&
                Objects.equals(numVinc, that.numVinc) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(dataNascimento, that.dataNascimento) &&
                Objects.equals(sexo, that.sexo) &&
                Objects.equals(numeroRg, that.numeroRg) &&
                Objects.equals(orgaoRg, that.orgaoRg) &&
                Objects.equals(contato, that.contato) &&
                Objects.equals(email, that.email) &&
                Objects.equals(endereco, that.endereco) &&
                Objects.equals(bairro, that.bairro) &&
                Objects.equals(cep, that.cep) &&
                Objects.equals(cidade, that.cidade) &&
                Objects.equals(uf, that.uf) &&
                Objects.equals(escolaridade, that.escolaridade) &&
                Objects.equals(estadoCivil, that.estadoCivil) &&
                Objects.equals(mae, that.mae) &&
                Objects.equals(pai, that.pai) &&
                Objects.equals(servidorStatus, that.servidorStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numFunc, numVinc, cpf, nome, dataNascimento, sexo, numeroRg, orgaoRg, contato, email, endereco, bairro, cep, cidade, uf, escolaridade, estadoCivil, mae, pai, servidorStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DadosPessoaisResponse{");
        sb.append("numFunc=").append(numFunc);
        sb.append(", numVinc=").append(numVinc);
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", dataNascimento=").append(dataNascimento);
        sb.append(", sexo='").append(sexo).append('\'');
        sb.append(", numeroRg='").append(numeroRg).append('\'');
        sb.append(", orgaoRg='").append(orgaoRg).append('\'');
        sb.append(", contato='").append(contato).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", endereco='").append(endereco).append('\'');
        sb.append(", bairro='").append(bairro).append('\'');
        sb.append(", cep=").append(cep);
        sb.append(", cidade='").append(cidade).append('\'');
        sb.append(", uf='").append(uf).append('\'');
        sb.append(", escolaridade='").append(escolaridade).append('\'');
        sb.append(", estadoCivil='").append(estadoCivil).append('\'');
        sb.append(", mae='").append(mae).append('\'');
        sb.append(", pai='").append(pai).append('\'');
        sb.append(", servidorStatus='").append(servidorStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
