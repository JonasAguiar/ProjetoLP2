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
	
	private int N_DO_METODO_INICIAL = 0;
	private DepartamentoClinico dptClinico;
	private DepartamentoADM dptADM;
	private Farmacia farmacia;
	private Map<String, Funcionario> funcionariosCadastrados;
	private Funcionario usuarioLogado;
	final String SENHA = "c041ebf8";
	
	public Controller(){
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
	
	public void iniciaSistema(){
		
	}
	 
	public void liberaSistema(String chave, String nome, LocalDate dataDeNascimento) throws Exception{
		if(verificaChamadaLiberaSistema()){
			if(verificaChave(chave)){
				if(usuarioLogado != null && !(usuarioLogado.getCargo() instanceof Diretor)){
					usuarioLogado = dptADM.cadastraFuncionario(nome, "diretor", dataDeNascimento);
					
				}else{
					
					
				}
			}else{
				throw new Exception("Erro ao liberar o sistema. Chave invalida");
				}
		}
	}
	
	public boolean verificaChamadaLiberaSistema() throws Exception{
		if(N_DO_METODO_INICIAL == 1){
			throw new Exception("Ja possui um diretor nesse sistema.");	
		}else{
			return true;
		}
	}
	
	public boolean login(String matricula, String senha) throws Exception{
		if(funcionariosCadastrados.containsKey(matricula)){
			if(funcionariosCadastrados.get(matricula).getSenha().equals(senha)){
				usuarioLogado = funcionariosCadastrados.get(matricula);
				return true;
			}else{
				throw new Exception("Nao foi possivel realizar login");
			}
		}return false;
		
	}
	public String logout() {
		usuarioLogado = null;
		return "logout";
	}
		
	public String fechaSistema(){
		return "fechaSistema";
	
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