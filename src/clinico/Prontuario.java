package clinico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import operacoesClinicas.Procedimento;

public class Prontuario implements Serializable{
	
	private List<Procedimento> procedimentos;
	private Paciente paciente;
	
	public Prontuario(Paciente paciente){
		this.procedimentos = new ArrayList();
		
	}

	
	/** Metodo que realiza o procedimento no paciente de acordo com o procedimento.
	 * @param procedimento
	 * @throws Exception
	 */
	public void realizaProcedimento(Procedimento procedimento) throws Exception{
		adicionaProcedimento(procedimento);
		procedimento.fazProcedimento(paciente);
		
	}
	
	
	/** Metodo que atribui o valor do medicamento ao paciente.
	 * @param valorDosRemedios
	 */
	public void atribuiValorMedicamento(int valorDosRemedios){
		int valorDosRemediosDesconto = paciente.calculaDescontoRemedio(valorDosRemedios);
		paciente.setTotalDeGastos(valorDosRemediosDesconto);
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
	
	/** Metodo que adiciona um procedimento a lista de procedimentos do prontuario.
	 * @param procedimento
	 */
	public void adicionaProcedimento(Procedimento procedimento){
		procedimentos.add(procedimento);
	}
	
	@Override
	public String toString() {
		String EOL = System.getProperty("line.separator");
		String stringPaciente = paciente.toString();
		String stringPadrao = "Resumo Procedimento: " + procedimentos.size() + " procedimento(s)" + EOL;
		String stringProcedimentos = "";
		for(Procedimento procedimento : procedimentos){
			stringProcedimentos += procedimento.toString() + EOL;
		}
		return stringPaciente + stringPadrao + stringProcedimentos;
	}
	
}	