package SOOS;

import easyaccept.EasyAccept;

public class Façade {

	public static void main(String[] args) {
	    args = new String[] {"SOOS.Facade", "resources/usecase_1.txt", "resources/usecase_2.txt"
	    		, "resouces/usecase_3.txt", "resources/usecase_4.txt"}; //separe cada script de teste por virgula.
	    EasyAccept.main(args);
	}
}
