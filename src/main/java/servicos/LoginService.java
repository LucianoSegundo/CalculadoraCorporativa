package servicos;

import java.util.ArrayList;
import java.util.List;

import entidades.Calculo;
import entidades.Usuario;
import interfaces.UsuarioInterfaceRepositorio;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Stateful
public class LoginService  {
	private String login;
	private String senha;
	private String usuario;
	
	@Inject
	 @Named("LISTA")
	private UsuarioInterfaceRepositorio usuarioRepositorio;
	

	public String logar(String login, String senha) {
		this.login = login;
		this.senha = senha;
		
		Usuario user = usuarioRepositorio.consultar(this.login);

		if (user != null) {

			if (user.getSenha().equals(this.senha)) {
				this.usuario = this.login;
				
				return "irhome";
			}
			return  "senha incorreta";
		}

		return "ircadastro";
	};

	public void logout() {
		this.login = null;
		this.senha = null;
		this.usuario = null;

	};

	public List<String> histrorico(){
		
		List<Calculo> lista = usuarioRepositorio.consultar(login).getHistorico();
		List<String> resultado;
		
		resultado = new ArrayList<String>();
		
		 if(lista == null) {
			 resultado.add("nenhum calculo foi realizado ainda");
			 return resultado;
		 } 
		 
		 for(Calculo c : lista) {
			 resultado.add(c.toString());
		 }
		 return resultado;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
