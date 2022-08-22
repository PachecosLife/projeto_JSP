package model;

import java.io.Serializable;
import java.sql.Date;

public class ModeloLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String perfil;
	private String sexo;
	private String fotoUser;
	private String extensaoFoto;
	private Date dataNascimento;
	
	
	public boolean isNovo() {	
		if (this.id == null) {
			return true;  // INSERIR UM NOVO
		} else if(this.id != null && this.id > 0){
			return false; // ATUALIZAR DADOS
		}
		
		return id == null; 
	}
	
	
	// MÉTODOS SETTERS & GETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFotoUser() {
		return fotoUser;
	}
	
	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}
	
	public String getExtensaoFoto() {
		return extensaoFoto;
	}
	
	public void setExtensaoFoto(String extensaoFoto) {
		this.extensaoFoto = extensaoFoto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
