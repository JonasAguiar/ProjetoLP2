package logicaCartao;

import clinico.Paciente;
import operacoesClinicas.Procedimento;

public interface CartaoFidelidade {
	
	
	
	/** Metodo que realiza o calculo de pontos a partir do tipo de cartao.
	 * @param pontos
	 * @return pontos
	 */
	public int calculaPontos(int pontos);
	
	/** Metodo que realiza o calculo do desconto a partir do tipo de cartao.
	 * @param valor
	 * @return valor
	 */
	public int calculaDesconto(int valor);

}
