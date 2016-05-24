package operacoesClinicas;

import java.io.Serializable;

import clinico.Paciente;
import pessoal.Funcionario;

public class CirurgiaBariatrica implements Procedimento, Serializable{

	private final int valor = 7600;
	private final int pontos = 100;
	private Funcionario medicoDoProcedimento;
	private String data;
	

	

	
	@Override
	public void fazProcedimento(Paciente paciente) {
		double novoPeso = (15.0 * paciente.getPeso()) / 100.0;
		paciente.setPeso(novoPeso);
		paciente.adicionaGasto(valor);
		paciente.adicionaPontos(pontos);
		
		
	}
	
	
	
	@Override
	public String toString() {
		String EOL = System.getProperty("line.separator");
		String linha1 = "--> Cirurgia bariatrica:" + EOL;
		String linha2 = "....... Data: " + data + "Medico: "  + medicoDoProcedimento + EOL;

		return linha1 + linha2;
	}
	
}
