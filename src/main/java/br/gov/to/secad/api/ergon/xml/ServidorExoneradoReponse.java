package br.gov.to.secad.api.ergon.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class ServidorExoneradoReponse {

	private String nome;

	private String cpf;

	private Integer numFunc;

	private Integer numVinc;

	private Date dataVacancia;

	public ServidorExoneradoReponse() {
	}

	//GETTERS E SETTERS=================================================================================================
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

	public Date getDataVacancia() {
		return dataVacancia;
	}

	public void setDataVacancia(Date dataVacancia) {
		this.dataVacancia = dataVacancia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
