package br.com.efpay.efpay.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cep")
public class CepEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cep;
	@Column(nullable = false)
    private String logradouro;
    private String complemento;
    @Column(nullable = false)
    private String bairro;
    private String localidade;
    @Column(nullable = false)
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;
    
    public CepEntity() {
    	
    }

	public String getCep() {
		String cepFormatado;
		
		if (cep.length() == 8) {
			cepFormatado = cep.substring(0, cep.length()-3);
			cepFormatado += "-" + cep.substring(cep.length()-3, cep.length());
			
			return cepFormatado;
		}

		return cep;
		
	}

	public void setCep(String cep) {
		if (cep != null && cep.length() > 0) {
			this.cep = cep.replaceAll("-", "");
		} else {
			this.cep = cep;
		}
		
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}
    
    
}
