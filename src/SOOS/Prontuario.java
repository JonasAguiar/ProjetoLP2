package SOOS;

import java.util.ArrayList;
import java.util.List;

public class Prontuario {
	
	private Paciente paciente;
	private List procedimentos;
	
	public Prontuario(Paciente paciente){
		this.paciente = paciente;
		this.procedimentos = new ArrayList();
	}
	
}
