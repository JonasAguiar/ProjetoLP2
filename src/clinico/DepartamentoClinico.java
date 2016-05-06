package clinico;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DepartamentoClinico {

	private List<Prontuario> prontuarios;
	private List<Paciente> pacientes;
	
	public DepartamentoClinico(){
		this.pacientes = new ArrayList<Paciente>();
	}

	public String getInfoPaciente(UUID id, String atributo) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (Paciente paciente : pacientes) {
			if (id.equals(paciente.getId())) {
				switch (atributo) {
				case "Nome":
					return paciente.getNome();
				case "Data":
					return String.valueOf(paciente.getDataNascimento().format(formatador));
				case "Sexo":
					return paciente.getSexoBiologico();
				case "Genero":
					return paciente.getGenero();
				case "TipoSanguineo":
					return paciente.getTipoSanguineo();
				case "Peso":
					return String.valueOf(paciente.getPeso());
				case "Idade":
					LocalDate hoje = LocalDate.now();
					Period periodo = Period.between(paciente.getDataNascimento(), hoje);
					return String.valueOf(periodo.getYears());
				}
			}
		}
		return null;
	}
	
	public void cadastraPaciente(String nome, double peso, LocalDate data, String tipoSanguineo, String sexo, String genero){
		Paciente paciente = new Paciente(nome, peso, data, tipoSanguineo, sexo, genero);
		Prontuario prontuario = new Prontuario(paciente);
		pacientes.add(paciente);
		prontuarios.add(prontuario);
		ProntuarioComparator comparator = new ProntuarioComparator();
		Collections.sort(prontuarios, comparator );
	}
	
	
	
}