package logicaCartao;


public class Padrao implements CartaoFidelidade{

	@Override
	public int calculaPontos(int pontos) {
		return pontos;
	}
	@Override
	public int calculaDesconto(int valor) {
		return valor;
		
	}
	

	
}
