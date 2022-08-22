package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionBanco;
import model.ModeloLogin;

public class DAOUsuarioRepository {
		
	private Connection connection;
	
		public DAOUsuarioRepository() {
			connection = ConnectionBanco.getConnection();
		}

	// CADASTRANDO USUÁRIO NO BANCO DE DADOS 
	public ModeloLogin gravarUsuario(ModeloLogin objeto, Long userlogado) throws Exception {
		
		if (objeto.isNovo()) {
			String sql = "insert into modelo_login (login, senha, nome, email, usuario_id, perfil, sexo, datanascimento) values (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setLong(5, userlogado);
			preparedSql.setString(6, objeto.getPerfil());
			preparedSql.setString(7, objeto.getSexo());
			preparedSql.setDate(8, objeto.getDataNascimento());
			
			preparedSql.execute();
			connection.commit();
			
			if (objeto.getFotoUser() != null && !objeto.getFotoUser().isEmpty()) {
				sql = "update modelo_login set fotouser =?, extensaofoto=? where login =?";
				preparedSql = connection.prepareStatement(sql);
				
				preparedSql.setString(1, objeto.getFotoUser());
				preparedSql.setString(2, objeto.getExtensaoFoto());
				preparedSql.setString(3, objeto.getLogin());
				
				preparedSql.execute();
				connection.commit();
				
			}
			
		} else {
			// ATUALIZANDO ALGUMA INFORMAÇÃO NO CADASTRO
			String sql = "UPDATE modelo_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, datanascimento=? WHERE id = "+objeto.getId()+"";
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString(1, objeto.getLogin());
			prepareSql.setString(2, objeto.getSenha());
			prepareSql.setString(3, objeto.getNome());
			prepareSql.setString(4, objeto.getEmail());
			prepareSql.setString(5, objeto.getPerfil());
			prepareSql.setString(6, objeto.getSexo());
			prepareSql.setDate(7, objeto.getDataNascimento());
			
			prepareSql.executeUpdate();
			connection.commit();
			
			if (objeto.getFotoUser() != null && !objeto.getFotoUser().isEmpty()) {
				sql = "update modelo_login set fotouser =?, extensaofoto=? where id =?";
				prepareSql = connection.prepareStatement(sql);
				
				prepareSql.setString(1, objeto.getFotoUser());
				prepareSql.setString(2, objeto.getExtensaoFoto());
				prepareSql.setLong(3, objeto.getId());
				
				prepareSql.execute();
				connection.commit();
			}
		}
		
		return this.consultaUsuario(objeto.getLogin(), userlogado);
	}
	
	
	// CONSULTAR USUÁRIO PARA EXIBIR ABAIXO DO FORMULÁRIO NO MODO PAGINAÇÃO DE 5 EM 5 
	public List<ModeloLogin> consultaUsuarioListPaginado(Long userlogado, Integer offset) throws SQLException {
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
				
		String sql = "select * from modelo_login where useradmin is false and usuario_id =" + userlogado + " order by id offset "+ offset +" limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
				
		ResultSet resultado = statement.executeQuery();
				
		while (resultado.next()) {
			ModeloLogin modeloLogin = new ModeloLogin();
					
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
				
			retorno.add(modeloLogin);
		}
			return retorno;
			
		}
	
	public int totalPaginas(Long userlogado) throws Exception {
		String sql = "select count(1) as total from modelo_login where usuario_id =" + userlogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		resultado.next();
		
		Double cadastros = resultado.getDouble("total");
		Double porPagina = 5.0;
		Double pagina = cadastros / porPagina;
		Double resto = pagina % 2;
		
		if (resto > 0) {
			pagina ++;
		}
		return pagina.intValue();
		
	}
	
	public List<ModeloLogin> consultaUsuarioListRel(Long userLogado, String dataInicial, String dataFinal) throws Exception {
			
			List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
			
			String sql = "select * from modelo_login where useradmin is false and usuario_id = " + userLogado + " and datanascimento >= ? and datanascimento <= ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
			statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
			
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				
				ModeloLogin modeloLogin = new ModeloLogin();
				
				modeloLogin.setEmail(resultado.getString("email"));
				modeloLogin.setId(resultado.getLong("id"));
				modeloLogin.setLogin(resultado.getString("login"));
				modeloLogin.setNome(resultado.getString("nome"));
				modeloLogin.setPerfil(resultado.getString("perfil"));
				modeloLogin.setSexo(resultado.getString("sexo"));
				modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
				
				
				retorno.add(modeloLogin);
			}
			
			
			return retorno;
		}
	
	
	public List<ModeloLogin> consultaUsuarioListRel(Long userLogado) throws Exception {
			
			List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
			
			String sql = "select * from modelo_login where useradmin is false and usuario_id = " + userLogado;
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				
				ModeloLogin modeloLogin = new ModeloLogin();
				
				modeloLogin.setEmail(resultado.getString("email"));
				modeloLogin.setId(resultado.getLong("id"));
				modeloLogin.setLogin(resultado.getString("login"));
				modeloLogin.setNome(resultado.getString("nome"));
				modeloLogin.setPerfil(resultado.getString("perfil"));
				modeloLogin.setSexo(resultado.getString("sexo"));
				modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
				
				retorno.add(modeloLogin);
			}
			
			
			return retorno;
		}
	
	
	// CONSULTAR USUÁRIO PARA EXIBIR ABAIXO DO FORMULÁRIO
	public List<ModeloLogin> consultaUsuarioList(Long userlogado) throws SQLException {
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
			
		String sql = "select * from modelo_login where useradmin is false and usuario_id =" + userlogado + " limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
			
		ResultSet resultado = statement.executeQuery();
			
		while (resultado.next()) {
			ModeloLogin modeloLogin = new ModeloLogin();
				
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
				
			retorno.add(modeloLogin);
		}
			return retorno;
			
		}
	
	
	// CONSULTAR USUÁRIO PELO MODAL
	public List<ModeloLogin> consultaUsuarioList(String nome, Long userlogado) throws SQLException {
		List<ModeloLogin> retorno = new ArrayList<ModeloLogin>();
		
		String sql = "select * from modelo_login  where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userlogado);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			ModeloLogin modeloLogin = new ModeloLogin();
			
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modeloLogin);
		}
		
		return retorno;
	}
	
	
	public ModeloLogin consultaUsuarioLogado(String login) throws Exception {
		ModeloLogin modeloLogin = new ModeloLogin();			
		String sql = "select * from modelo_login where upper(login) = upper('"+login+"')";
			
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
				
		while (resultado.next()) {
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));		
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotoUser(resultado.getString("fotouser"));
			modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
		} 
			return modeloLogin;
		}
	
	// CONSULTANDO CADASTRO PELO BANCO
	public ModeloLogin consultaUsuario(String login) throws Exception {
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from modelo_login where upper(login) = upper('"+login+"') and useradmin is false ";
			
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
			
		while (resultado.next()) {
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));		
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotoUser(resultado.getString("fotouser"));
			modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			
		} 
			
			return modeloLogin;
		}
	
	
	// CONSULTANDO CADASTRO PELO BANCO
	public ModeloLogin consultaUsuario(String login, Long userlogado) throws Exception {
		ModeloLogin modeloLogin = new ModeloLogin();
		String sql = "select * from modelo_login where upper(login) = upper('"+login+"') and useradmin is false and usuario_id=" + userlogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			modeloLogin.setId(resultado.getLong("id"));
			modeloLogin.setNome(resultado.getString("nome"));
			modeloLogin.setEmail(resultado.getString("email"));
			modeloLogin.setLogin(resultado.getString("login"));
			modeloLogin.setSenha(resultado.getString("senha"));
			modeloLogin.setPerfil(resultado.getString("perfil"));
			modeloLogin.setSexo(resultado.getString("sexo"));
			modeloLogin.setFotoUser(resultado.getString("fotouser"));
			modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
		} 
		
		return modeloLogin;
	}
	
	// CONSULTANDO CADASTRO PARA MODAL PELO ID 
		public ModeloLogin consultaUsuarioID(String id, Long userlogado) throws Exception {
			ModeloLogin modeloLogin = new ModeloLogin();
			String sql = "select * from modelo_login where id = ? and useradmin is false and usuario_id = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			statement.setLong(2, userlogado);
			ResultSet resultado = statement.executeQuery();
			
			while (resultado.next()) {
				modeloLogin.setId(resultado.getLong("id"));
				modeloLogin.setNome(resultado.getString("nome"));
				modeloLogin.setEmail(resultado.getString("email"));
				modeloLogin.setLogin(resultado.getString("login"));
				modeloLogin.setSenha(resultado.getString("senha"));
				modeloLogin.setPerfil(resultado.getString("perfil"));
				modeloLogin.setSexo(resultado.getString("sexo"));
				modeloLogin.setFotoUser(resultado.getString("fotouser"));
				modeloLogin.setDataNascimento(resultado.getDate("datanascimento"));
			} 
			
			return modeloLogin;
		}
		
	// VALIDANDO LOGIN 
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from modelo_login where upper(login) = upper('"+login+"');";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
	}
	
	// DELETANDO DO BANCO DE DADOS
	public void deletarUsuario(String idUser) throws Exception {
		String sql = "DELETE FROM modelo_login WHERE id = ? and useradmin is false"; 
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(idUser));
		
		prepareSql.executeUpdate();
		connection.commit();
		
	}
}
