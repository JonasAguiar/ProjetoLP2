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
	
	public Funcionario criaDiretor(String chave, String nome, LocalDate dataDeNascimento){
		Funcionario diretorGeral = new Diretor(nome, dataDeNascimento);
		String matricula = "1" + dataDeNascimento.getYear() + "001";
		diretorGeral.setMatricula(matricula);
		diretorGeral.setSenha(chave);
		return diretorGeral;
	}
		
		
	}
	
	
	
	
}