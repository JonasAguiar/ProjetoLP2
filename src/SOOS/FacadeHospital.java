package SOOS;

import java.time.LocalDate;

import bancoDeOrgao.Orgao;

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

	
	public String cadastraFuncionario1(String nome, String cargo, String dataDeNascimento) throws Exception{
		
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
	
	
	public void cadastraOrgao(String nome, String tipo) throws Exception{
		try {
			controller.cadastraOrgao(nome, tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public boolean buscaOrgao(String nome, String tipo) throws Exception{
		try {
			controller.buscaOrgao(nome, tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return false;
	}
	
	public String buscaOrgPorNome(String nome) throws Exception{
		try {
			controller.buscaOrgPorNome(nome);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return nome;
	}
	
	public Orgao buscaOrgPorSangue(String tipo) throws Exception{
		try {
			controller.buscaOrgPorSangue(tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		;
		return null;
	}
	
	public void retiraOrgao(String nome, String tipo) throws Exception{
		try {
			controller.retiraOrgao(nome, tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}
	
	public int qtdOrgaos(String nome) throws Exception{
		try {
			controller.qtdOrgaos(nome);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return 0;
	}
	
	public int totalOrgaosDisponiveis() throws Exception{
		try {
			controller.totalOrgaosDisponiveis();
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return 0;
	}
	
}
