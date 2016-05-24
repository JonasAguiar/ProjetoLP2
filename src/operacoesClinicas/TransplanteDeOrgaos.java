package operacoesClinicas;

import java.io.Serializable;

import bancoDeOrgao.BancoDeOrgaos;
import bancoDeOrgao.Orgao;
import clinico.Paciente;

public class TransplanteDeOrgaos implements Procedimento, Serializable {
	
	private final int valor = 12500;
	private final int pontos = 160;
	private Orgao orgao;
	private String data;
	private String medicoDoProcedimento;
	
	
	public TransplanteDeOrgaos(Orgao orgao) {
		this.orgao = orgao;
	}

	@Override
	public void fazProcedimento(Paciente paciente) throws Exception {
		String tipoPaciente = paciente.getTipoSanguineo();
		if(!orgao.getTipo().equals(tipoPaciente)){
			throw new Exception("Orgao incompativel.");
		}
		
		paciente.adicionaGasto(valor);
		paciente.adicionaPontos(pontos);
	}
	
	@Override
	public String toString() {
		String EOL = System.getProperty("line.separator");
		String linha1 = "--> Transplanete de orgaos:" + EOL;
		String linha2 = "....... Data: " + data + "Medico: "  + medicoDoProcedimento + EOL;

		return linha1 + linha2;
	}
}
