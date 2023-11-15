package br.com.fiap.protecaopesada.service;

import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.exception.BadInfoException;
import br.com.fiap.protecaopesada.model.Cliente;
import br.com.fiap.protecaopesada.model.Veiculo;
import br.com.fiap.protecaopesada.dao.ClienteDao;
import br.com.fiap.protecaopesada.dao.VeiculoDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private Connection conn;
    private ClienteDao clienteDao;
    private VeiculoDao veiculoDao;

    public ClienteService(Connection conn) {
        this.conn = conn;
        this.clienteDao = new ClienteDao(conn);
        this.veiculoDao = new VeiculoDao(conn);
    }

    public ClienteService() {
	}
    
    //Métodos relacionados a Clientes  

	public List<Cliente> listarClientes() throws SQLException {
        return clienteDao.listarClientes();
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException, IdNotFoundException {
        clienteDao.cadastrarCliente(cliente);
    }
    
    private void validarCliente(Cliente cliente) throws BadInfoException {
    	if (cliente.getNome() == null || cliente.getNome().length() > 80) {
			throw new BadInfoException("Nome invalido, nao pode ser nulo");
    	}
    }	

    public Cliente pesquisarCliente(int codigo) throws SQLException, IdNotFoundException {
        return clienteDao.pesquisarCliente(codigo);
    }

    public void removerCliente(int codigo) throws SQLException, IdNotFoundException {
        clienteDao.removerCliente(codigo);
    }

    //Métodos relacionados a Veículos

    public List<Veiculo> listarVeiculos() throws SQLException {
        return veiculoDao.listarVeiculos();
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws SQLException, IdNotFoundException {
        veiculoDao.cadastrarVeiculo(veiculo);
    }

    public Veiculo pesquisarVeiculo(String nome) throws SQLException, IdNotFoundException {
        return veiculoDao.pesquisarVeiculo(nome);
    }

    public void removerVeiculo(int codigo) throws SQLException, IdNotFoundException {
        veiculoDao.removerVeiculo(codigo);
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException, IdNotFoundException {
        veiculoDao.atualizarVeiculo(veiculo);
    }
}
