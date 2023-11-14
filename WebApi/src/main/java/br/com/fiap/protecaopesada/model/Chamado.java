package br.com.fiap.protecaopesada.model;

public class Chamado {

	private int id;
	private String descricao;
	private int dataCadastro;
	private Cliente cliente;
	
	public Chamado() {}
	
	public Chamado(int id, String descricao, int dataCadastro, Cliente cliente) {
	    super();
	    this.id = id;
	    this.descricao = descricao;
	    this.dataCadastro = dataCadastro;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(int dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
