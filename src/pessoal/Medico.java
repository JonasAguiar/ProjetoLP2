package pessoal;

import java.io.Serializable;

public class Medico implements Cargo, Serializable{ 
	
	public Medico() {
		super();
	}

	@Override
	public String toString() {
		return "Medico";
	}
}
