package pessoal;

import java.time.LocalDate;

import SOOS.Controller;

public class Medico extends Funcionario{ 
	


	public Medico(String nome, LocalDate dataDeNascimento) {
		super(nome, dataDeNascimento);
	}

	@Override
	public String toString() {
		return "Medico";
	}
}
