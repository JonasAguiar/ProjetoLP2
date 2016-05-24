package SOOS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


import clinico.Prontuario;

public class GerenciadorDeArquivos {
	
	File diretorioFichas;
	File diretorioSistema;
	
	public GerenciadorDeArquivos(){
		this.diretorioFichas = new File("fichas_pacientes/");
		this.diretorioSistema = new File("system_data/");
		diretorioFichas.mkdir();
		diretorioSistema.mkdir();
	}
	
	
	public void exportaFichaPaciente(Prontuario prontuario) throws IOException{
		LocalDate dataDeHoje = LocalDate.now();
		SimpleDateFormat formatData = new SimpleDateFormat("yyyy_MM_dd");
		String dataFormatada = formatData.format(dataDeHoje);
		
		File arquivoPaciente = new File (diretorioFichas, prontuario.getNomePaciente() +"_" + dataFormatada + "_.txt");
		
		BufferedWriter buffer = new BufferedWriter(new FileWriter(arquivoPaciente));
		buffer.write(prontuario.toString());
		buffer.close();
		
	}
	
	
	public void salvaSistema(){
		diretorioSistema.
	}
	
	
	
	
	
	
	
	
	

}
