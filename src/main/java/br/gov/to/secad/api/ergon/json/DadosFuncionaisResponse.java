package br.gov.to.secad.api.ergon.json;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author wandyer.silva
 */
public class DadosFuncionaisResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
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
    
    public DadosFuncionaisResponse() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosFuncionaisResponse that = (DadosFuncionaisResponse) o;
        return Objects.equals(numFunc, that.numFunc) &&
                Objects.equals(numVinc, that.numVinc) &&
                Objects.equals(cpf, that.cpf) &&
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

        return Objects.hash(numFunc, numVinc, cpf, dtAdmissao, dtFimContrato, dtDemissao, situacao, orgaoSigla, orgaoNome, cargo, cargoReferencia, funcao, funcaoReferencia, tipoVinculo, pisPasep, banco, agencia, conta, setor, setorId, setorMunicipio, quadro, servidorStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DadosFuncionaisResponse{");
        sb.append("numFunc=").append(numFunc);
        sb.append(", numVinc=").append(numVinc);
        sb.append(", cpf='").append(cpf).append('\'');
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
