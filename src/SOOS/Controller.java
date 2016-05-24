package SOOS;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import Farmacia.CategoriaMedicamento;
import Farmacia.Farmacia;
import Farmacia.Medicamento;
import bancoDeOrgao.BancoDeOrgaos;
import bancoDeOrgao.Orgao;
import clinico.DepartamentoClinico;
import pessoal.DepartamentoADM;
import pessoal.Diretor;
import pessoal.Funcionario;
import pessoal.Tecnico;

public class Controller implements Serializable {

	private boolean sistemaLiberado;
	private BancoDeOrgaos bancoDeOrgaos;
	private DepartamentoClinico dptClinico;
	private DepartamentoADM dptADM;
	private Farmacia farmacia;
	private Map<String, Funcionario> funcionariosCadastrados;
	private Funcionario usuarioLogado;
	final String SENHA = "c041ebf8";
	private GerenciadorDeArquivos gerenciadorDeArquivos;

	public Controller() {
		this.sistemaLiberado = false;
		this.bancoDeOrgaos = bancoDeOrgaos;
		this.dptClinico = new DepartamentoClinico();
		this.dptADM = new DepartamentoADM();
		this.farmacia = new Farmacia();
		this.funcionariosCadastrados = new HashMap<String, Funcionario>();
		this.gerenciadorDeArquivos = gerenciadorDeArquivos;
	}

	Funcionario diretor;

	// FORWADING CASO 1
	
	/** Metodo que realiza a verificaçao da chave de acesso primario.
	 * @param chave
	 * @return
	 */
	public boolean verificaChave(String chave) {
		if (chave.equals(SENHA)) {
			return true;
		}
		return false;
	}

	/** Metodo que realiza a liberacao do sistema, e a criação do primeiro cadastro
	 * @param chave
	 * @param nome
	 * @param dataDeNascimento
	 * @return
	 * @throws Exception
	 */
	public String liberaSistema(String chave, String nome, String dataDeNascimento) throws Exception {
		if (sistemaLiberado == true) {
			throw new Exception(" Sistema liberado anteriormente.");
		} else {
			if (verificaChave(chave)) {
				sistemaLiberado = true;
				return dptADM.cadastraFuncionario(nome, "diretor", dataDeNascimento);
			} else {
				throw new Exception(" Chave invalida.");
			}

		}

	}

	/** Metodo que realiza o forwading de cadastro de funcionario.
	 * @param nome
	 * @param cargo
	 * @param dataDeNascimento
	 * @return String
	 * @throws Exception
	 */
	public String cadastraFuncionario(String nome, String cargo, String dataDeNascimento) throws Exception {
		if (nome.trim().equals("")) {
			throw new Exception(" Nome do funcionario nao pode ser vazio.");
		}
		if (nome == null) {
			throw new Exception(" Nome do funcionario nao pode ser nulo.");
		}
		if (cargo.equals("")) {
			throw new Exception(" Nome do cargo nao pode ser vazio.");
		}
		if (cargo == null) {
			throw new Exception(" Nome do Cargo nao pode ser nulo.");
		}
		if (dataDeNascimento.trim().equals("") || dataDeNascimento == null) {
			throw new Exception(" Data invalida.");
		}
		if (usuarioLogado != null && cargo.equals("diretor")) {
			throw new Exception(" Nao eh possivel criar mais de um Diretor Geral.");

		}
		if (!(cargo.toLowerCase().equals("diretor")) || !(cargo.toLowerCase().equals("medico"))
				|| !(cargo.toLowerCase().equals("tecnico administrativo"))) {
			throw new Exception(" Cargo invalido.");
		}

		validaPermissaoDiretor(usuarioLogado);

		if (usuarioLogado != null && cargo.equals("diretor")) {
			throw new Exception(" Nao eh possivel criar mais de um Diretor Geral.");
		}

		return dptADM.cadastraFuncionario(nome, cargo, dataDeNascimento);
	}

	/** Metodo que realiza o login do funcionario.
	 * @param matricula
	 * @param senha
	 * @return String
	 * @throws Exception
	 */
	public String login(String matricula, String senha) throws Exception {
		if (funcionariosCadastrados.containsKey(matricula)) {

			if (funcionariosCadastrados.get(matricula).getSenha().equals(senha)) {
				throw new Exception(" Senha incorreta.");

			} else {
				usuarioLogado = funcionariosCadastrados.get(matricula);
				return usuarioLogado.getMatricula();
			}

		} else {
			throw new Exception(" Funcionario nao cadastrado.");
		}
	}

	/** Metodo que realiza o logout do usuario.
	 * @throws Exception
	 */
	public void logout() throws Exception {
		if (usuarioLogado == null) {
			throw new Exception("Usuario ja deslogado.");
		}
		usuarioLogado = null;

	}

	/** Metodo que realiza a consulta e retorno de algum atributo do usuario.
	 * @param matricula
	 * @param atributo
	 * @return atributo
	 * @throws Exception
	 */
	public String getInfoFuncionario(String matricula, String atributo) throws Exception {
		validaPermissaoDiretor(usuarioLogado);
		return dptADM.getInfoFuncionario(matricula, atributo);
	}

	/** Metodo que realiza o fechamento do sistema
	 * 
	 */
	public void fechaSistema() {
	}

	// FORWADING CASE 2

	/** Metodo que realiza a remoçao de um funcionario, feito so pelo diretor.
	 * @param matricula
	 * @param senha
	 * @throws Exception
	 */
	public void removeFuncionario(String matricula, String senha) throws Exception {
		validaPermissaoDiretor(usuarioLogado);
		if (usuarioLogado.getSenha().equals(senha)) {
			dptADM.removeFuncionario(matricula);
		} else {
			throw new Exception("Senha incorreta.");
		}

	}

	/** Metodo que realiza a pesquisa de um funcionario pela matricula.
	 * @param matricula
	 * @return Funcionario
	 * @throws Exception
	 */
	public Funcionario pesquisaFuncionario(String matricula) throws Exception {
		validaPermissaoDiretor(usuarioLogado);
		return dptADM.pesquisaFuncionario(matricula);
	}

	/** Metodo que realiza a alteracão da senha de um funcionario.
	 * @param matricula
	 * @param novaSenha
	 * @throws Exception
	 */
	public void alteraSenha(String matricula, String novaSenha) throws Exception {
		validaPermissaoDiretor(usuarioLogado);
		dptADM.alteraSenha(matricula, novaSenha);
	}

	/** Metodo que realiza a alteração do nome de um funcionario.
	 * @param matricula
	 * @param novoNome
	 * @throws Exception
	 */
	public void alteraNome(String matricula, String novoNome) throws Exception {
		validaPermissaoDiretor(usuarioLogado);
		dptADM.alteraNome(matricula, novoNome);
	}

	/** Metodo que realiza a alteraçao da data de nascimento de um funcionario.
	 * @param matricula
	 * @param novaData
	 * @throws Exception
	 */
	public void alteraDataDeNascimento(String matricula, String novaData) throws Exception {
		validaPermissaoDiretor(usuarioLogado);
		dptADM.alteraDataDeNascimento(matricula, novaData);
	}

	/** Metodo que realiza a modificação do nome do usuario logado.
	 * @param novoNome
	 * @throws Exception
	 */
	public void modificaNome(String novoNome) throws Exception {
		dptADM.modificaNome(usuarioLogado.getMatricula(), novoNome);
	}

	public void modificaSenha(String senhaAtual, String novaSenha) throws Exception {
		dptADM.alteraSenha(usuarioLogado.getMatricula(), novaSenha);
	}

	public void modificaDataDeNascimento(String novaData) throws Exception {
		dptADM.alteraDataDeNascimento(usuarioLogado.getMatricula(), novaData);
	}

	// VALIDACOES DE PERMISSAO

	public boolean validaPermissaoTecnico(Funcionario funcionario) throws Exception {
		if (funcionario.getCargo() instanceof Tecnico || funcionario.getCargo() instanceof Diretor) {
			return true;
		} else {
			throw new Exception("Usuario nao tem permissao.");
		}
	}

	public boolean validaPermissaoMedico(Funcionario funcionario) throws Exception {
		if (funcionario.getCargo() instanceof Tecnico || funcionario.getCargo() instanceof Diretor) {
			return true;
		} else {
			throw new Exception("Usuario nao tem permissao.");
		}
	}

	public boolean validaPermissaoDiretor(Funcionario funcionario) throws Exception {
		if (funcionario.getCargo() instanceof Diretor) {
			return true;
		} else {
			throw new Exception("Usuario nao tem permissao");
		}
	}

	// FORWADING CASE 3

	public String getInfoPaciente(UUID id, String atributo) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return dptClinico.getInfoPaciente(id, atributo);
	}

	public UUID cadastraPaciente(String nome, double peso, String data, String tipoSanguineo, String sexo,
			String genero) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return dptClinico.cadastraPaciente(nome, peso, data, tipoSanguineo, sexo, genero);
	}

	// FORWARDING DO CASE 4
	public void criaMedicamento(String nome, String tipo, double preco, int quantidade,
			Set<CategoriaMedicamento> categorias) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		farmacia.criaMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	public void removeMedicamento(Medicamento medicamento) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		farmacia.removeMedicamento(medicamento);
	}

	public void atualizaMedicamento(String nome, String atributo, double novoValor) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}

	public String consultaMedNome(String nome) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return farmacia.consultaMedNome(nome);
	}

	public List consultaMedCategoria(String categoria) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return farmacia.consultaMedCategoria(categoria);
	}

	public List getEstoqueFarmacia(String ordenacao) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return farmacia.getEstoqueFarmacia(ordenacao);
	}

	// forwading case 5 - BANCO DE ORGAOS
	public void cadastraOrgao(String nome, String tipo) throws Exception {
		bancoDeOrgaos.cadastraOrgao(nome, tipo);
	}

	public boolean buscaOrgao(String nome, String tipo) throws Exception {
		return bancoDeOrgaos.buscaOrgao(nome, tipo);
	}

	public String buscaOrgPorNome(String nome) throws Exception {
		return bancoDeOrgaos.buscaOrgPorNome(nome);
	}

	public Orgao buscaOrgPorSangue(String tipo) throws Exception {
		return bancoDeOrgaos.buscaOrgPorSangue(tipo);
	}

	public void retiraOrgao(String nome, String tipo) throws Exception {
		bancoDeOrgaos.retiraOrgao(nome, tipo);
	}

	public int qtdOrgaos(String nome) throws Exception {
		return bancoDeOrgaos.qtdOrgaos(nome);
	}

	public int totalOrgaosDisponiveis() {
		return bancoDeOrgaos.totalOrgaosDisponiveis();
	}

	// FORWADING CASE 6 e 7

	public void realizaProcedimento(String procedimento, String orgao, UUID id, String medicamentos) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		verificaProcedimento(procedimento);
		int valorRemedios = farmacia.verificaMedicamento(medicamentos);
		dptClinico.realizaTransplante(orgao, id, valorRemedios);
	}

	// procedimentos normais com medicamentos
	public void realizaProcedimento(String procedimento, UUID id, String medicamentos) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		verificaProcedimento(procedimento);
		int valorMedicamentos = farmacia.verificaMedicamento(medicamentos);
		dptClinico.realizaProcedimento(procedimento, id, valorMedicamentos);
	}

	// procedimento de consulta
	public void realizaProcedimento(String procedimento, UUID id) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		verificaProcedimento(procedimento);
		dptClinico.realizaConsulta(id);

	}

	// verifica se o procedimento é um dos quatro possiveis.
	public boolean verificaProcedimento(String procedimento) throws Exception {
		if (procedimento.equals("Consulta clinica") || procedimento.equals("Cirurgia ariatrica")
				|| procedimento.equals("Redesignacao sexual") || procedimento.equals("Transplante de orgaos")) {
			return true;
		} else {
			throw new Exception("Procedimento invalido.");
		}

	}

	public void exportaFichaPaciente(String id) throws IOException, Exception {
		gerenciadorDeArquivos.exportaFichaPaciente(dptClinico.verificaPaciente(id));

	}

}
