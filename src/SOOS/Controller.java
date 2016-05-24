package SOOS;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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

	/** Metodo que realiza a modificação da senha do usuario logado.
	 * @param senhaAtual
	 * @param novaSenha
	 * @throws Exception
	 */
	public void modificaSenha(String senhaAtual, String novaSenha) throws Exception {
		dptADM.alteraSenha(usuarioLogado.getMatricula(), novaSenha);
	}

	/** Metodo que realiza a modificação da data de nascimento do usuario logado.
	 * @param novaData
	 * @throws Exception
	 */
	public void modificaDataDeNascimento(String novaData) throws Exception {
		dptADM.alteraDataDeNascimento(usuarioLogado.getMatricula(), novaData);
	}

	// VALIDACOES DE PERMISSAO

	/** Metodo que realiza a verificação e validação do cargo do usuario logado, no caso tecnico.
	 * @param funcionario
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validaPermissaoTecnico(Funcionario funcionario) throws Exception {
		if (funcionario.getCargo() instanceof Tecnico || funcionario.getCargo() instanceof Diretor) {
			return true;
		} else {
			throw new Exception("Usuario nao tem permissao.");
		}
	}

	/** Metodo que realiza a verificação e validação do cargo do usuario logado, no caso Medico.
	 * @param funcionario
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validaPermissaoMedico(Funcionario funcionario) throws Exception {
		if (funcionario.getCargo() instanceof Tecnico || funcionario.getCargo() instanceof Diretor) {
			return true;
		} else {
			throw new Exception("Usuario nao tem permissao.");
		}
	}

	/** Metodo que realiza a verificação e validação do cargo do usuario logado, no caso Diretor.
	 * @param funcionario
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validaPermissaoDiretor(Funcionario funcionario) throws Exception {
		if (funcionario.getCargo() instanceof Diretor) {
			return true;
		} else {
			throw new Exception("Usuario nao tem permissao");
		}
	}

	// FORWADING CASE 3

	/** Metodo que realiza o forwading de getInfo para a classe dpt clinico.
	 * @param id
	 * @param atributo
	 * @return String
	 * @throws Exception
	 */
	public String getInfoPaciente(UUID id, String atributo) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return dptClinico.getInfoPaciente(id, atributo);
	}

	/** Metodo que realiza o forwading para o cadastra paciente do dp Clinico
	 * @param nome
	 * @param peso
	 * @param data
	 * @param tipoSanguineo
	 * @param sexo
	 * @param genero
	 * @return UUID
	 * @throws Exception
	 */
	public UUID cadastraPaciente(String nome, double peso, String data, String tipoSanguineo, String sexo,
			String genero) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return dptClinico.cadastraPaciente(nome, peso, data, tipoSanguineo, sexo, genero);
	}

	// FORWARDING DO CASE 4
	/** Metodo que realiza o forwading para criaMedicamento da Classe Farmacia.
	 * @param nome
	 * @param tipo
	 * @param preco
	 * @param quantidade
	 * @param categorias
	 * @throws Exception
	 */
	public void criaMedicamento(String nome, String tipo, double preco, int quantidade,
			Set<CategoriaMedicamento> categorias) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		farmacia.criaMedicamento(nome, tipo, preco, quantidade, categorias);
	}

	/** Metodo que realiza o forwading para removeMedicamento de farmacia.
	 * @param medicamento
	 * @throws Exception
	 */
	public void removeMedicamento(Medicamento medicamento) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		farmacia.removeMedicamento(medicamento);
	}

	/** Metodo que realiza o forwading para atualizaMedicamento de farmacia
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 * @throws Exception
	 */
	public void atualizaMedicamento(String nome, String atributo, double novoValor) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		farmacia.atualizaMedicamento(nome, atributo, novoValor);
	}

	/** Metodo que realiza o forwading para consultaMedNome em farmacia
	 * @param nome
	 * @return String
	 * @throws Exception
	 */
	public String consultaMedNome(String nome) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return farmacia.consultaMedNome(nome);
	}

	/** Metodo que realiza o forwading para ConsultaMedCategoria em farmacia
	 * @param categoria
	 * @return List
	 * @throws Exception
	 */
	public List consultaMedCategoria(String categoria) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return farmacia.consultaMedCategoria(categoria);
	}

	/** Metodo que realiza o forwading para getEstoque em farmacia
	 * @param ordenacao
	 * @return List
	 * @throws Exception
	 */
	public List getEstoqueFarmacia(String ordenacao) throws Exception {
		validaPermissaoTecnico(usuarioLogado);
		return farmacia.getEstoqueFarmacia(ordenacao);
	}

	// forwading case 5 - BANCO DE ORGAOS
	/** Metodo que realiza o forwading de cadastro em Banco de orgaos
	 * @param nome
	 * @param tipo
	 * @throws Exception
	 */
	public void cadastraOrgao(String nome, String tipo) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		bancoDeOrgaos.cadastraOrgao(nome, tipo);
	}

	/** Metodo que realiza o forwading de busca em banco de orgaos.
	 * @param nome
	 * @param tipo
	 * @return boolean
	 * @throws Exception
	 */
	public boolean buscaOrgao(String nome, String tipo) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		return bancoDeOrgaos.buscaOrgao(nome, tipo);
	}

	/** Metodo que realiza forwading de buscas em banco de orgaos.
	 * @param nome
	 * @return String
	 * @throws Exception
	 */
	public String buscaOrgPorNome(String nome) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		return bancoDeOrgaos.buscaOrgPorNome(nome);
	}

	/** Metodo que realiza o forwading de busca orgao para Banco de orgao.
	 * @param tipo
	 * @return Orgao
	 * @throws Exception
	 */
	public Orgao buscaOrgPorSangue(String tipo) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		return bancoDeOrgaos.buscaOrgPorSangue(tipo);
	}

	/** Metodo que realiza o forwading de retira orgao para o banco de orgao.
	 * @param nome
	 * @param tipo
	 * @throws Exception
	 */
	public void retiraOrgao(String nome, String tipo) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		bancoDeOrgaos.retiraOrgao(nome, tipo);
	}

	/** Metodo que realiza o forwading de qntOrgao em banco de orgao.
	 * @param nome
	 * @return int
	 * @throws Exception
	 */
	public int qtdOrgaos(String nome) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		return bancoDeOrgaos.qtdOrgaos(nome);
	}

	/** Metodo que realiza o forwading de total de orgaos para banco de orgaos.
	 * @return int 
	 * @throws Exception 
	 */
	public int totalOrgaosDisponiveis() throws Exception {
		validaPermissaoMedico(usuarioLogado);
		return bancoDeOrgaos.totalOrgaosDisponiveis();
	}

	// FORWADING CASE 6 e 7

	
	/** Metodo que realiza o forwading para realiza em Dp Clinico.
	 * @param procedimento
	 * @param orgao
	 * @param id
	 * @param medicamentos
	 * @throws Exception
	 */
	public void realizaProcedimento(String procedimento, String orgao, UUID id, String medicamentos) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		verificaProcedimento(procedimento);
		int valorRemedios = farmacia.verificaMedicamento(medicamentos);
		dptClinico.realizaTransplante(orgao, id, valorRemedios);
	}

	// procedimentos normais com medicamentos
	/** Metodo que realiza o forwading para realiza em Dp Clinico, a partir de sobrecarga
	 * @param procedimento
	 * @param id
	 * @param medicamentos
	 * @throws Exception
	 */
	public void realizaProcedimento(String procedimento, UUID id, String medicamentos) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		verificaProcedimento(procedimento);
		int valorMedicamentos = farmacia.verificaMedicamento(medicamentos);
		dptClinico.realizaProcedimento(procedimento, id, valorMedicamentos);
	}

	// procedimento de consulta
	/** Metodo que realiza o forwading para realiza em Dp Clinico, a partir de sobrecarga 
	 * @param procedimento
	 * @param id
	 * @throws Exception
	 */
	public void realizaProcedimento(String procedimento, UUID id) throws Exception {
		validaPermissaoMedico(usuarioLogado);
		verificaProcedimento(procedimento);
		dptClinico.realizaConsulta(id);

	}

	// verifica se o procedimento é um dos quatro possiveis.
	/** Metodo que verifica se o procedimento é valido, e está na lista de procedimentos.
	 * @param procedimento
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verificaProcedimento(String procedimento) throws Exception {
		if (procedimento.equals("Consulta clinica") || procedimento.equals("Cirurgia ariatrica")
				|| procedimento.equals("Redesignacao sexual") || procedimento.equals("Transplante de orgaos")) {
			return true;
		} else {
			throw new Exception("Procedimento invalido.");
		}

	}

	// case 8
	
	/** Metodo que realiza o forwading para a classe de gerenciamento de arquivos.
	 * @param id
	 * @throws IOException
	 * @throws Exception
	 */
	public void exportaFichaPaciente(String id) throws IOException, Exception {
		gerenciadorDeArquivos.exportaFichaPaciente(dptClinico.verificaPaciente(id));

	}

}
