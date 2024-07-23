package controler;

import java.util.ArrayList;

import entidades.Usuario;
import interfaces.UsuarioInterfaceRepositorio;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named("usuariocon")
public class UsuarioController {

	@Inject
	 @Named("LISTA")
	private UsuarioInterfaceRepositorio usuarioRepositorio;

	private String login;
	private String senha;
	private String retorno;

	public String cadastrar() {

		if (usuarioRepositorio.consultar(login) == null) {
			Usuario user = new Usuario(login, senha, new ArrayList<>());
			usuarioRepositorio.salvar(user);

			retorno = usuarioRepositorio.consultar(user.getNome()).getNome();
			retorno += usuarioRepositorio.consultar(user.getNome()).getSenha();

			return "irindex";
		} else
			retorno = "algo de errado";

		this.login = null;
		this.senha = null;
		return null;
	}

	public boolean excluir() {

		Usuario user = usuarioRepositorio.consultar(login);

		if (user != null) {
			if (user.getSenha().equals(senha)) {
				usuarioRepositorio.remover(login);
				return true;
			}
		}

		return false;
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

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public UsuarioInterfaceRepositorio getUsuarioRepositorio() {
		return usuarioRepositorio;
	}

	public void setUsuarioRepositorio(UsuarioInterfaceRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
}
