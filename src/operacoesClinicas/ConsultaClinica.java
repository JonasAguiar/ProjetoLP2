package operacoesClinicas;

import java.io.Serializable;

import clinico.Paciente;
import pessoal.Funcionario;

public class ConsultaClinica implements Procedimento, Serializable {

	private final int valor = 350;
	private final int pontos = 50;
	private Funcionario medicoDoProcedimento;
	private String data;
	
	@Override
	public void fazProcedimento(Paciente paciente) {
		paciente.adicionaGasto(valor);
		
	}


	@Override
	public String toString() {
		String EOL = System.getProperty("line.separator");
		String linha1 = "--> Consulta clinica:" + EOL;
		String linha2 = "....... Data: " + data + "Medico: "  + medicoDoProcedimento + EOL;

		return linha1 + linha2;
	}

	
	
	
}
