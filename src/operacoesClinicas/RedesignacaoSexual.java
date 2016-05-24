package operacoesClinicas;

import java.io.Serializable;

import clinico.Paciente;
import pessoal.Funcionario;

public class RedesignacaoSexual implements Procedimento, Serializable{
	
	private Funcionario medico;
	private final int valor = 9300;
	private final int pontos = 130;
	private String data;
	private Funcionario medicoDoProcedimento;

	@Override
	public String toString() {
		String EOL = System.getProperty("line.separator");
		String linha1 = "--> Redesignacao sexual" + EOL;
		String linha2 = "....... Data: " + data + "Medico: "  + medicoDoProcedimento + EOL;
		return linha1 + linha2;
	}

	@Override
	public void fazProcedimento(Paciente paciente) throws Exception {
		if(paciente.getGenero().equals("Masculino")){
			paciente.setGenero("Feminino");
			paciente.adicionaGasto(valor);
		}else if(paciente.getGenero().equals("Feminino")){
			paciente.setGenero("Masculino");
			paciente.adicionaGasto(valor);
		}else{
			throw new Exception("Genero nao existe.");
		}
		
	}

	
	
	
}
