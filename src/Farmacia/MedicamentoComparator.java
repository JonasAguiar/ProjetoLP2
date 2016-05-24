package Farmacia;

import java.io.Serializable;
import java.util.Comparator;

public class MedicamentoComparator implements Comparator<Medicamento>, Serializable{

	@Override
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
	}
	
	

}
