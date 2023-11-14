package br.com.fiap.protecaopesada.main;

import java.sql.SQLException;

import br.com.fiap.protecaopesada.dao.ClienteDao;
import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.model.Cliente;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IdNotFoundException {
		Cliente cl = new Cliente();
		ClienteDao Dao = new ClienteDao();
	}
}
