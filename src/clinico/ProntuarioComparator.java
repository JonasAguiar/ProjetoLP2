package clinico;

import java.io.Serializable;
import java.util.Comparator;

	public class ProntuarioComparator implements Comparator<Prontuario>, Serializable {
	
	@Override
	public int compare(Prontuario prontuario, Prontuario outroProntuario) {
		return prontuario.getNomePaciente().compareTo(outroProntuario.getNomePaciente());
	}

}
