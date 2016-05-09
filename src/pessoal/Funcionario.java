package pessoal;

import java.time.LocalDate;

public class Funcionario {
	
	private String nome;
	private String matricula;
	private String senha;
	private Cargo cargo;
	private String dataDeNascimento;
	
	public Funcionario(String nome, String dataDeNascimento){
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cargo = null;
		}
	
	public Funcionario(){
		this.cargo = new Diretor();
		this.senha = "c041ebf8";
	}
	
	public void atribuiCargoMedico(){
		this.cargo = new Medico();
	}
	
	public void atribuiCargoTecnico(){
		this.cargo = new Tecnico();
	}
	
	public void atribuiCargoDiretor(){
		this.cargo = new Diretor();
	}
	
	public Cargo getCargo(){
		return this.cargo;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}



	
	
	
	
}
