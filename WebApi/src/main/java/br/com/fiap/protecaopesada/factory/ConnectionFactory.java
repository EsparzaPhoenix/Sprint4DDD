package br.com.fiap.protecaopesada.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	//static -> método pertence a classe e não ao objeto
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
			// Abrir a conexão com o banco
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM550200",
					"310105");
			return conn;
		}
	
}
