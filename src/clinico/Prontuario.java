package clinico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import operacoesClinicas.Procedimento;

public class Prontuario{
	
	private List<Procedimento> procedimentos;
	
	public Prontuario(){
		this.procedimentos = new ArrayList();
	}

	public String getNomePaciente(){
		return null;
	}
	
	public int getPeso(){
		return 0;
	}
	
	public String getDataDeNascimento(){
		return null;
	}
	
	public String getTipoSanguineo(){
		return null;
	}
	
	public String getSexoBiologico(){
		return null;
	}
	
	public String getGenero(){
		return null;
	}
	
	public UUID getIdPaciente(){
		return null;
	}
	
	public void adicionaProcedimento(Procedimento procedimento){
		procedimentos.add(procedimento);
	}
	
}	