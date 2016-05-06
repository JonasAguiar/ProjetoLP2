package clinico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Prontuario{
	
	private Paciente paciente;
	private List procedimentos;
	
	public Prontuario(Paciente paciente){
		this.paciente = paciente;
		this.procedimentos = new ArrayList();
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}