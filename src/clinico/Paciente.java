package clinico;

import java.time.LocalDate;
import java.util.UUID;

import logicaCartao.CartaoFidelidade;
import logicaCartao.Master;
import logicaCartao.Padrao;
import logicaCartao.Vip;

public class Paciente {
	
	private String nome;
	private double peso;
	private String dataNascimento;
	private String tipoSanguineo;
	private String sexoBiologico;
	private String genero;
	private UUID id;
	private int totalDeGastos;
	private CartaoFidelidade cartaoFidelidade;
	private int pontosBonus;
	
	
	
	
	public Paciente(String nome, double peso, String data, String tipoSanguineo, String sexo, String genero){
		this.nome = nome;
		this.peso = peso;
		this.dataNascimento = data;
		this.tipoSanguineo = tipoSanguineo;
		this.sexoBiologico = sexo;
		this.genero = genero;
		this.id = UUID.randomUUID();
		this.totalDeGastos = 0;
		this.cartaoFidelidade = new Padrao();
		
	}
	


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = getPeso() - peso;
	}


	public String getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getTipoSanguineo() {
		return tipoSanguineo;
	}


	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}


	public String getSexoBiologico() {
		return sexoBiologico;
	}


	public void setSexoBiologico(String sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}


	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}



	public int getTotalDeGastos() {
		return totalDeGastos;
	}



	public void setTotalDeGastos(int valor) {
		this.totalDeGastos = totalDeGastos + valor;
	}

	public void adicionaPontos(int pontos){
		int pontosCartao = cartaoFidelidade.calculaPontos(pontos);
		this.pontosBonus += pontosCartao;
		verificaTipoCartao();
		
	}
	
	
	public void verificaTipoCartao(){
		if(pontosBonus >= 150 && pontosBonus <= 350){
			cartaoFidelidade = new Master();
		}else if(pontosBonus > 350){
			cartaoFidelidade = new Vip();
		}
	}

	public void adicionaGasto(int valor) {
		int valorCartao = cartaoFidelidade.calculaDesconto(valor);
		setTotalDeGastos(valorCartao);
		
	}
	
	public int calculaDescontoRemedio(int valor){
		return cartaoFidelidade.calculaDesconto(valor);
	}
	
	
	
}