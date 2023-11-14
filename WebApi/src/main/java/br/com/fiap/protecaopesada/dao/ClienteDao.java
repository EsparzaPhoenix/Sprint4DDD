package br.com.fiap.protecaopesada.dao;

import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private Connection conn;

    public ClienteDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public List<Cliente> listarClientes() throws SQLException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM tabela_clientes");
        ResultSet result = stm.executeQuery();
        List<Cliente> lista = new ArrayList<>();
        while (result.next()) {
            Cliente cliente = parseCliente(result);
            lista.add(cliente);
        }
        return lista;
    }

    private Cliente parseCliente(ResultSet result) throws SQLException {
        int codigo = result.getInt("codigo");
        String nome = result.getString("nome");
        int idade = result.getInt("idade");
        String endereco = result.getString("endereco");
        return new Cliente(codigo, nome, idade, endereco);
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO tabela_clientes (codigo, nome, idade, endereco) VALUES (?, ?, ?, ?)");
        stm.setInt(1, cliente.getCodigo());
        stm.setString(2, cliente.getNome());
        stm.setInt(3, cliente.getIdade());
        stm.setString(4, cliente.getEndereco());
        stm.executeUpdate();
    }

    public Cliente pesquisarCliente(int codigo) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM tabela_clientes WHERE codigo = ?");
        stm.setInt(1, codigo);
        ResultSet result = stm.executeQuery();
        if (!result.next()) {
            throw new IdNotFoundException("Cliente não encontrado");
        }
        return parseCliente(result);
    }

    public void removerCliente(int codigo) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("DELETE FROM tabela_clientes WHERE codigo = ?");
        stm.setInt(1, codigo);
        int linha = stm.executeUpdate();
        if (linha == 0) {
            throw new IdNotFoundException("Cliente não encontrado para remoção");
        }
    }
}

