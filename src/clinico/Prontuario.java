package clinico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import operacoesClinicas.Procedimento;

public class Prontuario{
	
	private List<Procedimento> procedimentos;
	private Paciente paciente;
	
	public Prontuario(Paciente paciente){
		this.procedimentos = new ArrayList();
		
	}

	
	public void realizaProcedimento(Procedimento procedimento) throws Exception{
		adicionaProcedimento(procedimento);
		procedimento.fazProcedimento(paciente);
	}
	
	
	public void atribuiValorMedicamento(int valorDosRemedios){
		paciente.setTotalDeGastos(valorDosRemedios);
	}
	
	
	
	public String getNomePaciente(){
		return paciente.getNome();
	}
	
	public double getPeso(){
		return paciente.getPeso();
	}
	
	public String getDataDeNascimento(){
		return paciente.getDataNascimento();
	}
	
	public String getTipoSanguineo(){
		return paciente.getTipoSanguineo();
	}
	
	public String getSexoBiologico(){
		return paciente.getSexoBiologico();
	}
	
	public String getGenero(){
		return paciente.getGenero();
	}
	
	public UUID getIdPaciente(){
		return paciente.getId();
	}
	
	public Paciente getPaciente(){
		return this.paciente;
	}
	
	public void adicionaProcedimento(Procedimento procedimento){
		procedimentos.add(procedimento);
	}
	
}	