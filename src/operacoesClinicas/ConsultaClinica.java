package operacoesClinicas;

import clinico.Paciente;

public class ConsultaClinica implements Procedimento {

	private final int valor = 350;
	private final int pontos = 50;
	
	@Override
	public void fazProcedimento(Paciente paciente) {
		paciente.adicionaGasto(valor);
		
	}


	@Override
	public String toString() {
		return "Consulta Clinica";
	}

	
	
	
}
