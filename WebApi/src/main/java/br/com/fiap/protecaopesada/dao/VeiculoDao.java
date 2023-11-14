package br.com.fiap.protecaopesada.dao;

import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao {

    private Connection conn;

    public VeiculoDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public List<Veiculo> listarVeiculos() throws SQLException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM tabela_veiculos");
        ResultSet result = stm.executeQuery();
        List<Veiculo> lista = new ArrayList<>();
        while (result.next()) {
            Veiculo veiculo = parseVeiculo(result);
            lista.add(veiculo);
        }
        return lista;
    }

    private Veiculo parseVeiculo(ResultSet result) throws SQLException {
        String modelo = result.getString("modelo");
        int ano = result.getInt("ano");
        double valor = result.getDouble("valor");
        int rodas = result.getInt("rodas");
        int codigo = result.getInt("codigo");
        String nome = result.getString("nome"); // Adicionando a variável de nome
        return new Veiculo(0 , modelo, ano, valor, rodas, codigo, nome);
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement(
                "INSERT INTO tabela_veiculos (modelo, ano, valor, rodas, codigo, nome) VALUES (?, ?, ?, ?, ?, ?)");
        stm.setString(1, veiculo.getModelo());
        stm.setInt(2, veiculo.getAno());
        stm.setDouble(3, veiculo.getValor());
        stm.setInt(4, veiculo.getRodas());
        stm.setInt(5, veiculo.getCodigo());
        stm.setString(6, veiculo.getNome()); // Adicionando o nome
        stm.executeUpdate();
    }

    public Veiculo pesquisarVeiculo(String pesquisa) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM tabela_veiculos WHERE id_veiculo = ?");
        stm.setString(1, pesquisa);
        ResultSet result = stm.executeQuery();
        if (!result.next()) {
            throw new SQLException("Veiculo não encontrado");
        }
        return parseVeiculo(result);
    }

    public void removerVeiculo(int id) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement("DELETE FROM tabela_veiculos WHERE id_veiculo = ?");
        stm.setInt(1, id);
        int linha = stm.executeUpdate();
        if (linha == 0) {
            throw new SQLException("Veiculo não encontrado para remoção");
        }
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException, IdNotFoundException {
        PreparedStatement stm = conn.prepareStatement(
                "UPDATE tabela_veiculos SET modelo = ?, ano = ?, valor = ?, rodas = ? WHERE codigo = ?");
        stm.setString(1, veiculo.getModelo());
        stm.setInt(2, veiculo.getAno());
        stm.setDouble(3, veiculo.getValor());
        stm.setInt(4, veiculo.getRodas());
        stm.setInt(5, veiculo.getCodigo());
        
        int linhasAfetadas = stm.executeUpdate();

        if (linhasAfetadas == 0) {
            throw new IdNotFoundException("Veículo não encontrado para atualização");
        }
    }

}
