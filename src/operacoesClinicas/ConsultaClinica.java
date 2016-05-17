package operacoesClinicas;

public class ConsultaClinica extends Procedimento {

	private final int valor = 350;

	@Override
	public void procedimento() {
		// TODO Auto-generated method stub
		
	}

	public int getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return "Consulta Clinica";
	}
	
	
}
