package Farmacia;

import java.util.Set;

public class FactoryDeMedicamentos {
	
	public Medicamento criaMedicamento(String nome, String tipo, double preco, int quantidade,
			Set<CategoriaMedicamento> categorias){
		if (tipo.equalsIgnoreCase("referencia")){
			Medicamento medicamento = new MedicamentoDeReferencia(nome, preco, quantidade, categorias);
			return medicamento;
		}else if (tipo.equalsIgnoreCase("generico")){
			double precoComDesconto = preco * 0.6;
			Medicamento medicamento = new MedicamentoGenerico(nome, precoComDesconto, quantidade, categorias);
			return medicamento;
		}return null;
	}

}
