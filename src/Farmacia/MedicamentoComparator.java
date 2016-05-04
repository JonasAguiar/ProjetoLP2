package Farmacia;

import java.util.Comparator;

public class MedicamentoComparator implements Comparator<Medicamento>{

	@Override
	public int compare(Medicamento medicamento, Medicamento outroMedicamento) {
		return medicamento.getNome().compareTo(outroMedicamento.getNome());
	}
	
	

}
