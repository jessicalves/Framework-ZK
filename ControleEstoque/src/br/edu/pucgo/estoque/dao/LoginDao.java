package br.edu.pucgo.estoque.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.edu.pucgo.estoque.modelo.Login;
import br.edu.pucgo.estoque.persistencia.Conexao;

public class LoginDao extends Conexao {

	public Login fazerLogin(Login login) throws SQLException, InterruptedException {

		Conexao conexao = new Conexao();
		Connection conn = conexao.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;

		if (conn == null) {
			login.setMsgErro("Erro ao Conectar!!");
			login = null;
		} else {
			try {
				String select = "SELECT * FROM login WHERE nome = ? AND senha = ? ;";
				stmt = conn.prepareStatement(select);
				stmt.setString(1, login.getLogin());
				stmt.setString(2, login.getSenha());
				rs = stmt.executeQuery();

				if (rs.next()) {
					login = new Login();
					login.setLogin(rs.getString("nome"));
					login.setSenha(rs.getString("senha"));

				} else {
					login = null;
				}
			} catch (SQLException e) {
				login = null;
			} 
		}
		return login;

	}

	public Login fazerCadastro(Login login) throws SQLException, InterruptedException {

		Conexao conexao = new Conexao();
		Connection conn = conexao.getConnection();
		PreparedStatement stmt;

		if (conn == null) {
			login.setMsgErro("Erro ao Conectar!!");
			login = null;
		} else {
			try {
				stmt = conn.prepareStatement("INSERT INTO login (nome, senha) VALUES(?,?)");
				stmt.setString(1, login.getLogin());
				stmt.setString(2, login.getSenha());
				stmt.executeQuery();
				
			} catch (SQLException e) {
				login.setMsgErro("Cadastrado com sucesso!!");
			}
		}
		return login;
	}
}
