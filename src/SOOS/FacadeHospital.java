package SOOS;

import java.time.LocalDate;

public class FacadeHospital {
	
	private Controller controller;
	
	public FacadeHospital(){
		this.controller = new Controller();
	}
	
	public void iniciaSistema() {
	}
	
	public String liberaSistema(String chave, String nome, String dataDeNascimento) throws Exception{
		try {
			controller.liberaSistema(chave, nome, dataDeNascimento);
		} catch (Exception e) {
			throw new Exception("Erro ao liberar o sistema." + e.getMessage());
		}
		return null;
	}
	
	public String login(String login, String senha) throws Exception{
		try {
			controller.login(login, senha);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel realizar o login." + e.getMessage());
		}
		return null;
	}

	
	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento) throws Exception{
		
		try {
			controller.cadastraFuncionario(nome, cargo, dataDeNascimento);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de funcionario." + e.getMessage());
		}
		return null;
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
