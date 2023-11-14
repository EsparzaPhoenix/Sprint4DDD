package br.com.fiap.protecaopesada.model;

public class Agenda {

	private int id;
	private String tipoServico;
	private String localEntrega;
	private String dataEntrega;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getLocalEntrega() {
		return localEntrega;
	}
	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}
	public String getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public void marcarData(String horario) {
		if(this.dataEntrega == null) {
			this.setDataEntrega(horario);
			return;
		}
		
	}
}