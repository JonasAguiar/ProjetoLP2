package bancoDeOrgao;

import java.io.Serializable;

public class Orgao implements Serializable {
	
	private String nome;
	private String tipo;
	
	public Orgao(String nome, String tipo) throws Exception{
		if(nome == null){
			throw new Exception("Nome do orgao nao pode ser vazio.");
		}if(nome.trim().equals("")){
			throw new Exception("Nome do orgao nao pode ser vazio");
		}if(tipo == null){
			throw new Exception("Tipo do orgao nao pode ser nulo");
		}if(tipo.trim().equals("")){
			throw new Exception("Tipo do orgao nao pode ser vazio");
		}
		this.setNome(nome);
		this.setTipo(tipo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orgao other = (Orgao) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	

}
