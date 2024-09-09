package controler;

import java.io.Serializable;
import java.util.List;

import entidades.Calculo;
import entidades.Usuario;
import interfaces.UsuarioInterfaceRepositorio;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import servicos.Calculadora;
import servicos.LoginService;


@Named("calculadora")
@SessionScoped
public class CalculadoraController implements Serializable {

    @Inject
    private Calculadora calculadora;

    @Inject
    @Named("LISTA")
    private UsuarioInterfaceRepositorio usuarioRepositorio;

    @Inject
    private LoginService mecanismoLogin;

    private Double numeroA;
    private Double numeroB;
    private String resultado;

    private String login;
    private String senha;


    public String logar() {
        resultado = mecanismoLogin.logar(login, senha);
        login = null;
        senha= null;
        return resultado;
    }

    public String logout() {
        mecanismoLogin.logout();
        limparCalculadora();
        return "irindex";    }

    public List<String> getHistorico() {
    	
    	return mecanismoLogin.histrorico();
    }
    
    public String excluirConta() {
    	usuarioRepositorio.remover(mecanismoLogin.getUsuario());
    	return logout();
    }
    public void somar() {
        if (validarNumeros()) {
            Usuario user = usuarioRepositorio.consultar(mecanismoLogin.getUsuario());

            Calculo cal = calculadora.somar(numeroA, numeroB, user);
            processarCalculo(cal, user);
        } else {
            reclamacao("é necessário 2 números válidos para calcular");
        }
    }

    public void subtrair() {
        if (validarNumeros()) {
            Usuario user = usuarioRepositorio.consultar(mecanismoLogin.getUsuario());

            Calculo cal = calculadora.subtrair(numeroA, numeroB, user);
            processarCalculo(cal, user);
        } else {
            reclamacao("é necessário 2 números válidos para calcular");
        }
    }

    public void dividir() {
        if (validarNumeros()) {
            Usuario user = usuarioRepositorio.consultar(mecanismoLogin.getUsuario());

            Calculo cal = calculadora.dividir(numeroA, numeroB, user);
            processarCalculo(cal, user);
        } else {
            reclamacao("é necessário 2 números válidos para calcular");
        }
    }

    public void multiplicar() {
        if (validarNumeros()) {
            Usuario user = usuarioRepositorio.consultar(mecanismoLogin.getUsuario());

            Calculo cal = calculadora.multiplicar(numeroA, numeroB, user);
            processarCalculo(cal, user);
        } else {
            reclamacao("é necessário 2 números válidos para calcular");
        }
    }

    public void potenciar() {
        if (validarNumeros()) {
            Usuario user = usuarioRepositorio.consultar(mecanismoLogin.getUsuario());

            Calculo cal = calculadora.potenciar(numeroA, numeroB, user);
            processarCalculo(cal, user);
        } else {
            reclamacao("é necessário 2 números para calcular");
        }
    }

    private void processarCalculo(Calculo cal, Usuario user) {
        if (user != null) {
            user.addCalculo(cal);
            usuarioRepositorio.atualizar(user);
            resultado = cal.getResultado().toString();
        } else {
            resultado = "você precisa estar logado para calcular ";
        }
        limparCalculadora();
    }

    private void reclamacao(String mensagem) {
        resultado = mensagem;
        limparCalculadora();
    }

    private boolean validarNumeros() {
        return numeroA != null && numeroB != null && !numeroA.isNaN() && !numeroB.isNaN();
    }

    private void limparCalculadora() {
        numeroA = null;
        numeroB = null;
    }

	public Calculadora getCalculadora() {
		return calculadora;
	}

	public void setCalculadora(Calculadora calculadora) {
		this.calculadora = calculadora;
	}

	public UsuarioInterfaceRepositorio getUsuarioRepositorio() {
		return usuarioRepositorio;
	}

	public void setUsuarioRepositorio(UsuarioInterfaceRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	public LoginService getMecanismoLogin() {
		return mecanismoLogin;
	}

	public void setMecanismoLogin(LoginService mecanismoLogin) {
		this.mecanismoLogin = mecanismoLogin;
	}

	public Double getNumeroA() {
		return numeroA;
	}

	public void setNumeroA(Double numeroA) {
		this.numeroA = numeroA;
	}

	public Double getNumeroB() {
		return numeroB;
	}

	public void setNumeroB(Double numeroB) {
		this.numeroB = numeroB;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
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

 
}
