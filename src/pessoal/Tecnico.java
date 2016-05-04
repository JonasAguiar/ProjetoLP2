package pessoal;

import java.time.LocalDate;

public class Tecnico extends Funcionario {

	public Tecnico(String nome, LocalDate dataDeNascimento) {
		super(nome, dataDeNascimento);

	}

	@Override
	public String toString() {
		return "Tecnico Administrativo";
	}

	
}
