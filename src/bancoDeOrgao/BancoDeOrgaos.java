package bancoDeOrgao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BancoDeOrgaos {
	
	private List<Orgao> bancoDeOrgaos;
	private Set<String> tiposDeSangue;
	
	
	public BancoDeOrgaos(){
		this.bancoDeOrgaos = new ArrayList<Orgao>();
		this.tiposDeSangue = new HashSet<String>();
	}
	
	public void cadastraOrgao(String nome, String tipo) throws Exception {
		if(tiposDeSangue.contains(tipo)){
			Orgao orgao = new Orgao(nome, tipo);
			bancoDeOrgaos.add(orgao);
			}else{
				throw new Exception("Tipo sanguineo invalido.");
			}
	}
	
	public boolean buscaOrgao(String nome, String tipo) throws Exception {
		if(tiposDeSangue.contains(tipo)){
			for(Orgao orgao : bancoDeOrgaos){
				if(orgao.getNome().equals(nome) && orgao.getTipo().equals(tipo)){
					return true;
					}else{
						throw new Exception("Orgao nao existe no banco de orgaos.");
					}
				}
			}
		throw new Exception("Tipo sanguineo invalido.");
	}
	
	
	public String buscaOrgPorNome(String nome) throws Exception{
		for(Orgao orgao : bancoDeOrgaos){
				if(orgao.getNome().equals(nome)){
					return orgao.getTipo();
			}
		}
				throw new Exception("Orgao nao cadastrado.");
		

	}
	
	
	
	public Orgao buscaOrgPorSangue(String tipo) throws Exception{
		if(tiposDeSangue.contains(tipo)){
			for(Orgao orgao : bancoDeOrgaos){
			if(orgao.getTipo().equals(tipo)){
				return orgao;
			}else{
				throw new Exception("Nao ha orgaos cadastrados para esse tipo sanguineo.");
					}
				}
			}
			
		throw new Exception("Tipo sanguineo invalido.");	
		
	}
	
	
	public void retiraOrgao(String nome, String tipo) throws Exception{
		if(tiposDeSangue.contains(tipo)){
			for(Orgao orgao: bancoDeOrgaos){
				if(orgao.getNome().equals(nome) && orgao.getTipo().equals(tipo)){
					bancoDeOrgaos.remove(orgao);
				}
			}
		}else{
			throw new Exception("Orgao nao cadastrado.");
		}
		throw new Exception("Tipo sanguineo invalido.");
	}
	
	
	public int qtdOrgaos(String nome) throws Exception{
		int qntdDeOrgao = 0;
		for(Orgao orgao : bancoDeOrgaos){
			if(orgao.getNome().equals(nome)){
				qntdDeOrgao++;
			}
		}
		
		if(qntdDeOrgao == 0){
			throw new Exception("Orgao nao cadastrado.");
		}else{
			return qntdDeOrgao;
		}
	}
	
	public int totalOrgaosDisponiveis(){
		return bancoDeOrgaos.size();
	}
	
	public boolean verificaOrgao(String nomeDoOrgao, String tipoDoPaciente) throws Exception{
		if(bancoDeOrgaos.contains(nomeDoOrgao)){
			String tipoOrgaoNoBanco = getOrgao(nomeDoOrgao).getTipo();
			if(tipoOrgaoNoBanco.equals(tipoDoPaciente)){
				return true;
			}
			return true;
		}else{
			throw new Exception("Banco nao possui o orgao especificado.");
		}
	}
	
	public Orgao getOrgao(String nomeDoOrgao){
		for(Orgao orgao : bancoDeOrgaos){
			if(orgao.getNome().equals(nomeDoOrgao)){
				return orgao;
			}
		}
		return null;
	}
	
	private void adicionaTipo(){
		tiposDeSangue.add("A+");
		tiposDeSangue.add("A-");
		tiposDeSangue.add("B+");
		tiposDeSangue.add("B-");
		tiposDeSangue.add("AB+");
		tiposDeSangue.add("AB-");
		tiposDeSangue.add("O-");
		tiposDeSangue.add("O+");
	}
}
