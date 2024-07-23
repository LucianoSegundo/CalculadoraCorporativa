package servicos;

import java.io.Serializable;

import entidades.Calculo;
import jakarta.ejb.Stateless;

@Stateless
public class Calculadora implements Serializable{
	
	public Calculo somar(double numeroA, double numeroB) {
		
		double resultado = numeroA + numeroB;
		
		return new Calculo(numeroA, numeroB, "+", resultado);
	}
	
	public Calculo subtrair(double numeroA, double numeroB) {
		
		double resultado = numeroA - numeroB;
		
		return new Calculo(numeroA, numeroB, "-", resultado);
	}
	
	public Calculo multiplicar(double numeroA, double numeroB) {
		
		double resultado = numeroA * numeroB;
		
		return new Calculo(numeroA, numeroB, "x", resultado);
	}
	
	public Calculo dividir(double numeroA, double numeroB) {
		
		double resultado = numeroA / numeroB;
		
		return new Calculo(numeroA, numeroB, "/", resultado);
	}
	
	public Calculo potenciar(double numeroA, double numeroB) {
		
		double resultado = numeroA;
		
		for(int i =1;i<numeroB ;i++) resultado *= numeroA;
		
		return new Calculo(numeroA, numeroB, "^", resultado);
	}

}
