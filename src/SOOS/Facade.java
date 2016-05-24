package SOOS;

import easyaccept.EasyAccept;

public class Facade {

	public static void main(String[] args) {
	    args = new String[] {"SOOS.FacadeHospital", "resources/usecase_1.txt", "resources/usecase_2.txt"
	    		, "resources/usecase_3.txt", "resources/usecase_4.txt", "resources/usecase_5.txt", "resources/usecase_6.txt", "resources/usecase_7.txt" }; //separe cada script de teste por virgula.
	    EasyAccept.main(args);
	    
	}
	
	
	
	
	
	
	
}
