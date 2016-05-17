package operacoesClinicas;

import clinico.Paciente;

public class RedesignacaoSexual implements Procedimento {
	
	private final int valor = 9300;

	@Override
	public String toString() {
		return "Redesignacao Sexual";
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
