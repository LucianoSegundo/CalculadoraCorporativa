package repository;

import java.io.Serializable;

import entidades.Usuario;
import interfaces.UsuarioInterfaceRepositorio;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
//@Named("banco")
@Named("LISTA")
public class UsuarioRepository implements UsuarioInterfaceRepositorio, Serializable{
	//public class UsuarioRepository {

	@PersistenceContext(unitName = "Unidade")
	private EntityManager gerente;

	public UsuarioRepository() {
	}

	public Usuario salvar(Usuario usuario) {

		if (!gerente.contains(usuario)) {

				this.gerente.persist(usuario);
				return usuario;


		} else return null;
			

	}
	
	@Override
	public Usuario atualizar(Usuario usuario) {
		
		if (gerente.find(Usuario.class, usuario.getNome()) != null) return gerente.merge(usuario);
		
		return null;
	}


	public Usuario consultar(String login) {
		Usuario usuario = this.gerente.find(Usuario.class, login);

		return usuario;
	}

	public boolean remover(String login) {

		Usuario usuario = consultar(login);

		if (usuario == null)
			return false;

		this.gerente.remove(usuario);

		return true;
	}

	public EntityManager getGerente() {
		return gerente;
	}

	public void setGerente(EntityManager gerente) {
		this.gerente = gerente;
	}

	
}
