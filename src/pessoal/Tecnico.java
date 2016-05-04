package pessoal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import clinico.Paciente;
import clinico.Prontuario;
import clinico.ProntuarioComparator;

public class Tecnico extends Funcionario {
	
	private List<Prontuario> prontuarios;
	private List<Paciente> pacientes;
	
	public Tecnico(String nome, LocalDate dataDeNascimento) {
		super(nome, dataDeNascimento);
		this.prontuarios = new ArrayList<Prontuario>();
		this.pacientes = new ArrayList<Paciente>();

	}
	
	public void cadastraPaciente(String nome, double peso, LocalDate data, String tipoSanguineo, String sexo, String genero){
		Paciente paciente = new Paciente(nome, peso, data, tipoSanguineo, sexo, genero);
		Prontuario prontuario = new Prontuario(paciente);
		pacientes.add(paciente);
		prontuarios.add(prontuario);
		ProntuarioComparator comparator = new ProntuarioComparator();
		Collections.sort(prontuarios, comparator );
	}
	
	public String getInfoPaciente(UUID id, String atributo) {
		for (Paciente paciente : pacientes) {
			if (id.equals(paciente.getId())) {
				switch (atributo) {
				case "Nome":
					return paciente.getNome();
				case "Data":
					return String.valueOf(paciente.getDataNascimento());
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
	
	@Override
	public String toString() {
		return "Tecnico Administrativo";
	}

	
}
