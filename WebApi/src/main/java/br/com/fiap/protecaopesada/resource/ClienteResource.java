package br.com.fiap.protecaopesada.resource;

import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteResource implements AutoCloseable {

    private Connection conn;

    public ClienteResource(Connection conn) {
        this.conn = conn;
    }

    public List<Cliente> listarClientes() throws SQLException {
        try (PreparedStatement stm = conn.prepareStatement("SELECT * FROM tabela_clientes");
             ResultSet result = stm.executeQuery()) {
            List<Cliente> lista = new ArrayList<>();
            while (result.next()) {
                Cliente cliente = parseCliente(result);
                lista.add(cliente);
            }
            return lista;
        }
    }

    public void cadastrarCliente(Cliente cliente) throws SQLException, IdNotFoundException {
        try (PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO tabela_clientes (codigo, nome, idade, endereco) VALUES (?, ?, ?, ?)")) {
            stm.setInt(1, cliente.getCodigo());
            stm.setString(2, cliente.getNome());
            stm.setInt(3, cliente.getIdade());
            stm.setString(4, cliente.getEndereco());
            stm.executeUpdate();
        }
    }

    public Cliente pesquisarCliente(int codigo) throws SQLException, IdNotFoundException {
        try (PreparedStatement stm = conn.prepareStatement("SELECT * FROM tabela_clientes WHERE codigo = ?")) {
            stm.setInt(1, codigo);
            try (ResultSet result = stm.executeQuery()) {
                if (!result.next()) {
                    throw new IdNotFoundException("Cliente não encontrado");
                }
                return parseCliente(result);
            }
        }
    }

    public void removerCliente(int codigo) throws SQLException, IdNotFoundException {
        try (PreparedStatement stm = conn.prepareStatement("DELETE FROM tabela_clientes WHERE codigo = ?")) {
            stm.setInt(1, codigo);
            int linha = stm.executeUpdate();
            if (linha == 0) {
                throw new IdNotFoundException("Cliente não encontrado para remoção");
            }
        }
    }

    private Cliente parseCliente(ResultSet result) throws SQLException {
        int codigo = result.getInt("codigo");
        String nome = result.getString("nome");
        int idade = result.getInt("idade");
        String endereco = result.getString("endereco");
        return new Cliente(codigo, nome, idade, endereco);
    }

    @Override
    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
