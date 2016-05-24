package Farmacia;

import java.io.Serializable;
import java.util.Set;

public class MedicamentoGenerico extends Medicamento implements Serializable{

	public MedicamentoGenerico(String nome, double preco, int quantidade, Set<CategoriaMedicamento> categorias) {
		super(nome, preco, quantidade, categorias);		
	}


}
