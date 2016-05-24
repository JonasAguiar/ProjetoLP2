package pessoal;

import java.io.Serializable;
import java.time.LocalDate;

public class FactoryFuncionarios implements Serializable {
	
	
	public FactoryFuncionarios(){
		
	}
	
	/** Metodo que realiza a criação de funcionario a partir de atributos dados. Padrao Factory.
	 * @param cargo
	 * @param nome
	 * @param dataDeNascimento
	 * @return
	 */
	public Funcionario criaFuncionario(String cargo, String nome, String dataDeNascimento){
		
		switch (cargo) {
		case "diretor":
			return criaDiretor(nome, dataDeNascimento);
		case "medico":
			return criaMedico(nome, dataDeNascimento);
		case "tecnico":
			return criaTecnico(nome, dataDeNascimento);

		default:
			break;
		}
		return null;
	
		
	}
	
	/** Metodo que realiza a criacao de diretor.
	 * @param nome
	 * @param dataDeNascimento
	 * @return Funcionario
	 */
	private Funcionario criaDiretor(String nome, String dataDeNascimento){
		Funcionario diretor = new Funcionario(nome, dataDeNascimento);
		diretor.atribuiCargoDiretor();
		return diretor;
	}
	
	/** Metodo que realiza a criaçao de medico a partir de atributos.
	 * @param nome
	 * @param dataDeNascimento
	 * @return Funcionario
	 */
	private Funcionario criaMedico(String nome, String dataDeNascimento){
		Funcionario medico = new Funcionario(nome, dataDeNascimento);
		medico.atribuiCargoMedico();
		return medico;
	}
	
	/** Metodo que realiza a criacao de tecnico a partir de atributos.
	 * @param nome
	 * @param dataDeNascimento
	 * @return Funcionario
	 */
	private Funcionario criaTecnico(String nome, String dataDeNascimento){
		Funcionario tecnico = new Funcionario(nome, dataDeNascimento);
		tecnico.atribuiCargoTecnico();
		return tecnico;
	}
	
}
