package operacoesClinicas;

public abstract class Procedimento {

	private int valor;
	
	public Procedimento(){
	}
	
	public abstract void procedimento();
	
	public abstract int getValor();
	

	public abstract String toString();
	
}
