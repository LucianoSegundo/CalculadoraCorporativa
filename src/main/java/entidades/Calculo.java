package entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Calculo implements Serializable  {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario usuario;
	@Column(nullable = false)
	
	private Double numeroA;
	@Column(nullable = false)
	private Double numerob;
	
	@Column(nullable = false)
	private String sinal;
	
	@Column(nullable = false)
	private Double resultado;
	
	
	
	public Calculo() {};
	public Calculo(Double numeroA, Double numerob, String sinal, Double resultado, Usuario user) {
		super();
		this.numeroA = numeroA;
		this.numerob = numerob;
		this.sinal = sinal;
		this.resultado = resultado;
		this.usuario =user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getNumeroA() {
		return numeroA;
	}
	public void setNumeroA(Double numeroA) {
		this.numeroA = numeroA;
	}
	public Double getNumerob() {
		return numerob;
	}
	public void setNumerob(Double numerob) {
		this.numerob = numerob;
	}
	public String getSinal() {
		return sinal;
	}
	public void setSinal(String sinal) {
		this.sinal = sinal;
	}
	public Double getResultado() {
		return resultado;
	}
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}
	@Override
	public String toString() {
		return numeroA.toString()+" " + sinal + " "+ numerob.toString() +" = "+ resultado.toString();
	}
	
	
	
	
}
