package Farmacia;

import java.util.Set;

public class MedicamentoGenerico extends Medicamento{

	public MedicamentoGenerico(String nome, double preco, int quantidade, Set<CategoriaMedicamento> categorias) {
		super(nome, preco, quantidade, categorias);		
	}


}
