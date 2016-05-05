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
	
	public String complementoMatricula(){
		if(funcionarios.size() == 0){
			return "001";
		}else if(funcionarios.size() <= 9){
			return "00" + String.valueOf(funcionarios.size());
		}else if(funcionarios.size() > 9 && funcionarios.size() < 99){
			return "0" + String.valueOf(funcionarios.size());
		}else{
			return String.valueOf(funcionarios.size());
		}
	}
	
	

	
	public String complementoSenha(String matricula){
		return  String.valueOf(matricula.charAt(0) +  matricula.charAt(1) +  matricula.charAt(2) +  matricula.charAt(3));
	}

	public Funcionario criaDiretor(String nome, LocalDate dataDeNascimento){
		String senha = "c041ebf8";
		Funcionario diretorGeral = new Diretor(nome, dataDeNascimento);
		String matricula = "1" + String.valueOf(LocalDate.now().getYear()) 
								+ complementoMatricula();
		diretorGeral.setMatricula(matricula);
		diretorGeral.setSenha(senha);
		return diretorGeral;
	}
	
	public Funcionario criaMedico(String nome, LocalDate dataDeNascimento){
		Funcionario medico = new Medico(nome, dataDeNascimento);
		String matricula = "2" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		medico.setMatricula(matricula);
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		medico.setSenha(senha);
		return medico;
	}
	
	public Funcionario criaTecnico(String nome, LocalDate dataDeNascimento){
		Funcionario tecnico = new Tecnico(nome, dataDeNascimento);
		String matricula = "3" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		tecnico.setMatricula(matricula);
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		tecnico.setSenha(senha);
		return tecnico;
	}
	
	
	
	
}