package operacoesClinicas;

public class TransplanteDeOrgaos extends Procedimento {

	private final int valor = 12500;
	
	
	public void procedimento(){
		
	}


	public int getValor() {
		return valor;
	}


	@Override
	public String toString() {
		return "Transplante de Orgaos";
	}
	
}
