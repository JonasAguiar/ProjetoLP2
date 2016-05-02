package SOOS;

import easyaccept.EasyAccept;

public class Façade {

	public static void main(String[] args) {
	    args = new String[] {"meu_pacote.Facade", "diretorio_testes/usecase_1.txt", "diretorio_testes/usecase_2.txt"}; //separe cada script de teste por virgula.
	    EasyAccept.main(args);
	}
}
