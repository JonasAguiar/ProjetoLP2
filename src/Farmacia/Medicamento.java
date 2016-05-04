package Farmacia;

import java.util.HashSet;
import java.util.Set;

public abstract class Medicamento implements Comparable<Medicamento> {
	
	private String nome;
	private double preco;
	private int quantidade;
	private Set<CategoriaMedicamento> categorias;
	
	
	public Medicamento(String nome, double preco, int quantidade, Set<CategoriaMedicamento> categorias){
		this.categorias = new HashSet<CategoriaMedicamento>();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	public Set<CategoriaMedicamento> getCategorias() {
		return categorias;
	}


	public void setCategorias(Set<CategoriaMedicamento> categorias) {
		this.categorias = categorias;
	}
	
	@Override
	public int compareTo(Medicamento outroMedicamento) {
		if(this.preco < outroMedicamento.preco){
			return -1;
		}else if(this.preco > outroMedicamento.preco){
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return "nome= " + nome + " tipo= " + getCategorias() + " preco= "+ preco + "quantidade= " + quantidade ;
	}

}
