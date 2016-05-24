package pessoal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartamentoADM implements Serializable{
	
	private FactoryFuncionarios factoryFuncionario;
	private Map<String, Funcionario> funcionarios;

	public DepartamentoADM(){
		this.funcionarios = new HashMap<String, Funcionario>();
		this.factoryFuncionario = new FactoryFuncionarios();
	}

	/** Metodo que realiza o cadastro de um funcionario com todas suas informações iniciais
	 * @param nome
	 * @param cargo
	 * @param dataDeNascimento
	 * @return Matricula
	 * @throws Exception
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento) throws Exception{
		
		if(funcionarios.get(nome).getDataDeNascimento().equals(dataDeNascimento)){
			throw new Exception(" Funcionario ja existe.");
		}
		Funcionario funcionario = factoryFuncionario.criaFuncionario(cargo, nome, dataDeNascimento);
		atribuiLogin(funcionario);
		funcionarios.put(funcionario.getMatricula(), funcionario);
			return funcionario.getMatricula();
		
		
	}
	
	
	/** Metodo que realiza a remoçao de um funcionario, a partir de sua matricula
	 * @param matricula
	 * @throws Exception
	 */
	public void removeFuncionario(String matricula) throws Exception{
		if(funcionarios.containsKey(matricula)){
			funcionarios.remove(matricula);
		}else{
			throw new Exception("Funcionario nao existe");
		}
		
		
	}
	
	/** Metodo que realiza a pesquisa de um funcionario atraves da matricula do mesmo.
	 * @param matricula
	 * @return Funcionario
	 * @throws Exception
	 */
	public Funcionario pesquisaFuncionario(String matricula) throws Exception{
		if(funcionarios.containsKey(matricula)){
			return funcionarios.get(matricula);
		}else{
			throw new Exception("Funcionario nao cadastrado.");
		}
		
	}
	
	// alteracoes que podem ser feitas pelo diretor
	/** Metodo que altera a senha de um funcionario, feita pelo diretor
	 * @param matricula
	 * @param novaSenha
	 * @throws Exception
	 */
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
	
	/** Metodo que realiza a troca do nome de um funcionario, realizado pelo diretor
	 * @param matricula
	 * @param novoNome
	 * @throws Exception
	 */
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
	
	
	/** Metodo que realiza a alteracao da data de nascimento de um funcionario, feita pelo diretor.
	 * @param matricula
	 * @param novaData
	 * @throws Exception
	 */
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
	
	/** Metodo que realiza a alteracao do nome do usuario logado.
	 * @param matricula
	 * @param novoNome
	 * @throws Exception
	 */
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
	
	/** Metodo que realiza a alteracao da senha do usuario logado.
	 * @param matricula
	 * @param senhaAtual
	 * @param novaSenha
	 * @throws Exception
	 */
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
	
	/** Metodo que realiza a alteracao da data de nascimento do usuario logado.
	 * @param matricula
	 * @param novaData
	 * @throws Exception
	 */
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
	
	
	/** Metodo que realiza a consulta de um atributo do funcionario
	 * @param matricula
	 * @param atributo
	 * @return atributo
	 * @throws Exception
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws Exception{
		Funcionario funcionario = pesquisaFuncionario(matricula);
		switch (atributo){
		case "nome":
			return funcionario.getNome();
		case "data":			
			return String.valueOf(funcionario.getDataDeNascimento());
		case "cargo":
			return String.valueOf(funcionario.getCargo());
		case "senha":
			throw new Exception("A senha do funcionario eh protegida");
		}
		return null;
	}
	
	// metodos de auxilio ao login
	
	/** Metodo que realiza a atribuição de login a um novo cadastro
	 * @param funcionario
	 */
	private void atribuiLogin(Funcionario funcionario){
		if(funcionario.getCargo() instanceof Diretor){
			atribuiLoginDiretor(funcionario);
		}else if(funcionario.getCargo() instanceof Medico){
			atribuiLoginMedico(funcionario);
		}else if(funcionario.getCargo() instanceof Tecnico){
			atribuiLoginTecnico(funcionario);
		}
		
	}
	
	/** Metodo que realiza a atribuiçao de um login de diretor
	 * @param funcionario
	 */
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
	
	/** Metodo que realiza a atribuiçao de um login de medico ao um novo cadastro
	 * @param funcionario
	 */
	private void atribuiLoginMedico (Funcionario funcionario){
		String matricula = "2" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		funcionario.setMatricula(matricula);
		LocalDate dataDeNascimento = formataData(funcionario.getDataDeNascimento());
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		funcionario.setSenha(senha);
	}
	
	/** Metodo que realiza a atribuiçao de um login de Tecnico a um novo cadastro
	 * @param funcionario
	 */
	private void atribuiLoginTecnico(Funcionario funcionario){
		
		String matricula = "3" + String.valueOf(LocalDate.now().getYear())  + complementoMatricula();
		funcionario.setMatricula(matricula);
		LocalDate dataDeNascimento = formataData(funcionario.getDataDeNascimento());
				
		String senha = String.valueOf(dataDeNascimento.getYear()) + complementoSenha(matricula);
		funcionario.setSenha(senha);
	}
	
	/** Metodo que realiza o retorno do complemento da matricula
	 * @return String
	 */
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
	
	/** Metodo que realiza o retorno do complemento da matricula
	 * @param matricula
	 * @return String
	 */
	private String complementoSenha(String matricula){
		return  String.valueOf(matricula.charAt(0) +  matricula.charAt(1) +  matricula.charAt(2) +  matricula.charAt(3));
	}	

	
}
