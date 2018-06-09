/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.api.ergon.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author wandyer.silva
 */
@XmlRootElement
public class DadosFuncionaisResponseXml {
    
    //Dados Funcionais
    private Integer numFunc;

    private Integer numVinc;
    
    private String cpf;
    
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
    
    public DadosFuncionaisResponseXml () {
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
}
