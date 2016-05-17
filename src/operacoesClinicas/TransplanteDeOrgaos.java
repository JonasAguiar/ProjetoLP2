package operacoesClinicas;

import bancoDeOrgao.BancoDeOrgaos;
import bancoDeOrgao.Orgao;
import clinico.Paciente;

public class TransplanteDeOrgaos implements Procedimento {
	
	private final int valor = 12500;
	private Orgao orgao;
	
	
	public TransplanteDeOrgaos(Orgao orgao) {
		this.orgao = orgao;
	}

	@Override
	public void fazProcedimento(Paciente paciente) {
		String tipoPaciente = paciente.getTipoSanguineo();
		if(!orgao.getTipo().equals(tipoPaciente)){
			throw new Exception("Orgao incompativel.");
		}
		
		paciente.adicionaGasto(valor);
		paciente.adicionaPontos(pontos);
	}
	
	@Override
	public String toString() {
		return "Transplante de Orgaos";
	}
}
