package Farmacia;

import java.util.HashSet;
import java.util.Set;

public class Medicamento {
	
	private String nome;
	private double preco;
	private int quantidade;
	private Set<CategoriaMedicamento> categoria;
	
	public Medicamento(String nome, double preco, int quantidade){
		this.categoria = new HashSet<CategoriaMedicamento>();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

}
