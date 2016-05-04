package clinico;

import java.util.Comparator;

public class ProntuarioComparator implements Comparator<Prontuario> {
	
	@Override
	public int compare(Prontuario prontuario, Prontuario outroProntuario) {
		return prontuario.getPaciente().getNome().compareTo(outroProntuario.getPaciente().getNome());
	}

}
