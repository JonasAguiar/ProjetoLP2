package pessoal;

import java.time.LocalDate;

public class Diretor extends Funcionario{
	
	public Diretor(String nome, LocalDate dataDeNascimento){
		super(nome, dataDeNascimento);
	}

	@Override
	public String toString() {
		return "Diretor Geral";
	}

	
	
}
