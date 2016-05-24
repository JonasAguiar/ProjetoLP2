package logicaCartao;

import java.io.Serializable;

public class Padrao implements CartaoFidelidade, Serializable{

	@Override
	public int calculaPontos(int pontos) {
		return pontos;
	}
	@Override
	public int calculaDesconto(int valor) {
		return valor;
		
	}
	

	
}
