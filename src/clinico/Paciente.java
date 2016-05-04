package clinico;

import java.time.LocalDate;
import java.util.UUID;

public class Paciente {
	
	private String nome;
	private double peso;
	private LocalDate dataNascimento;
	private String tipoSanguineo;
	private String sexoBiologico;
	private String genero;
	private UUID id;
	
	
	public Paciente(String nome, double peso, LocalDate data, String tipoSanguineo, String sexo, String genero){
		this.nome = nome;
		this.peso = peso;
		this.dataNascimento = data;
		this.tipoSanguineo = tipoSanguineo;
		this.sexoBiologico = sexo;
		this.genero = genero;
		this.id = UUID.randomUUID();
	}
	


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getTipoSanguineo() {
		return tipoSanguineo;
	}


	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}


	public String getSexoBiologico() {
		return sexoBiologico;
	}


	public void setSexoBiologico(String sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}
}
