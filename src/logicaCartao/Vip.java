package logicaCartao;



public class Vip implements CartaoFidelidade {

	private final int desconto = 30;
	private final int credito = 10;
	@Override
	public int calculaPontos(int pontos) {
		int pontosVip = pontos + credito;
		return pontosVip;
	}
	@Override
	public int calculaDesconto(int valor) {
		int valorComDesconto = valor - (valor * desconto)/100;
		return valorComDesconto;
	}
	
	

}
