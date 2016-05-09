package SOOS;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Farmacia.CategoriaMedicamento;
import Farmacia.Farmacia;
import Farmacia.Medicamento;
import clinico.DepartamentoClinico;
import pessoal.DepartamentoADM;
import pessoal.Diretor;
import pessoal.Funcionario;

public class Controller {
	
	private boolean sistemaLiberado;
	private DepartamentoClinico dptClinico;
	private DepartamentoADM dptADM;
	private Farmacia farmacia;
	private Map<String, Funcionario> funcionariosCadastrados;
	private Funcionario usuarioLogado;
	final String SENHA = "c041ebf8";
	
	public Controller(){
		this.sistemaLiberado = false;
		this.dptClinico = new DepartamentoClinico();
		this.dptADM = new DepartamentoADM();
		this.farmacia = new Farmacia();
		this.funcionariosCadastrados = new HashMap<String, Funcionario>();
	}

	Funcionario diretor;
	
	public boolean verificaChave(String chave){
		if(chave.equals(SENHA)) {
			return true;
			} return false;
		}
	 
	public String liberaSistema(String chave, String nome, String dataDeNascimento) throws Exception{
		if(sistemaLiberado == true){
			throw new Exception(" Sistema liberado anteriormente.");
		}
		else{
			if(verificaChave(chave)){
				sistemaLiberado = true;
				 return dptADM.cadastraFuncionario(nome, "diretor", dataDeNascimento);
				}else{
					throw new Exception(" Chave invalida.");
				} 
			
		}
		

	}
	
	/*public boolean verificaChamadaLiberaSistema() throws Exception{
		if(N_DO_METODO_INICIAL == 1){
			throw new Exception("Ja possui um diretor nesse sistema.");	
		}else{
			return true;
		}
	}*/
	
	
	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento) throws Exception{
		if(nome.trim().equals("")){
			throw new Exception(" Nome do funcionario nao pode ser vazio.");
		}if(nome == null){
			throw new Exception(" Nome do funcionario nao pode ser nulo.");
		}if(cargo.equals("")){
			throw new Exception(" Nome do cargo nao pode ser vazio.");
		}if(cargo == null){
			throw new Exception(" Nome do Cargo nao pode ser nulo.");
		}if(dataDeNascimento.trim().equals("") || dataDeNascimento == null){
			throw new Exception(" Data invalida.");
		}if(usuarioLogado != null && cargo.equals("diretor")){
			throw new Exception(" Nao eh possivel criar mais de um Diretor Geral.");
			
		}if(!(cargo.toLowerCase().equals("diretor")) || !(cargo.toLowerCase().equals("medico")) || !(cargo.toLowerCase().equals("tecnico administrativo"))){
			throw new Exception(" Cargo invalido.");
		}
		
		if(usuarioLogado != null && cargo.equals("diretor")){
			throw new Exception(" Nao eh possivel criar mais de um Diretor Geral.");
		}
		
		return dptADM.cadastraFuncionario(nome, cargo, dataDeNascimento);
	}
	
	
	public String login(String matricula, String senha) throws Exception{
		if(funcionariosCadastrados.containsKey(matricula)){
	
			if(funcionariosCadastrados.get(matricula).getSenha().equals(senha)){
				throw new Exception(" Senha incorreta.");
				
			}else{
				usuarioLogado = funcionariosCadastrados.get(matricula);
				return usuarioLogado.getMatricula();
			}
	
		}else{
			throw new Exception(" Funcionario nao cadastrado.");
		}
	}	

	
	
	
	public void logout() throws Exception {
		if(usuarioLogado == null){
			throw new Exception("Usuario ja deslogado.");
		}
		usuarioLogado = null;
		
	}
		
	public void fechaSistema(){
		
	
	}
	
	//FORWARDING DOS METODOS DA CLASSE FARMACIA
	public void criaMedicamento(String nome, String tipo, double preco, int quantidade, 
			Set<CategoriaMedicamento> categorias){
		farmacia.criaMedicamento(nome, tipo, preco, quantidade, categorias);
	}
	
	public void removeMedicamento(Medicamento medicamento){
		farmacia.removeMedicamento(medicamento);
	}
	
	public void atualizaMedicamento(String nome, String atributo, double novoValor){
		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}
	
	public String consultaMedNome(String nome){
		return farmacia.consultaMedNome(nome);
	}
	
	public List consultaMedCategoria(String categoria){
		return farmacia.consultaMedCategoria(categoria);
	}
	
	public List getEstoqueFarmacia(String ordenacao){
		return farmacia.getEstoqueFarmacia(ordenacao);
	}
}