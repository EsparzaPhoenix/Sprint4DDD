package br.com.fiap.protecaopesada.model;

public class Apolice {

	private int id;
	private int numeroSeguro;
	private String nomeSegurado;
	private String placa;
	private String modelo;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroSeguro() {
		return numeroSeguro;
	}
	public void setNumeroSeguro(int numeroSeguro) {
		this.numeroSeguro = numeroSeguro;
	}
	public String getNomeSegurado() {
		return nomeSegurado;
	}
	public void setNomeSegurado(String nomeSegurado) {
		this.nomeSegurado = nomeSegurado;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}