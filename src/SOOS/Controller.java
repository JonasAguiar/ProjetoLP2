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
import pessoal.Funcionario;

public class Controller {
	
	private int N_DO_METODO_INICIAL = 0;
	private DepartamentoClinico dptClinico;
	private DepartamentoADM dptADM;
	private Farmacia farmacia;
	private Map<String, Funcionario> funcionariosCadastrados;
	
	public Controller(){
		this.dptClinico = new DepartamentoClinico();
		this.dptADM = new DepartamentoADM();
		this.farmacia = new Farmacia();
		this.funcionariosCadastrados = new HashMap<String, Funcionario>();
	}

	
	public void iniciaSistema(String chave, String nome, LocalDate dataDeNascimento) throws Exception{
		liberaSistema(chave, nome, dataDeNascimento);
	}
	
	public void liberaSistema(String chave, String nome, LocalDate dataDeNascimento) throws Exception{
		verificaChamadaLiberaSistema();
		if(chave.equals("c041ebf8") ){
			
			Funcionario diretor = dptADM.criaDiretor(chave, nome, dataDeNascimento);
			N_DO_METODO_INICIAL += 1;
			funcionariosCadastrados.put(diretor.getMatricula(), diretor);
		}
	}
	
	public void verificaChamadaLiberaSistema() throws Exception{
		if(N_DO_METODO_INICIAL == 1){
			throw new Exception("Já possui um diretor nesse sistema.");	
		}
	}
	
	public boolean realizaLogin(String login, String senha){
		if(funcionariosCadastrados.containsKey(login)){
			if(funcionariosCadastrados.get(login).getSenha().equals(senha)){
				return true;
			}
		}return false;
		
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
