package pessoal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartamentoADM {
	

	private Map<Integer, Funcionario> funcionarios;

	public DepartamentoADM(){
		this.funcionarios = new HashMap<Integer, Funcionario>();
	}
	
	public Funcionario criaDiretor(String nome, LocalDate dataDeNascimento){
		String senha = "c041ebf8";
		Funcionario diretorGeral = new Diretor(nome, dataDeNascimento);
		String matricula = "1" + String.valueOf(dataDeNascimento.getYear()) 
								+ String.valueOf(funcionarios.size());
		diretorGeral.setMatricula(matricula);
		diretorGeral.setSenha(senha);
		return diretorGeral;
	}
	
	public Funcionario criaMedico(String nome, LocalDate dataDeNascimento){
		Funcionario medico = new Medico(nome, dataDeNascimento);
		String matricula = "2" + dataDeNascimento.getDayOfYear() + ""
	}
	
	
	
	
}