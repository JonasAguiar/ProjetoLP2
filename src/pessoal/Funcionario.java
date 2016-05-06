package pessoal;

import java.time.LocalDate;

public class Funcionario {
	
	private String nome;
	private String matricula;
	private String senha;
	private String cargo;
	private LocalDate dataDeNascimento;
	
	public Funcionario(String nome, LocalDate dataDeNascimento){
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		}

	
/*	public Funcionario(){
		this.cargo = new Diretor();
		this.senha = "c041ebf8";
	}*/

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


	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	
	
	
	
}
