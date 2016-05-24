package operacoesClinicas;

import java.io.Serializable;

import bancoDeOrgao.Orgao;

public class FactoryDeProcedimentos implements Serializable{
	
	
	
	
	/** Metodo que realiza a criaçao de Procedimento.
	 * @param procedimento
	 * @return Procedimento
	 */
	public Procedimento criaProcedimento(String procedimento){
		
		switch (procedimento) {
		case "Cirurgia bariatrica":
			return criaCirurgiaBariatrica();
		case "Consulta clinica":
			return criaConsultaClinica();
		case "Redesignacao Sexual":
			return criaRedesignacaoSexual();
		default:
			break;
		}
		return null;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	public Procedimento criaTransplante(Orgao orgao){
		Procedimento transplante = new TransplanteDeOrgaos(orgao);
		return transplante;
		
	}
	
	private Procedimento criaConsultaClinica(){
		Procedimento consulta = new ConsultaClinica();
		return consulta;
	}
	
	private Procedimento criaRedesignacaoSexual(){
		Procedimento redesignacao = new RedesignacaoSexual();
		return redesignacao;
	}
	
	private Procedimento criaCirurgiaBariatrica(){
		Procedimento cirugiaBariatrica = new CirurgiaBariatrica();
		return cirugiaBariatrica;
	}
	
	
	

}
