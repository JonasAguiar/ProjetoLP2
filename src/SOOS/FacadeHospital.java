package SOOS;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import Farmacia.CategoriaMedicamento;
import Farmacia.Medicamento;
import bancoDeOrgao.Orgao;

public class FacadeHospital implements Serializable {

	private Controller controller;

	public FacadeHospital() {
		this.controller = new Controller();
	}

	public void iniciaSistema() {
	}

	public String liberaSistema(String chave, String nome, String dataDeNascimento) throws Exception {
		try {
			controller.liberaSistema(chave, nome, dataDeNascimento);
		} catch (Exception e) {
			throw new Exception("Erro ao liberar o sistema." + e.getMessage());
		}
		return null;
	}

	public String login(String login, String senha) throws Exception {
		try {
			controller.login(login, senha);
		} catch (Exception e) {
			throw new Exception("Nao foi possivel realizar o login." + e.getMessage());
		}
		return null;
	}

	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento) throws Exception {

		try {
			controller.cadastraFuncionario(nome, cargo, dataDeNascimento);
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de funcionario." + e.getMessage());
		}
		return null;

	}

	public void logout() {
		try {
			controller.logout();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void fechaSistema() {
		try {
			controller.fechaSistema();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void removeFuncionario(String matricula, String senha) throws Exception {
		try {
			controller.removeFuncionario(matricula, senha);
		} catch (Exception e) {
			throw new Exception("Erro ao excluir funcionario. " + e.getMessage());
		}

	}

	public void pesquisaFuncionario(String matricula) throws Exception {
		try {
			controller.pesquisaFuncionario(matricula);
		} catch (Exception e) {
			throw new Exception("Erro na consulta de funcionario." + e.getMessage());
		}
	}

	public void alteraSenha(String matricula, String novaSenha) throws Exception {

	}

	
	public void getInfoFuncionario(String matricula, String atributo) throws Exception{
		try {
			controller.getInfoFuncionario(matricula, atributo);
		} catch (Exception e) {
			throw new Exception("Erro na consultado de funcionario. " + e.getMessage());
		}
		
	}
	
	
	
	public void alteraNome(String matricula, String novoNome) throws Exception {
		try {
			controller.alteraNome(matricula, novoNome);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	public void alteraDataDeNascimento(String matricula, String novaData) throws Exception {
		try {
			controller.alteraDataDeNascimento(matricula, novaData);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	public void modificaNome(String novoNome) throws Exception {
		try {
			controller.modificaNome(novoNome);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}

	}

	public void modificaSenha(String senhaAtual, String novaSenha) throws Exception {
		try {
			controller.modificaSenha(senhaAtual, novaSenha);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	public void modificaDataDeNascimento(String novaData) throws Exception {
		try {
			controller.modificaDataDeNascimento(novaData);
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar funcionario. " + e.getMessage());
		}
	}

	
	public void getInfoPaciente(UUID id, String atributo) throws Exception {
		controller.getInfoPaciente(id, atributo);
	}

	public void cadastraPaciente(String nome, double peso, String data, String tipoSanguineo, String sexo, String genero) throws Exception{
		try{
			controller.cadastraPaciente(nome, peso, data, tipoSanguineo, sexo, genero);
		}catch (Exception e){
			throw new Exception("Nao foi possivel cadastrar o paciente. " + e.getMessage());
		}
	}
	
	public void criaMedicamento(String nome, String tipo, double preco, int quantidade, 
			Set<CategoriaMedicamento> categorias) throws Exception{
		try{
			controller.criaMedicamento(nome, tipo, preco, quantidade, categorias);
		}catch (Exception e){
			throw new Exception("Erro no cadastro de medicamento. " + e.getMessage());
		}
	}
	
	public void removeMedicamento(Medicamento medicamento) throws Exception{
		controller.removeMedicamento(medicamento);
	}
	
	public void atualizaMedicamento(String nome, String atributo, double novoValor) throws Exception{
		try{
			controller.atualizaMedicamento(nome, atributo, novoValor);
		}catch (Exception e){
			throw new Exception("Erro ao atualizar medicamento. " + e.getMessage());
		}
	}
	public void consultaMedNome(String nome) throws Exception{
		try{
			controller.consultaMedNome(nome);
		}catch (Exception e){
			throw new Exception("Erro na consulta de medicamentos. " + e.getMessage());
		}
		
	}
	public void consultaMedCategoria(String categoria) throws Exception{
		try{
			controller.consultaMedCategoria(categoria);
		}catch (Exception e){
			throw new Exception("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}

	public void getEstoqueFarmacia(String ordenacao) throws Exception{
		try{
			controller.getEstoqueFarmacia(ordenacao);
		}catch (Exception e){
			throw new Exception("Erro na consulta de medicamentos. " + e.getMessage());
		}
	}
	
	
	
	
	public void cadastraOrgao(String nome, String tipo) throws Exception {
		try {
			controller.cadastraOrgao(nome, tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public boolean buscaOrgao(String nome, String tipo) throws Exception {
		try {
			controller.buscaOrgao(nome, tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return false;
	}

	public String buscaOrgPorNome(String nome) throws Exception {
		try {
			controller.buscaOrgPorNome(nome);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return nome;
	}

	public Orgao buscaOrgPorSangue(String tipo) throws Exception {
		try {
			controller.buscaOrgPorSangue(tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		;
		return null;
	}

	public void retiraOrgao(String nome, String tipo) throws Exception {
		try {
			controller.retiraOrgao(nome, tipo);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
	}

	public int qtdOrgaos(String nome) throws Exception {
		try {
			controller.qtdOrgaos(nome);
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return 0;
	}

	public int totalOrgaosDisponiveis() throws Exception {
		try {
			controller.totalOrgaosDisponiveis();
		} catch (Exception e) {
			throw new Exception("O banco de orgaos apresentou um erro. " + e.getMessage());
		}
		return 0;
	}
	
	public void realizaProcedimento(String procedimento, String orgao, UUID id, String medicamentos) throws Exception{
		try {
			controller.realizaProcedimento(procedimento, orgao, id, medicamentos);
		} catch (Exception e) {
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	public void realizaProcedimento(String procedimento, UUID id, String medicamentos) throws Exception{
		try {
			controller.realizaProcedimento(procedimento, id, medicamentos);
		} catch (Exception e) {
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	public void realizaProcedimento(String procedimento, UUID id) throws Exception{
		try {
			controller.realizaProcedimento(procedimento, id);
		} catch (Exception e) {
			throw new Exception("Erro na realizacao de procedimentos. " + e.getMessage());
		}
	}
	
	public void verificaProcedimento(String procedimento) throws Exception{
		try {
			controller.verificaProcedimento(procedimento);
		} catch (Exception e) {
			throw new Exception("Erro na realizacao de procedimentos. "+ e.getMessage());
		}
	}
	
	public void exportaFichaPaciente(String id) throws Exception{
		try {
			controller.exportaFichaPaciente(id);
		} catch (Exception e) {
			throw new Exception("Erro nos arquivos. " + e.getMessage());
		}
	}
	
	
	
}




