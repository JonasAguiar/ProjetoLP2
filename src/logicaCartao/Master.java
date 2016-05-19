package logicaCartao;

public class Master implements CartaoFidelidade{
	
	private final int desconto = 15;
	private final int credito = 5;
	
	
	@Override
	public int calculaPontos(int pontos) {
		int pontosMaster = pontos + credito;
		return pontosMaster;
	}
	@Override
	public int calculaDesconto(int valor) {
		int valorComDesconto = valor - (valor*desconto)/100;
		return valorComDesconto;
	}

	
}
