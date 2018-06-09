package br.gov.to.secad.api.ergon.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DadosSecretariosPorPastaResponse {

	private String sNome;
	private String sCPF;
	private Integer sNumFunc;
	private Integer sNumVinc;

	public DadosSecretariosPorPastaResponse() {
	}

	public String getsNome() {
		return sNome;
	}

	public void setsNome(String sNome) {
		this.sNome = sNome;
	}

	public String getsCPF() {
		return sCPF;
	}

	public void setsCPF(String sCPF) {
		this.sCPF = sCPF;
	}

	public Integer getsNumFunc() {
		return sNumFunc;
	}

	public void setsNumFunc(Integer sNumFunc) {
		this.sNumFunc = sNumFunc;
	}

	public Integer getsNumVinc() {
		return sNumVinc;
	}

	public void setsNumVinc(Integer sNumVinc) {
		this.sNumVinc = sNumVinc;
	}
}
