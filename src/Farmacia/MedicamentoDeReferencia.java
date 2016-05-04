package Farmacia;

import java.util.Set;

public class MedicamentoDeReferencia extends Medicamento{

	public MedicamentoDeReferencia(String nome, double preco, int quantidade, Set<CategoriaMedicamento> categorias) {
		
		super(nome, preco, quantidade, categorias);
		
	}

}
