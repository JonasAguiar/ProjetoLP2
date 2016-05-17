package clinico;

import java.util.Comparator;

public class ProntuarioComparator implements Comparator<Prontuario> {
	
	@Override
	public int compare(Prontuario prontuario, Prontuario outroProntuario) {
		return prontuario.getNomePaciente().compareTo(outroProntuario.getNomePaciente());
	}

}
