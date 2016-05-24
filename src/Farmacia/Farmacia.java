package Farmacia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Farmacia implements Serializable{
	
	private List<Medicamento> estoqueFarmacia;
	private FactoryDeMedicamentos factoryMedicamentos;
	
	public Farmacia(){
		this.factoryMedicamentos = new FactoryDeMedicamentos();
		this.estoqueFarmacia = new ArrayList<Medicamento>();
	}
	
	/** Metodo de criaçao de medicamento, realizando forwading
	 * @param nome
	 * @param tipo
	 * @param preco
	 * @param quantidade
	 * @param categorias
	 */
	public void criaMedicamento(String nome, String tipo, double preco, int quantidade, 
		Set<CategoriaMedicamento> categorias){
		Medicamento medicamento = factoryMedicamentos.criaMedicamento(nome, tipo, preco, quantidade, categorias);
		estoqueFarmacia.add(medicamento);
	}
	
	/** Metodo que realiza a remoçao de um medicamento da lista de medicamentos.
	 * @param medicamento
	 * @throws Exception
	 */
	public void removeMedicamento(Medicamento medicamento) throws Exception{
		if(estoqueFarmacia.contains(medicamento)){
			estoqueFarmacia.remove(medicamento);
		}else{
			throw new Exception("Remedio nao cadastrado.");
		}
	}
	
	/** Metodo que realiza a atualizacao de atributos do medicamento
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 */
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
	
	/** Metodo que realiza a consulta de um medicamento atraves do nome.
	 * @param nome
	 * @return String
	 */
	public String consultaMedNome(String nome){
		for (Medicamento medicamento : estoqueFarmacia){
			if(nome.equals(medicamento.getNome())){
				return medicamento.toString();
			}
		}return null;
	}
	
	/** Metodo que realiza a consulta de medicamentos atraves de algum tipo.
	 * @param categoria
	 * @return List
	 */
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
	
	/** Metodo que realiza o retorno de todos os medicamentos do estoque, a partir de um tipo de ordenação.
	 * @param ordenacao
	 * @return List
	 */
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
	
	/** Metodo que realiza o retorno de todos os medicamentos da lista de medicamentos.
	 * @return List
	 */
	public List<Medicamento> retornaEstoque(){
		return this.estoqueFarmacia;
	}
	
	/** Metodo que realiza a verificação de existencia de um medicamento e retorna se existir o seu valor.
	 * @param medicamentos
	 * @return int
	 * @throws Exception
	 */
	public int verificaMedicamento(String medicamentos) throws Exception{
		String listaDeMedimento[] = medicamentos.split(Pattern.quote(","));
		for(String medicamento : listaDeMedimento){
			int valorDoMedicamento = 0;
			if(estoqueFarmacia.contains(medicamento)){
				valorDoMedicamento += getMedicamento(medicamento).getPreco();
				return valorDoMedicamento;
			}else{
				throw new Exception("Medicamento nao cadastrado.");
			}
		}
		return 0;
		
	}
	
	/** Metodo que realiza o retorno de um medicamento pelo nome.
	 * @param nome
	 * @return medicamento
	 */
	public Medicamento getMedicamento(String nome){
		for(Medicamento medicamento : estoqueFarmacia){
			if(medicamento.getNome().equals(nome)){
				return medicamento;
			}
		}
		return null;
	}
	
}
