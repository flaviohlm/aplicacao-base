package br.gov.to.secad.api.ergon.json;

/**
 * @author wandyer.silva
 */
public class DadosVinculoResponse {

    private String cpf;

    private Integer numFunc;

    private Integer numVinc;

    private String nome;

    private String cargo;

    private String cargoFuncao;

    private String funcao;

    private String orgaoFantasia;

    private String orgaoNome;

    private String quadro;

    private String situacao;

    public DadosVinculoResponse() {
    }

    //Getters & Setters
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargoFuncao() {
        return cargoFuncao;
    }

    public void setCargoFuncao(String cargoFuncao) {
        this.cargoFuncao = cargoFuncao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getOrgaoFantasia() {
        return orgaoFantasia;
    }

    public void setOrgaoFantasia(String orgaoFantasia) {
        this.orgaoFantasia = orgaoFantasia;
    }

    public String getOrgaoNome() {
        return orgaoNome;
    }

    public void setOrgaoNome(String orgaoNome) {
        this.orgaoNome = orgaoNome;
    }

    public String getQuadro() {
        return quadro;
    }

    public void setQuadro(String quadro) {
        this.quadro = quadro;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DadosVinculoResponse{");
        sb.append("cpf='").append(cpf).append('\'');
        sb.append(", numFunc=").append(numFunc);
        sb.append(", numVinc=").append(numVinc);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", cargo='").append(cargo).append('\'');
        sb.append(", cargoFuncao='").append(cargoFuncao).append('\'');
        sb.append(", funcao='").append(funcao).append('\'');
        sb.append(", orgaoFantasia='").append(orgaoFantasia).append('\'');
        sb.append(", orgaoNome='").append(orgaoNome).append('\'');
        sb.append(", quadro='").append(quadro).append('\'');
        sb.append(", situacao='").append(situacao).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
