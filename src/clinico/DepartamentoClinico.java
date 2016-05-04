package clinico;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoClinico {

	private List<Prontuario> prontuarios;
	private List<Paciente> pacientes;
	
	public DepartamentoClinico(){
		this.pacientes = new ArrayList<Paciente>();
	}

	public String getInfoPaciente(UUID id, String atributo) {
		for (Paciente paciente : pacientes) {
			if (id.equals(paciente.getId())) {
				switch (atributo) {
				case "Nome":
					return paciente.getNome();
				case "Data":
					return paciente.getDataNascimento();
				case "Sexo":
					return paciente.getSexoBiologico();
				case "Genero":
					return paciente.getGenero();
				case "TipoSanguineo":
					return paciente.getTipoSanguineo();

				}
			}
		}
		return null;
	}
}
