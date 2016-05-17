package pessoal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartamentoADM {
	
	private FactoryFuncionarios factoryFuncionario;
	private Map<String, Funcionario> funcionarios;

	public DepartamentoADM(){
		this.funcionarios = new HashMap<String, Funcionario>();
		this.factoryFuncionario = new FactoryFuncionarios();
	}

	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento) throws Exception{
		
		if(funcionarios.get(nome).getDataDeNascimento().equals(dataDeNascimento)){
			throw new Exception(" Funcionario ja existe.");
		}
		Funcionario funcionario = factoryFuncionario.criaFuncionario(cargo, nome, dataDeNascimento);
		atribuiLogin(funcionario);
		funcionarios.put(funcionario.getMatricula(), funcionario);
			return funcionario.getMatricula();
		
		
	}
	
	
	public void removeFuncionario(String matricula) throws Exception{
		if(funcionarios.containsKey(matricula)){
			funcionarios.remove(matricula);
		}else{
			throw new Exception("Funcionario nao existe");
		}
		
		
	}
	
	public Funcionario pesquisaFuncionario(String matricula) throws Exception{
		if(funcionarios.containsKey(matricula)){
			return funcionarios.get(matricula);
		}else{
			throw new Exception("Funcionario nao cadastrado.");
		}
		
	}
	
	// alteracoes que podem ser feitas pelo diretor
	public void alteraSenha(String matricula, String novaSenha) throws Exception{
		if(novaSenha.length() > 8 && novaSenha.length() < 12 ){
			if(funcionarios.containsKey(matricula)){
				funcionarios.get(matricula).setSenha(novaSenha);
			}else{
				throw new Exception("Funcionario nao cadastrado.");
			}
		}else{
			throw new Exception("Senha invalida.");
		}
		
		
	}
	
	public void alteraNome(String matricula, String novoNome) throws Exception{
		if(novoNome.length() < 50){
			if(funcionarios.containsKey(matricula)){
				funcionarios.get(matricula).setNome(novoNome);
			}else{
				throw new Exception("Funcionario nao cadastrado.");
			}
		}else{
			throw new Exception("Nome maior que 50 caracteres.");
		}
	}
	
	public void alteraCargo(String matricula){
		
	}
	
	public void alteraDataDeNascimento(String matricula, String novaData) throws Exception{
		if(novaData == null || novaData.trim().equals("")){
			if(funcionarios.containsKey(matricula)){
				funcionarios.get(matricula).setDataDeNascimento(novaData);
			}else{
				throw new Exception("Funcionario nao cadastrado.");
			}
		}else{
			throw new Exception("Data invalida.");
		}
	}
	
	//alteracao do proprio usuario logado
	
	public void modificaNome(String matricula, String novoNome) throws Exception{
		if(novoNome.length() < 50){
			if(funcionarios.containsKey(matricula)){
				funcionarios.get(matricula).setNome(novoNome);
			}else{
				throw new Exception("Funcionario nao cadastrado.");
			}
		}else{
			throw new Exception("Nome nao pode ter mais de 50 caracteres.");
		}
		
	}
	
	public void modificaSenha(String matricula, String senhaAtual, String novaSenha) throws Exception{
		if(novaSenha.length() > 8 && novaSenha.length() < 12 ){
			if(funcionarios.containsKey(matricula)){
				Funcionario funcionario = funcionarios.get(matricula);
				if(funcionario.getSenha().equals(senhaAtual)){
					funcionario.setSenha(novaSenha);
					}else{
						throw new Exception("Senha incorreta.");
					}
			}else{
				throw new Exception("Funcionario nao cadastrado");
			}
		}else{
			throw new Exception("Senha invalida");
		}
		
	}
	
	public void modificaDataDeNascimento(String matricula, String novaData) throws Exception{
		if(novaData == null || novaData.trim().equals("")){
			if(funcionarios.containsKey(matricula)){
				Funcionario funcionario = funcionarios.get(matricula);
				funcionario.setDataDeNascimento(novaData);
			}else{
				throw new Exception("Funcionario nao cadastrado");
			}
		}else{
			throw new Exception("Data invalida.");
		}
		
	}
	
	
	
	// metodos de auxilio ao login
	
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
	
	private String complementoMatricula(){
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
	
	private String complementoSenha(String matricula){
		return  String.valueOf(matricula.charAt(0) +  matricula.charAt(1) +  matricula.charAt(2) +  matricula.charAt(3));
	}	

	
}
