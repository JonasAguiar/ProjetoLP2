package pessoal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartamentoADM {
	
	private FactoryFuncionarios factoryFuncionario;
	private Map<Integer, Funcionario> funcionarios;

	public DepartamentoADM(){
		this.funcionarios = new HashMap<Integer, Funcionario>();
		this.factoryFuncionario = new FactoryFuncionarios();
	}

	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento){
		Funcionario funcionario = factoryFuncionario.criaFuncionario(cargo, nome, dataDeNascimento);
		atribuiLogin(funcionario);
			return funcionario.getMatricula();
	}
	
	
	
	private void atribuiLogin(Funcionario funcionario){
		if(funcionario.getCargo() instanceof Diretor){
			atribuiLoginDiretor(funcionario);
		}else if(funcionario.getCargo() instanceof Medico){
			atribuiLoginMedico(funcionario);
		}else if(funcionario.getCargo() instanceof Tecnico){
			atribuiLoginTecnico(funcionario);
		}
		
	}
	
	private void atribuiLoginDiretor(Funcionario funcionario){
		String matricula = "1" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		funcionario.setMatricula(matricula);
		LocalDate dataDeNascimento = formataData(funcionario.getDataDeNascimento());
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		funcionario.setSenha(senha);
	}
	
	
	private LocalDate formataData(String data){
		
		String[] datas = data.split("/");
		
		int dia = Integer.parseInt(datas[0]);
		int mes = Integer.parseInt(datas[1]);
		int ano = Integer.parseInt(datas[2]);
		
		LocalDate dataFormatada = LocalDate.of(ano, mes, dia);
		return dataFormatada;
		
	}
	
	
	
	
	private void atribuiLoginMedico (Funcionario funcionario){
		String matricula = "2" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		funcionario.setMatricula(matricula);
		LocalDate dataDeNascimento = formataData(funcionario.getDataDeNascimento());
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		funcionario.setSenha(senha);
	}
	
	private void atribuiLoginTecnico(Funcionario funcionario){
		
		String matricula = "3" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		funcionario.setMatricula(matricula);
		LocalDate dataDeNascimento = formataData(funcionario.getDataDeNascimento());
				
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		funcionario.setSenha(senha);
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

	
}