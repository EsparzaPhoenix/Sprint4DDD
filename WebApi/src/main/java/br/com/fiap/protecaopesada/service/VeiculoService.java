package br.com.fiap.protecaopesada.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.protecaopesada.dao.VeiculoDao;
import br.com.fiap.protecaopesada.exception.BadInfoException;
import br.com.fiap.protecaopesada.exception.IdNotFoundException;
import br.com.fiap.protecaopesada.factory.ConnectionFactory;
import br.com.fiap.protecaopesada.model.Veiculo;

public class VeiculoService {

    private VeiculoDao veiculoDao;

    public VeiculoService() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionFactory.getConnection();
        veiculoDao = new VeiculoDao(conn);
    }

    public void cadastrar(Veiculo veiculo) throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException {
        validar(veiculo);
        veiculoDao.cadastrarVeiculo(veiculo);
    }

    private void validar(Veiculo veiculo) throws BadInfoException {
        // Nome obrigatório e não pode ter mais do que 50 caracteres
        if (veiculo.getModelo() == null || veiculo.getModelo().length() > 50) {
            throw new BadInfoException("Nome inválido, não pode ser nulo e no máximo 50 caracteres");
        }
        if (veiculo.getAno() <= 0) {
            throw new BadInfoException("Ano deve ser maior que 0");
        }
        
        if (veiculo.getValor() <= 0) {
        	throw new BadInfoException("Valor deve ser maior que 0");
        }
        
        if (veiculo.getRodas() <= 0) {
        	throw new BadInfoException("Numero de rodas deve ser maior que 0");
        }
    }

    public void atualizar(Veiculo veiculo) throws ClassNotFoundException, SQLException, IdNotFoundException, BadInfoException {
        validar(veiculo);
        veiculoDao.atualizarVeiculo(veiculo);
    }

    public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
        veiculoDao.removerVeiculo(id);
    }

    public List<Veiculo> listar() throws ClassNotFoundException, SQLException {
        return veiculoDao.listarVeiculos();
    }

    public Veiculo pesquisarPorModelo(String modelo) throws SQLException, IdNotFoundException {
        return veiculoDao.pesquisarVeiculo(modelo);
    }

    public Veiculo pesquisar(String pesquisa) throws SQLException, IdNotFoundException{
        return veiculoDao.pesquisarVeiculo(pesquisa);
    }
}

