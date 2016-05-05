package SOOS;

import java.time.LocalDate;

public class FacadeHospital {
	
	private Controller controller;
	
	public FacadeHospital(){
		this.controller = new Controller();
	}
	
	public Object iniciaSistema(String chave, String nome, LocalDate dataDeNascimento) throws Exception{
		return controller.iniciaSistema(chave, nome, dataDeNascimento);
	}
	
	public void liberaSistema(String chave, String nome, LocalDate dataDeNascimento) throws Exception{
		controller.liberaSistema(chave, nome, dataDeNascimento);
	}
	
	public boolean realizaLogin(String login, String senha){
		return controller.realizaLogin(login, senha);
	}
	
	public String logout(){
		return controller.logout();
	}
	
	public String fechaSistema(){
		return controller.fechaSistema();
	}
	
	
}
