package entidades;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Calculo> historico;

	public Usuario() {}
	public Usuario(String nome, String senha, List<Calculo> historico) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.historico = historico;
	}
	
	public void addCalculo(Calculo calculo) {
		this.historico.add(calculo);
	}
	public void removerCalculo(Long calculoId) {
		this.historico.removeIf( i -> i.getId() == calculoId);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Calculo> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Calculo> historico) {
		this.historico = historico;
	}
	
}
