package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionBanco;
import model.ModeloLogin;

public class DAOLoginRepository {

	private Connection connection;
		
	public DAOLoginRepository() {
		connection = ConnectionBanco.getConnection();
}		
		
	public boolean validarAutenticacao(ModeloLogin modelo) throws Exception {
		
		String sql = "select * from modelo_login where login = ? and senha = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelo.getLogin());
		statement.setString(2, modelo.getSenha());
		
		ResultSet resultSet = statement.executeQuery();
		
			if (resultSet.next()) {
				return true;
			} else {
				return false;
				
			}
	}
	
}
