package Farmacia;

import java.io.Serializable;
import java.util.Set;

public class MedicamentoDeReferencia extends Medicamento implements Serializable{

	public MedicamentoDeReferencia(String nome, double preco, int quantidade, Set<CategoriaMedicamento> categorias) {
		
		super(nome, preco, quantidade, categorias);
		
	}

}
