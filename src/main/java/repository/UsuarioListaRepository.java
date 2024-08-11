package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entidades.Calculo;
import entidades.Usuario;
import interfaces.UsuarioInterfaceRepositorio;
import jakarta.annotation.PreDestroy;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;

@Stateless
@Named("banco")
//@Named("LISTA")
public class UsuarioListaRepository implements UsuarioInterfaceRepositorio, Serializable {

	private static final long serialVersionUID = 1L;
	private static List<Usuario> BancoDeDados;

	public UsuarioListaRepository() {
		
		if(BancoDeDados == null)  this.BancoDeDados = recuperarLista();
		
	}

	@PreDestroy
	public void destrutor() {
		File bancoBecap = new File("./bancolocal/bancoBecap.txt");

		try (ObjectOutputStream gravador = new ObjectOutputStream(new FileOutputStream(bancoBecap))) {

			bancoBecap.createNewFile();
			gravador.writeObject(BancoDeDados);
			System.out.println("Objetos Usuário foram escritos no arquivo ");

		} catch (IOException e) {
			System.err.println( "algo deu errado na persistencia do arquivo"+e.getMessage());
		}
	}

	@Override
	public Usuario salvar(Usuario usuario) {

		if (consultar(usuario.getNome()) == null) {

			BancoDeDados.add(usuario);
			return usuario;
		}
		return null;
	}

	@Override
	public Usuario consultar(String login) {
		// TODO Auto-generated method stub

		for (Usuario user : BancoDeDados) {
			if (user.getNome().equals(login)) {
				return user;
			}

		}

		return null;
	}

	@Override
	public boolean remover(String login) {

		return BancoDeDados.removeIf(i -> i.getNome().equals(login));

	}
	
	@Override
	public Usuario atualizar(Usuario usuario) {
		if (consultar(usuario.getNome()) == null) {

			BancoDeDados.add(usuario);
			return usuario;
		}
		return null;
	}
	
	private List<Usuario> recuperarLista(){
		
		File bancoBecap = new File("\"./bancolocal/bancoBecap.txt\"");
		
		List<Usuario> lista = null;

		if(bancoBecap.exists()) {
			
        try (ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(bancoBecap))) {
            lista = (List<Usuario>) leitor.readObject();
            
            
        } catch (IOException | ClassNotFoundException e) {
        	lista = null;
            System.err.println("Erro ao ler lista do arquivo: " + e.getMessage());
        }
		}
		
	if(lista == null) {
		lista = new ArrayList<Usuario>();
		lista.add(new Usuario("adm", "2", new ArrayList<Calculo>()));
		return lista; 
	}
	else return lista;
	}
	

	public List<Usuario> getBancoDeDados() {
		return BancoDeDados;
	}

	public void setBancoDeDados(List<Usuario> bancoDeDados) {
		BancoDeDados = bancoDeDados;
	}

	

}
