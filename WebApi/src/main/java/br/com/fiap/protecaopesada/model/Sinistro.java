package br.com.fiap.protecaopesada.model;

public class Sinistro {

	private int id;
	private int dataSinistro;
	private int numeroApolice;
	private int numeroSinistro;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDataSinistro() {
		return dataSinistro;
	}
	public void setDataSinistro(int dataSinistro) {
		this.dataSinistro = dataSinistro;
	}
	public int getNumeroApolice() {
		return numeroApolice;
	}
	public void setNumeroApolice(int numeroApolice) {
		this.numeroApolice = numeroApolice;
	}
	public int getNumeroSinistro() {
		return numeroSinistro;
	}
	public void setNumeroSinistro(int numeroSinistro) {
		this.numeroSinistro = numeroSinistro;
	}
	
}