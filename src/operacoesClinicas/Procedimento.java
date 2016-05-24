package operacoesClinicas;

import clinico.Paciente;

public interface Procedimento {
	
	
	
	/** Metodo que realiza o procedimento no paciente de acordo com o tipo de procedimento, alterando informações do paciente
	 * e realizando os calculos devidos.
	 * @param paciente
	 * @throws Exception
	 */
	public void fazProcedimento(Paciente paciente) throws Exception;
	
	

}
