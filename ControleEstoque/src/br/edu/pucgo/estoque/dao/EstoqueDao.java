package br.edu.pucgo.estoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import br.edu.pucgo.estoque.persistencia.Conexao;
import br.edu.pucgo.estoque.modelo.Produto;

public class EstoqueDao extends Conexao {

	public ArrayList<Produto> listAll() throws ClassNotFoundException, InterruptedException, SQLException {
		ResultSet rs = null;
		Conexao conexao = new Conexao();
		Connection conn = conexao.getConnection();

		ArrayList<Produto> al = new ArrayList<Produto>();
		Produto produto = new Produto();
		Statement st = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM produto ORDER BY prod_id ASC");

			while (rs.next()) {
				produto = new Produto();
				produto.setProd_id(rs.getInt("prod_id"));
				produto.setNome(rs.getString("nome"));
				produto.setCodigo(rs.getInt("codigo"));
				al.add(produto);
			}
		} catch (SQLException e) {
			al = null;
		} finally {
		}
		return al;
	}

	public ArrayList<Produto> buscarPorNomeGeral(String str)
			throws ClassNotFoundException, InterruptedException, SQLException {
		ResultSet rs = null;
		Connection conn = this.getConnection();

		ArrayList<Produto> al = new ArrayList<Produto>();
		Produto produto = new Produto();
		Statement st = null;

		String select = "SELECT * FROM produto WHERE nome = '" + str + "'";

		if (str.equals(""))
			select = "SELECT * FROM produto ORDER BY nome ASC";

		try {

			st = conn.createStatement();
			rs = st.executeQuery(select);

			while (rs.next()) {
				produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setQtde_estoque(rs.getInt("qtde_estoque"));
				al.add(produto);
			}
		} catch (SQLException e) {
			al = null;
		} finally {
		}
		return al;
	}

	public Produto buscarPorId(int id) throws ClassNotFoundException, InterruptedException, SQLException {
		ResultSet rs = null;
		Connection conn = this.getConnection();

		Produto produto = new Produto();
		Statement st = null;

		String select = "SELECT * FROM produto WHERE prod_id = " + id;

		try {

			st = conn.createStatement();
			rs = st.executeQuery(select);

			if (rs.next()) {
				produto = new Produto();
				produto.setProd_id(rs.getInt("prod_id"));
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco_compra(rs.getDouble("preco_compra"));
				produto.setPreco_venda(rs.getDouble("preco_venda"));
				produto.setQtde_estoque(rs.getInt("qtde_estoque"));
			}
		} catch (SQLException e) {
			produto = null;
		} finally {
		}
		return produto;
	}

	public Produto buscarProdutoId(int codigo) throws ClassNotFoundException, InterruptedException, SQLException {
		ResultSet rs = null;
		Connection conn = this.getConnection();

		Produto produto = new Produto();
		Statement st = null;

		String select = "SELECT prod_id FROM produto WHERE codigo = " + codigo;

		try {

			st = conn.createStatement();
			rs = st.executeQuery(select);

			if (rs.next()) {
				produto = new Produto();
				produto.setProd_id(rs.getInt("prod_id"));
			}
		} catch (SQLException e) {
			produto = null;
		} finally {
		}
		return produto;
	}

	public Produto incluir(Produto produto) throws SQLException, InterruptedException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.getConnection();
		PreparedStatement stmt;

		if (conn == null) {
			produto.setMsgErro("Erro ao salvar!!");
		} else {
			try {
				stmt = conn.prepareStatement(
						"INSERT INTO produto (codigo, nome, preco_compra, preco_venda, qtde_estoque) VALUES(?,?,?,?,?)");
				stmt.setInt(1, produto.getCodigo());
				stmt.setString(2, produto.getNome());
				stmt.setDouble(3, produto.getPreco_compra());
				stmt.setDouble(4, produto.getPreco_venda());
				stmt.setInt(5, produto.getQtde_estoque());
				stmt.executeQuery();

			} catch (SQLException e) {
				produto.setMsgErro("Cadastro realizado com sucesso!!");
			}
		}
		return produto;
	}

	public Produto alterar(Produto produto) throws ClassNotFoundException, InterruptedException, SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstm;
		String update = "UPDATE produto SET codigo = ?, nome = ?, preco_compra = ?, preco_venda = ?,qtde_estoque=? ";
		update += " WHERE prod_id = ?";
		try {
			pstm = conn.prepareStatement(update);
			pstm.setInt(1, produto.getCodigo());
			pstm.setString(2, produto.getNome());
			pstm.setDouble(3, produto.getPreco_compra());
			pstm.setDouble(4, produto.getPreco_venda());
			pstm.setInt(5, produto.getQtde_estoque());
			pstm.setInt(6, produto.getProd_id());
			pstm.execute();
			produto.setMsgErro("Altera��o realizada com sucesso!!");
		} catch (SQLException e) {
			produto.setMsgErro("Erro ao Alterar.");
		}
		return produto;
	}

	public Produto excluir(Produto produto) throws ClassNotFoundException, InterruptedException, SQLException {
		Connection conn = this.getConnection();
		PreparedStatement pstm;
		String delete = "DELETE FROM produto WHERE prod_id = ?";
		try {
			pstm = conn.prepareStatement(delete);
			pstm.setInt(1, produto.getProd_id());
			pstm.execute();
			produto.setMsgErro("Excluido com sucesso!!");
		} catch (SQLException e) {
			produto.setMsgErro("Erro ao Excluir.");
		}
		return produto;
	}

	public Produto logar(Produto produto) throws ClassNotFoundException, InterruptedException, SQLException {

		// Pensar como fazer o login
		return null;
	}

	public ArrayList<Produto> listAllSql(String sql) throws ClassNotFoundException, InterruptedException, SQLException {
		ResultSet rs = null;
		Connection conn = this.getConnection();

		ArrayList<Produto> al = new ArrayList<Produto>();
		Produto produto = new Produto();
		Statement st = null;
		try {

			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				produto = new Produto();
				produto.setProd_id(rs.getInt("prod_id"));
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				al.add(produto);
			}
		} catch (SQLException e) {
			al = null;
		} finally {
		}
		return al;
	}

}
