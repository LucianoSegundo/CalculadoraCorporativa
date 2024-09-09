package interfaces;

import entidades.Usuario;


public interface UsuarioInterfaceRepositorio  {

	public Usuario salvar(Usuario usuario);
	
	public Usuario consultar(String login);
	
	public boolean remover(String login);
	
	public Usuario atualizar(Usuario usuario);

	
}
