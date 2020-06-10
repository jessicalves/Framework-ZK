package br.edu.pucgo.estoque.controle;

import java.io.IOException;
import java.sql.SQLException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import br.edu.pucgo.estoque.dao.LoginDao;
import br.edu.pucgo.estoque.modelo.Login;
import br.edu.pucgo.estoque.util.Utilidade;

public class LoginControle extends SelectorComposer<Window> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	Textbox txtLogin;
	@Wire
	Textbox txtSenha;
	@Wire
	Button btnEntrar;
	@Wire
	Textbox txtSenhaUsuario;
	@Wire
	Textbox txtNomeUsuario;
	@Wire
	Button btnSalvarCadastro;

	@Listen("onClick = #btnEntrar")
	public void onClickbtnEntrar() throws SQLException, InterruptedException, IOException, ClassNotFoundException {
		LoginDao DAO = new LoginDao();
		Login login = new Login();

		login.setLogin(txtLogin.getText());
		login.setSenha(txtSenha.getText());

		login = DAO.fazerLogin(login);

		if (login == null) {
			Login log = new Login();
			log.setMsgErro("Usu�rio n�o possui conta.");
			Utilidade.mensagem(log.getMsgErro());
		} else {
			Executions.sendRedirect("/EstoqueVisao.zul");
		}

	}
	
	@Listen("onClick = #btnSalvarCadastro")
	public void onClickbtnCadastrarUser() throws SQLException, InterruptedException{
		LoginDao DAO = new LoginDao();
		Login login = new Login();
		login.setLogin(txtNomeUsuario.getText());
		login.setSenha(txtSenhaUsuario.getText());
		login = DAO.fazerCadastro(login);
		Utilidade.mensagem(login.getMsgErro());
		Executions.sendRedirect("/index.zul");
		
	}

}
