package br.edu.pucgo.estoque.modelo;
import br.edu.pucgo.estoque.modelo.ErroMsg;

public class Login extends ErroMsg {
	
	private int cont_id;
	private String login;
	private String senha;
	
	public int getCont_id() {
		return cont_id;
	}
	public void setCont_id(int cont_id) {
		this.cont_id = cont_id;
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
	
}
