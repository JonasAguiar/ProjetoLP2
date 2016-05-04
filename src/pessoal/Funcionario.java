package pessoal;

public class Funcionario {
	
	private String nome;
	private int matricula;
	private String senha;
	private Cargo cargo;
	private String dataDeNascimento;
	
	public Funcionario(String nome, String cargo, String dataDeNascimento){
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		if(cargo.equals("Diretor")){
			this.cargo = new Diretor();
		}else if(cargo.equals("Medico")){
			this.cargo = new Medico();
		}else if(cargo.equals("Tecnico")){
			this.cargo = new Tecnico();
		}
		
	}
	
	public Funcionario(){
		this.cargo = new Diretor();
		this.senha = "c041ebf8";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	
	
	
}
