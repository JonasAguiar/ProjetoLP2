package bancoDeOrgao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BancoDeOrgaos implements Serializable {

	private List<Orgao> bancoDeOrgaos;
	private Set<String> tiposDeSangue;

	public BancoDeOrgaos() {
		this.bancoDeOrgaos = new ArrayList<Orgao>();
		this.tiposDeSangue = new HashSet<String>();
		tiposDeSangue.add("A+");
		tiposDeSangue.add("A-");
		tiposDeSangue.add("B+");
		tiposDeSangue.add("B-");
		tiposDeSangue.add("AB+");
		tiposDeSangue.add("AB-");
		tiposDeSangue.add("O-");
		tiposDeSangue.add("O+");
	}

	
	/**Metodo de cadastro de orgao ao banco de orgao.
	 * @param nome
	 * @param tipo
	 * @throws Exception
	 */
	public void cadastraOrgao(String nome, String tipo) throws Exception {
		if (tiposDeSangue.contains(tipo)) {
			Orgao orgao = new Orgao(nome, tipo);
			bancoDeOrgaos.add(orgao);
		} else {
			throw new Exception("Tipo sanguineo invalido.");
		}
	}

	/** Metodo de busca de orgao, verifica se o orgao existe no banco.
	 * @param nome
	 * @param tipo
	 * @return boolean
	 * @throws Exception
	 */
	public boolean buscaOrgao(String nome, String tipo) throws Exception {
		if (tiposDeSangue.contains(tipo)) {
			for (Orgao orgao : bancoDeOrgaos) {
				if (orgao.getNome().equals(nome) && orgao.getTipo().equals(tipo)) {
					return true;
				} else {
					throw new Exception("Orgao nao existe no banco de orgaos.");
				}
			}
		}
		throw new Exception("Tipo sanguineo invalido.");
	}

	/** Metodo de busca orgao por nome.
	 * @param nome
	 * @return tipo saguineo do Orgao
	 * @throws Exception
	 */
	public String buscaOrgPorNome(String nome) throws Exception {
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				return orgao.getTipo();
			}
		}
		throw new Exception("Orgao nao cadastrado.");

	}

	/** Metodo de busca um orgao por tipo de orgao.
	 * @param tipo
	 * @return Orgao
	 * @throws Exception
	 */
	public Orgao buscaOrgPorSangue(String tipo) throws Exception {
		if (tiposDeSangue.contains(tipo)) {
			for (Orgao orgao : bancoDeOrgaos) {
				if (orgao.getTipo().equals(tipo)) {
					return orgao;
				} else {
					throw new Exception("Nao ha orgaos cadastrados para esse tipo sanguineo.");
				}
			}
		}

		throw new Exception("Tipo sanguineo invalido.");

	}

	/** Metodo que retira orgao do banco de orgao, se ele existir.
	 * @param nome
	 * @param tipo
	 * @throws Exception
	 */
	public void retiraOrgao(String nome, String tipo) throws Exception {
		if (tiposDeSangue.contains(tipo)) {
			for (Orgao orgao : bancoDeOrgaos) {
				if (orgao.getNome().equals(nome) && orgao.getTipo().equals(tipo)) {
					bancoDeOrgaos.remove(orgao);
				}
			}
		} else {
			throw new Exception("Orgao nao cadastrado.");
		}
		throw new Exception("Tipo sanguineo invalido.");
	}

	/** Metodo que mostra a quantidade de orgaos existentes no banco de orgaos por nome.
	 * @param nome
	 * @return quantidade de orgaos 
	 * @throws Exception
	 */
	public int qtdOrgaos(String nome) throws Exception {
		int qntdDeOrgao = 0;
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nome)) {
				qntdDeOrgao++;
			}
		}

		if (qntdDeOrgao == 0) {
			throw new Exception("Orgao nao cadastrado.");
		} else {
			return qntdDeOrgao;
		}
	}

	/** Metodo que retorna a quantidade de Orgao no total.
	 * @return quantidade de orgao no banco de orgao.
	 */
	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.size();
	}

	/** Metodo que verifica o orgao a ser doado, se o mesmo existir no banco de orgao.
	 * @param nomeDoOrgao
	 * @return Orgao
	 * @throws Exception
	 */
	public Orgao verificaOrgao(String nomeDoOrgao) throws Exception {
		if (bancoDeOrgaos.contains(nomeDoOrgao)) {
			Orgao orgaoDoado = getOrgao(nomeDoOrgao);
			return orgaoDoado;
		} else {
			throw new Exception("Banco nao possui o orgao especificado.");
		}

	}

	/** Metodo que retorna um orgao pelo nome.
	 * @param nomeDoOrgao
	 * @return Orgao
	 */
	public Orgao getOrgao(String nomeDoOrgao) {
		for (Orgao orgao : bancoDeOrgaos) {
			if (orgao.getNome().equals(nomeDoOrgao)) {
				return orgao;
			}
		}
		return null;
	}


}
