package br.gov.to.secad.api.ergon.json;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author wandyer.silva
 */
public class DadosSimplesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numFunc;

    private Integer numVinc;
    
    private String cpf;

    private String nome;

    private String email;
    
    private String servidorStatus;
    
    public DadosSimplesResponse() {
        
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
        DadosSimplesResponse that = (DadosSimplesResponse) o;
        return Objects.equals(numFunc, that.numFunc) &&
                Objects.equals(numVinc, that.numVinc) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(email, that.email) &&
                Objects.equals(servidorStatus, that.servidorStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numFunc, numVinc, cpf, nome, email, servidorStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DadosSimplesResponse{");
        sb.append("numFunc=").append(numFunc);
        sb.append(", numVinc=").append(numVinc);
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", servidorStatus='").append(servidorStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
