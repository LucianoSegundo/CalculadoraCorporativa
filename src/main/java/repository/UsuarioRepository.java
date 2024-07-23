package repository;

import java.io.Serializable;

import entidades.Usuario;
import interfaces.UsuarioInterfaceRepositorio;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
//@Named("banco")
//public class UsuarioRepository implements UsuarioInterfaceRepositorio, Serializable{
	public class UsuarioRepository {

	@PersistenceContext
	private EntityManager gerente;

	public UsuarioRepository() {
	}

	public Usuario salvar(Usuario usuario) {

		if (!gerente.contains(usuario)) {

			if (gerente.find(Usuario.class, usuario.getNome()) == null)
				this.gerente.persist(usuario);

			else
				return null;
		} else
			usuario = gerente.merge(usuario);

		return usuario;
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
