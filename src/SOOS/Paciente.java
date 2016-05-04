package SOOS;

import java.time.LocalDate;
import java.util.UUID;

public class Paciente {
	
	private String nome;
	private double peso;
	private String dataNascimento;
	private String tipoSanguineo;
	private String sexoBiologico;
	private String genero;
	private UUID id;
	
	
	public Paciente(String nome, double peso, String data, String tipoSanguineo, String sexo, String genero){
		this.nome = nome;
		this.peso = peso;
		this.dataNascimento = data;
		this.tipoSanguineo = tipoSanguineo;
		this.sexoBiologico = sexo;
		this.genero = genero;
		this.id = UUID.randomUUID();
	}
}
