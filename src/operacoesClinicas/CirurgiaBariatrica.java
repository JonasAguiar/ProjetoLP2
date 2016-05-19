package operacoesClinicas;

import clinico.Paciente;

public class CirurgiaBariatrica implements Procedimento{

	private final int valor = 7600;
	private final int pontos = 100;
	

	

	@Override
	public void fazProcedimento(Paciente paciente) {
		double novoPeso = (15.0 * paciente.getPeso()) / 100.0;
		paciente.setPeso(novoPeso);
		paciente.adicionaGasto(valor);
		paciente.adicionaPontos(pontos);
		
		
	}
	
	
	
	@Override
	public String toString() {
		return "Cirurgia bariatrica";
	}
	
}
