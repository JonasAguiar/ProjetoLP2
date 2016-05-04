package SOOS;

import Farmacia.Farmacia;
import clinico.DepartamentoClinico;
import pessoal.DepartamentoADM;

public class Controller {
	
	private int N_DO_METODO_INICIAL = 0;
	private DepartamentoClinico dptClinico;
	private DepartamentoADM dptADM;
	private Farmacia farmacia;
	
	public Controller(){
		this.dptClinico = new DepartamentoClinico();
		this.dptADM = new DepartamentoADM();
		this.farmacia = new Farmacia();
	}
	

	public void liberaSistema(String chave) throws Exception{
		verificachamadaLiberaSistema();
		if(chave.equals("c041ebf8") ){
			dptADM.criaUsuarioInicial();
			
		}
	}
	
	public void verificachamadaLiberaSistema() throws Exception{
		if(N_DO_METODO_INICIAL == 1){
			throw new Exception("Já possui um diretor nesse sistema.");	
		}
	}
}
