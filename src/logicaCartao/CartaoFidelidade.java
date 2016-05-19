package logicaCartao;

import clinico.Paciente;
import operacoesClinicas.Procedimento;

public interface CartaoFidelidade {
	
	
	
	public int calculaPontos(int pontos);
	public int calculaDesconto(int valor);

}
