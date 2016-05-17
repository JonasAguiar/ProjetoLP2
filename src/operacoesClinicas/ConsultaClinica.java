package operacoesClinicas;

import clinico.Paciente;

public class ConsultaClinica implements Procedimento {

	private final int valor = 350;

	@Override
	public void fazProcedimento(Paciente paciente) {
		paciente.adicionaGasto(valor);
		
	}


	@Override
	public String toString() {
		return "Consulta Clinica";
	}

	
	
	
}
