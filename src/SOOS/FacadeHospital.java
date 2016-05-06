package SOOS;

import java.time.LocalDate;

public class FacadeHospital {
	
	private Controller controller;
	
	public FacadeHospital(){
		this.controller = new Controller();
	}
	
	public void iniciaSistema() throws Exception{
		try {
			controller.iniciaSistema();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void liberaSistema(String chave, String nome, LocalDate dataDeNascimento) throws Exception{
		try {
			controller.liberaSistema(chave, nome, dataDeNascimento);
		} catch (Exception e) {
			
		}
	}
	
	public void login(String login, String senha){
		try {
			controller.login(login, senha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logout(){
		try {
			controller.logout();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void fechaSistema(){
		try {
			controller.fechaSistema();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
}
