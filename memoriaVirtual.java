package main;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class memoriaVirtual {
	
	static RandomAccessFile memoriaVirtual;
	
	Scanner s = new Scanner(System.in);	
	String dados;
	String pos;

	public static void CriaArquivo() { //CRIA ARQUIVO COM TAMANHO NECESSARIO										
		try {				    	   //PARA ARMAZENAR NA MEMORIA
			memoriaVirtual = new RandomAccessFile("memoriaVirtual.txt", "rw");
			System.out.println("... Arquivo Criado com Sucesso ! ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void EscreverB(long bin, String valor) {  //ESCREVE 1 BYTE NA POSI��O ESPECIFICADA	
		long pos = 0;
		long pagina = controlador.somentePagina(bin);
		//FUN��O PARA DESCOBRIR ENDERE�AMENTO. int numero = Integer.parseInt(bin, 2);
		/*pos = Integer.toString(Integer.parseInt("ff", 16), 2);
		pos = pos.substring(0, 2);		
		pos = Integer.toString(Integer.parseInt(pos, 2));
		System.out.println(pos);
		
		
		try {			
			memoriaVirtual.seek(Integer.parseInt(pos)); //POSI��O			
			memoriaVirtual.writeBytes(dados);  //ESCREVE NO ARQUIVO
		} catch (IOException e) {
			e.printStackTrace();
		}		*/
	}
	
	public void LerB(long bin) { //LE 1 BITE NA POSI��O ESPECIFICADA
		try {			
			long pos = 0;
			long pagina = controlador.somentePagina(bin);
			//FUN��O PARA DESCOBRIR ENDERE�AMENTO. 
			//bin�rio para hexa: String hexa = Integer.toString(Integer.parseInt("1111", 2), 16);
			//hexadecimal para bin�rio: String bin = Integer.toString(Integer.parseInt("ff", 16), 2);
			int tamanhodapagina = 100;
			int local = encontrarpagina(pagina);
			long endereco = local+pos; //pos tera de ser convertido ainda
			
			memoriaVirtual.seek(endereco); 		   //POSIC�O
			System.out.println(memoriaVirtual.read()); //LER 8 bits dO ARQUIVO
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int encontrarpagina(long pagina) {
		//encontrar aonde est� a pagina em bin
		return 0;
	}

}
	