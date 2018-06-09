/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.ergon.json;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author wandyer.silva
 */
public class DadosCompletosResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    //Dados Pessoais
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

    //Dados Funcionais
    private Date dtAdmissao;

    private Date dtFimContrato;

    private Date dtDemissao;

    private String situacao;

    private String orgaoSigla;

    private String orgaoNome;

    private String cargo;

    private String cargoReferencia;

    private String funcao;

    private String funcaoReferencia;

    private String tipoVinculo;

    private Long pisPasep;

    private String banco;

    private String agencia;

    private String conta;

    private String setor;

    private String setorId;

    private String setorMunicipio;

    private String quadro;
    
    private String servidorStatus;

    public DadosCompletosResponse() {

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

    public Date getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(Date dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
    }

    public Date getDtFimContrato() {
        return dtFimContrato;
    }

    public void setDtFimContrato(Date dtFimContrato) {
        this.dtFimContrato = dtFimContrato;
    }

    public Date getDtDemissao() {
        return dtDemissao;
    }

    public void setDtDemissao(Date dtDemissao) {
        this.dtDemissao = dtDemissao;
    }

    public String getOrgaoSigla() {
        return orgaoSigla;
    }

    public void setOrgaoSigla(String orgaoSigla) {
        this.orgaoSigla = orgaoSigla;
    }

    public String getOrgaoNome() {
        return orgaoNome;
    }

    public void setOrgaoNome(String orgaoNome) {
        this.orgaoNome = orgaoNome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargoReferencia() {
        return cargoReferencia;
    }

    public void setCargoReferencia(String cargoReferencia) {
        this.cargoReferencia = cargoReferencia;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncaoReferencia() {
        return funcaoReferencia;
    }

    public void setFuncaoReferencia(String funcaoReferencia) {
        this.funcaoReferencia = funcaoReferencia;
    }

    public String getTipoVinculo() {
        return tipoVinculo;
    }

    public void setTipoVinculo(String tipoVinculo) {
        this.tipoVinculo = tipoVinculo;
    }

    public Long getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(Long pisPasep) {
        this.pisPasep = pisPasep;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSetorId() {
        return setorId;
    }

    public void setSetorId(String setorId) {
        this.setorId = setorId;
    }

    public String getSetorMunicipio() {
        return setorMunicipio;
    }

    public void setSetorMunicipio(String setorMunicipio) {
        this.setorMunicipio = setorMunicipio;
    }

    public String getQuadro() {
        return quadro;
    }

    public void setQuadro(String quadro) {
        this.quadro = quadro;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosCompletosResponse that = (DadosCompletosResponse) o;
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
                Objects.equals(dtAdmissao, that.dtAdmissao) &&
                Objects.equals(dtFimContrato, that.dtFimContrato) &&
                Objects.equals(dtDemissao, that.dtDemissao) &&
                Objects.equals(situacao, that.situacao) &&
                Objects.equals(orgaoSigla, that.orgaoSigla) &&
                Objects.equals(orgaoNome, that.orgaoNome) &&
                Objects.equals(cargo, that.cargo) &&
                Objects.equals(cargoReferencia, that.cargoReferencia) &&
                Objects.equals(funcao, that.funcao) &&
                Objects.equals(funcaoReferencia, that.funcaoReferencia) &&
                Objects.equals(tipoVinculo, that.tipoVinculo) &&
                Objects.equals(pisPasep, that.pisPasep) &&
                Objects.equals(banco, that.banco) &&
                Objects.equals(agencia, that.agencia) &&
                Objects.equals(conta, that.conta) &&
                Objects.equals(setor, that.setor) &&
                Objects.equals(setorId, that.setorId) &&
                Objects.equals(setorMunicipio, that.setorMunicipio) &&
                Objects.equals(quadro, that.quadro) &&
                Objects.equals(servidorStatus, that.servidorStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numFunc, numVinc, cpf, nome, dataNascimento, sexo, numeroRg, orgaoRg, contato, email, endereco, bairro, cep, cidade, uf, escolaridade, estadoCivil, mae, pai, dtAdmissao, dtFimContrato, dtDemissao, situacao, orgaoSigla, orgaoNome, cargo, cargoReferencia, funcao, funcaoReferencia, tipoVinculo, pisPasep, banco, agencia, conta, setor, setorId, setorMunicipio, quadro, servidorStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DadosCompletosResponse{");
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
        sb.append(", dtAdmissao=").append(dtAdmissao);
        sb.append(", dtFimContrato=").append(dtFimContrato);
        sb.append(", dtDemissao=").append(dtDemissao);
        sb.append(", situacao='").append(situacao).append('\'');
        sb.append(", orgaoSigla='").append(orgaoSigla).append('\'');
        sb.append(", orgaoNome='").append(orgaoNome).append('\'');
        sb.append(", cargo='").append(cargo).append('\'');
        sb.append(", cargoReferencia='").append(cargoReferencia).append('\'');
        sb.append(", funcao='").append(funcao).append('\'');
        sb.append(", funcaoReferencia='").append(funcaoReferencia).append('\'');
        sb.append(", tipoVinculo='").append(tipoVinculo).append('\'');
        sb.append(", pisPasep=").append(pisPasep);
        sb.append(", banco='").append(banco).append('\'');
        sb.append(", agencia='").append(agencia).append('\'');
        sb.append(", conta='").append(conta).append('\'');
        sb.append(", setor='").append(setor).append('\'');
        sb.append(", setorId='").append(setorId).append('\'');
        sb.append(", setorMunicipio='").append(setorMunicipio).append('\'');
        sb.append(", quadro='").append(quadro).append('\'');
        sb.append(", servidorStatus='").append(servidorStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
