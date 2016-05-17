package clinico;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import bancoDeOrgao.BancoDeOrgaos;
import operacoesClinicas.CirurgiaBariatrica;
import operacoesClinicas.ConsultaClinica;
import operacoesClinicas.Procedimento;
import operacoesClinicas.RedesignacaoSexual;
import operacoesClinicas.TransplanteDeOrgaos;

public class DepartamentoClinico {
	
	private BancoDeOrgaos bancoDeOrgaos;
	private List<Prontuario> prontuarios;
	private List<Paciente> pacientes;
	
	
	public DepartamentoClinico(){
		this.pacientes = new ArrayList<Paciente>();
		this.prontuarios = new ArrayList<Prontuario>();
		this.bancoDeOrgaos = new BancoDeOrgaos();
		
	}

	public String getInfoPaciente(UUID id, String atributo) {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (Paciente paciente : pacientes) {
			if (id.equals(paciente.getId())) {
				switch (atributo) {
				case "Nome":
					return paciente.getNome();
				case "Data":
					return String.valueOf(paciente.getDataNascimento().format(formatador));
				case "Sexo":
					return paciente.getSexoBiologico();
				case "Genero":
					return paciente.getGenero();
				case "TipoSanguineo":
					return paciente.getTipoSanguineo();
				case "Peso":
					return String.valueOf(paciente.getPeso());
				case "Idade":
					LocalDate hoje = LocalDate.now();
					Period periodo = Period.between(paciente.getDataNascimento(), hoje);
					return String.valueOf(periodo.getYears());
				}
			}
		}
		return null;
	}
	
	public UUID cadastraPaciente(String nome, double peso, String data, String tipoSanguineo, String sexo, String genero){
		Paciente paciente = new Paciente(nome, peso, data, tipoSanguineo, sexo, genero);
		pacientes.add(paciente);
		prontuarios.add(paciente.getProntuario());
		ProntuarioComparator comparator = new ProntuarioComparator();
		Collections.sort(prontuarios, comparator );
		return paciente.getId();
	}
	
	public void realizaProcedimento(){
		
	}
	
	
	public void realizaTransplante(String orgao, String id, String medicamentos) throws Exception{
		if(pacientes.contains(id)){
			Procedimento transplante = new TransplanteDeOrgaos();
			Paciente paciente = getPaciente(id);
			Prontuario prontuarioPaciente = paciente.getProntuario();
			prontuarioPaciente.adicionaProcedimento(transplante);
			paciente.setTotalDeGastos(transplante.getValor());
		}else {
			throw new Exception("Paciente nao cadastrado.");
		}
		
	}
	
	public void realizaConsulta(String id, String medicamentos) throws Exception{
		if(pacientes.contains(id)){
			Procedimento consulta = new ConsultaClinica();
			Paciente paciente = getPaciente(id);
			Prontuario prontuarioPaciente = paciente.getProntuario();
			prontuarioPaciente.adicionaProcedimento(consulta);
			paciente.setTotalDeGastos(consulta.getValor());
		}else {
			throw new Exception("Paciente nao cadastrado.");
		}
	}
	
	public void realizaCirurgiaBariatrica(String id, String medicamentos) throws Exception{
		if(pacientes.contains(id)){
			Procedimento cirurgiaBariatrica = new CirurgiaBariatrica();
			Paciente paciente = getPaciente(id);
			double novoPeso = subtracaoDoPeso(paciente);
			paciente.setPeso(novoPeso);
			Prontuario prontuarioPaciente = paciente.getProntuario();
			prontuarioPaciente.adicionaProcedimento(cirurgiaBariatrica);
			paciente.setTotalDeGastos(cirurgiaBariatrica.getValor());
		}else {
			throw new Exception("Paciente nao cadastrado.");
		}
		
		
	}
	
	public void realizaRedesignacao(String id, String medicamentos) throws Exception{
		if(pacientes.contains(id)){
			Procedimento redesignacao = new RedesignacaoSexual();
			Paciente paciente = getPaciente(id);
			String generoDeMudanca = verificaGenero(paciente);
			paciente.setGenero(generoDeMudanca);
			Prontuario prontuarioPaciente = paciente.getProntuario();
			prontuarioPaciente.adicionaProcedimento(redesignacao);
			paciente.setTotalDeGastos(redesignacao.getValor());
		}else {
			throw new Exception("Paciente nao cadastrado.");
		}
		
	}
	
	public Paciente getPaciente(String id){
		for(Paciente paciente : pacientes){
			if(paciente.getId().equals(id)){
				return paciente;
			}
		}return null;
	}
	
	// encontrar uma solu��aaao
	public boolean verificaOrgao(String orgao) throws Exception{
		return bancoDeOrgaos.verificaOrgao(orgao);
		
	}
	
	public String verificaGenero(Paciente paciente){
		if(paciente.getGenero().equals("Masculino")){
			return "Feminino";
		}else{
			return "Masculino";
		}
	}
	
	
	public double subtracaoDoPeso(Paciente paciente){
		double pesoSubtraido = (paciente.getPeso()*15.0) / 100.0;
		return pesoSubtraido;
		
	}
	
}