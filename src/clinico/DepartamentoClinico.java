package clinico;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import bancoDeOrgao.BancoDeOrgaos;
import bancoDeOrgao.Orgao;
import operacoesClinicas.CirurgiaBariatrica;
import operacoesClinicas.ConsultaClinica;
import operacoesClinicas.FactoryDeProcedimentos;
import operacoesClinicas.Procedimento;
import operacoesClinicas.RedesignacaoSexual;
import operacoesClinicas.TransplanteDeOrgaos;

public class DepartamentoClinico implements Serializable {
	
	private BancoDeOrgaos bancoDeOrgaos;
	private List<Prontuario> prontuarios;
	private FactoryDeProcedimentos factoryProcedimentos;
	
	
	public DepartamentoClinico(){
		this.prontuarios = new ArrayList<Prontuario>();
		this.bancoDeOrgaos = new BancoDeOrgaos();
		this.factoryProcedimentos = new FactoryDeProcedimentos();
		
	}

	
	/** Metodo que cadastra paciente na clinica.
	 * @param nome
	 * @param peso
	 * @param data
	 * @param tipoSanguineo
	 * @param sexo
	 * @param genero
	 * @return ID
	 * @throws Exception
	 */
	public UUID cadastraPaciente(String nome, double peso, String data, String tipoSanguineo, String sexo, String genero) throws Exception{
		Paciente paciente = new Paciente(nome, peso, data, tipoSanguineo, sexo, genero);
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getPaciente().equals(paciente)){
				throw new Exception("Paciente ja cadastrado.");
			}else{
				Prontuario prontuarioPaciente = new Prontuario(paciente);
				prontuarios.add(prontuarioPaciente);
			}
		}
		ProntuarioComparator comparator = new ProntuarioComparator();
		Collections.sort(prontuarios, comparator );
		return paciente.getId();
	}
	
	/** Metodo que realiza procedimento clinico no paciente.
	 * @param procedimento
	 * @param id
	 * @param valorMedicamentos
	 * @throws Exception
	 */
	public void realizaProcedimento(String procedimento, UUID id, int valorMedicamentos) throws Exception{
		
		switch (procedimento) {
		case "Cirurgia bariatrica":
			realizaCirurgiaBariatrica(id, valorMedicamentos);
		case "Consulta clinica":
			realizaConsulta(id, valorMedicamentos);
		case "Redesignacao sexual":
			realizaRedesignacao(id, valorMedicamentos);

		default:
			break;
		}
		
		
	}
	
	
	/** Metodo que verifica se existe um paciente com aquele nome, e retorna seu prontuario.
	 * @param paciente
	 * @return Prontuario
	 * @throws Exception
	 */
	public Prontuario verificaPaciente(String paciente) throws Exception{
		for (Prontuario prontuario : prontuarios){
			if(prontuario.getNomePaciente().equals(paciente)){
				return prontuario;
			}else{
				throw new Exception("Paciente nao cadastrado.");
			}
		}
		return null;
	}
	
	
	
	/** Metodo de realizar transplante
	 * @param orgao
	 * @param id
	 * @param valorMedicamentos
	 * @throws Exception
	 */
	public void realizaTransplante(String orgao, UUID id, int valorMedicamentos) throws Exception{
		
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getIdPaciente().equals(id)){
				Orgao orgaoDoado = bancoDeOrgaos.verificaOrgao(orgao);
				Procedimento procedimento = factoryProcedimentos.criaTransplante(orgaoDoado);
				prontuario.realizaProcedimento(procedimento);
				prontuario.atribuiValorMedicamento(valorMedicamentos);
			}else {
				throw new Exception("Paciente nao cadastrado.");
				}
			}
		}
		
	
	
	public void realizaConsulta(UUID id, int valorMedicamentos) throws Exception{
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getIdPaciente().equals(id)){
				Procedimento procedimento = factoryProcedimentos.criaProcedimento("Consulta clinica");
				prontuario.realizaProcedimento(procedimento);
				prontuario.atribuiValorMedicamento(valorMedicamentos);
			}else {
				throw new Exception("Paciente nao cadastrado.");
				}
			}
	}
	
	
	/** Metodo que realiza consulta clinica ao paciente passado.
	 * @param id
	 * @throws Exception
	 */
	public void realizaConsulta(UUID id) throws Exception{
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getIdPaciente().equals(id)){
				Procedimento procedimento = factoryProcedimentos.criaProcedimento("Consulta clinica");
				prontuario.realizaProcedimento(procedimento);
			}else {
				throw new Exception("Paciente nao cadastrado.");
				}
			}
	}
	
	
	
	/** Metodo que realiza cirurgia bariatrica.
	 * @param id
	 * @param valorMedicamentos
	 * @throws Exception
	 */
	public void realizaCirurgiaBariatrica(UUID id, int valorMedicamentos) throws Exception{
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getIdPaciente().equals(id)){
				Procedimento procedimento = factoryProcedimentos.criaProcedimento("Cirurgia bariatrica");
				prontuario.realizaProcedimento(procedimento);
				prontuario.atribuiValorMedicamento(valorMedicamentos);
			}else {
				throw new Exception("Paciente nao cadastrado.");
				}
			}
		
		
	}
	
	/** Metodo que realiza consulta as informacoes do paciente
	 * @param id
	 * @param atributo
	 * @return String
	 */
	public String getInfoPaciente(UUID id, String atributo) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (Prontuario prontuario : prontuarios) {
			if (id.equals(prontuario.getIdPaciente())) {
				switch (atributo) {
				case "Nome":
					return prontuario.getNomePaciente();
				case "Data":
					return String.valueOf(prontuario.getDataDeNascimento().format(formatador));
				case "Sexo":
					return prontuario.getSexoBiologico();
				case "Genero":
					return prontuario.getGenero();
				case "TipoSanguineo":
					return prontuario.getTipoSanguineo();
				case "Peso":
					return String.valueOf(prontuario.getPeso());
				case "Idade":
					LocalDate hoje = LocalDate.now();
					Period periodo = Period.between(prontuario.getDataDeNascimento(), hoje);
					return String.valueOf(periodo.getYears());
				}
				}
			}
		}
	
	/** Metodo que realiza mudanca de genero no paciente.
	 * @param id
	 * @param valorMedicamentos
	 * @throws Exception
	 */
	public void realizaRedesignacao(UUID id, int valorMedicamentos) throws Exception{
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getIdPaciente().equals(id)){
				Procedimento procedimento = factoryProcedimentos.criaProcedimento("Redesignacao sexual");
				prontuario.realizaProcedimento(procedimento);
				prontuario.atribuiValorMedicamento(valorMedicamentos);
			}else {
				throw new Exception("Paciente nao cadastrado.");
				}
			}
		
	}
	
	/** Metodo que retorna um paciente a partir de um id.
	 * @param id
	 * @return Paciente
	 */
	public Paciente getPaciente(String id){
		for(Prontuario prontuario : prontuarios){
			if(prontuario.getIdPaciente().equals(id)){
				return prontuario.getPaciente();
			}
		}return null;
	}
	
	

	
	/** Metodo que verifica o genero do paciente e retorna o genero de troca.
	 * @param paciente
	 * @return String
	 */
	public String verificaGenero(Paciente paciente){
		if(paciente.getGenero().equals("Masculino")){
			return "Feminino";
		}else{
			return "Masculino";
		}
	}

	

	
	
	
		
}