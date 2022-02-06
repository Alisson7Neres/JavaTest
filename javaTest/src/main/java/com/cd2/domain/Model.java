package com.cd2.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Model implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private double  peso;
	
	private String cepOrigem;
	private String cepDestino;
	
	private String cep;
	private String dddOrigem;
	private String dddDestino;
	private String ufDestino;
	private String ufOrigem;
	
	private String ddd;
	private String uf;
	
	private String nomeDestinatario;
	
	private double vlTotalFrete;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPrevistaEntrega;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConsulta;
	
	public Model() {
		super();
	}
	public Model(double peso, String cepOrigem, String cepDestino, String nomeDestinatario, String cep,
			 String dddOrigem, String dddDestino, String ufDestino, String ufOrigem, String ddd, String uf,
			double vlTotalFrete, Date dataPrevistaEntrega, Date dataConsulta, Integer id) {
		super();
		this.id = id;
		this.peso = peso;
		this.cepOrigem = cepOrigem;
		this.cepDestino = cepDestino;
		this.cep = cep;
		this.ddd = ddd;
		this.dddOrigem = dddOrigem;
		this.dddDestino = dddDestino;
		this.ufDestino = ufDestino;
		this.ufOrigem = ufOrigem;
		this.uf = uf;
		this.nomeDestinatario = nomeDestinatario;
		this.vlTotalFrete = vlTotalFrete;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		this.dataConsulta = dataConsulta;
		
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getCepOrigem() {
		return cepOrigem;
	}
	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	public String getCepDestino() {
		return cepDestino;
	}
	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getDddOrigem() {
		return dddOrigem;
	}
	public void setDddOrigem(String dddOrigem) {
		this.dddOrigem = dddOrigem;
	}
	public String getDddDestino() {
		return dddDestino;
	}
	public void setDddDestino(String dddDestino) {
		this.dddDestino = dddDestino;
	}
	public String getUfDestino() {
		return ufDestino;
	}
	public void setUfDestino(String ufDestino) {
		this.ufDestino = ufDestino;
	}
	public String getUfOrigem() {
		return ufOrigem;
	}
	public void setUfOrigem(String ufOrigem) {
		this.ufOrigem = ufOrigem;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public double getVlTotalFrete() {
		return vlTotalFrete;
	}
	public void setVlTotalFrete(double vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}
	public Date getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}
	public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}
	public Date getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "Model [peso=" + peso + ", cepOrigem=" + cepOrigem + ", cepDestino=" + cepDestino + ", dddOrigem="
				+ dddOrigem + ", dddDestino=" + dddDestino + ", ufDestino=" + ufDestino + ", ufOrigem=" + ufOrigem
				+ ", nomeDestinatario=" + nomeDestinatario + ", vlTotalFrete=" + vlTotalFrete + ", dataPrevistaEntrega="
				+ dataPrevistaEntrega + ", dataConsulta=" + dataConsulta + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
