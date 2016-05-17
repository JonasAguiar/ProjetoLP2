package Farmacia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Farmacia {
	
	private List<Medicamento> estoqueFarmacia;
	private FactoryDeMedicamentos factoryMedicamentos;
	
	public Farmacia(){
		this.factoryMedicamentos = new FactoryDeMedicamentos();
		this.estoqueFarmacia = new ArrayList<Medicamento>();
	}
	
	public void criaMedicamento(String nome, String tipo, double preco, int quantidade, 
		Set<CategoriaMedicamento> categorias){
		Medicamento medicamento = factoryMedicamentos.criaMedicamento(nome, tipo, preco, quantidade, categorias);
		estoqueFarmacia.add(medicamento);
	}
	
	public void removeMedicamento(Medicamento medicamento) throws Exception{
		if(estoqueFarmacia.contains(medicamento)){
			estoqueFarmacia.remove(medicamento);
		}else{
			throw new Exception("Remedio nao cadastrado.");
		}
	}
	
	public void atualizaMedicamento(String nome, String atributo, double novoValor){
		for(Medicamento medicamento : estoqueFarmacia){
			if(medicamento.getNome().equals(nome)){
				if(atributo.equalsIgnoreCase("preco")){
					medicamento.setPreco(novoValor);
				}else if(atributo.equalsIgnoreCase("quantidade")){
					medicamento.setQuantidade((int)(novoValor));
				}
				
			}
		}
	}
	
	public String consultaMedNome(String nome){
		for (Medicamento medicamento : estoqueFarmacia){
			if(nome.equals(medicamento.getNome())){
				return medicamento.toString();
			}
		}return null;
	}
	
	public List consultaMedCategoria(String categoria){
		List<Medicamento> medicamentosDaCategoria = new ArrayList<Medicamento>();
		for (Medicamento medicamento : estoqueFarmacia){
			if(medicamento.getCategorias().contains(categoria)){
				medicamentosDaCategoria.add(medicamento);
			}
		}Collections.sort(medicamentosDaCategoria);
		List<String> medicamentosOrdenados = new ArrayList<String>();
		for (Medicamento medicamento : medicamentosDaCategoria){
			medicamentosOrdenados.add(medicamento.getNome());
		}return medicamentosOrdenados;
	}
	
	public List getEstoqueFarmacia(String ordenacao){
		switch (ordenacao){
		case "preco":
			Collections.sort(estoqueFarmacia);
			return estoqueFarmacia;
		case "alfabetica":
			MedicamentoComparator comparator = new MedicamentoComparator();
			Collections.sort(estoqueFarmacia, comparator);
			return estoqueFarmacia;			
		}return null;
	}
	
	public List<Medicamento> retornaEstoque(){
		return this.estoqueFarmacia;
	}
	
	public boolean verificaMedicamento(String medicamentos) throws Exception{
		String listaDeMedimento[] = medicamentos.split(Pattern.quote(","));
		for(String medicamento : listaDeMedimento){
			if(estoqueFarmacia.contains(medicamento)){
				return true;
			}else{
				throw new Exception("Medicamento nao cadastrado.");
			}
		}
		return false;
	}
}
