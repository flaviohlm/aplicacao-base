package br.gov.to.secad.api.ergon.json;


import br.gov.to.secad.main.domain.Usuario;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author wandyer.silva
 */
public class ServidorRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;

    private Integer numFunc;

    private Integer numVinc;


    public ServidorRequest() {

    }

    public ServidorRequest(Usuario usuario) {
        this.cpf = usuario.getPessoa().getCpf();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServidorRequest that = (ServidorRequest) o;
        return Objects.equals(cpf, that.cpf) &&
                Objects.equals(numFunc, that.numFunc) &&
                Objects.equals(numVinc, that.numVinc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cpf, numFunc, numVinc);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServidorRequest{");
        sb.append("cpf='").append(cpf).append('\'');
        sb.append(", numFunc=").append(numFunc);
        sb.append(", numVinc=").append(numVinc);
        sb.append('}');
        return sb.toString();
    }
}
