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
	
	public void EscreverB() {  //ESCREVE 1 BYTE NA POSIÇÃO ESPECIFICADA
		System.out.print("//-: ");
		dados = s.nextLine();		
		 
		//FUNÇÃO PARA DESCOBRIR ENDEREÇAMENTO. int numero = Integer.parseInt(bin, 2);
		pos = Integer.toString(Integer.parseInt("ff", 16), 2);
		pos = pos.substring(0, 2);		
		pos = Integer.toString(Integer.parseInt(pos, 2));
		System.out.println(pos);
		
		
		try {			
			memoriaVirtual.seek(Integer.parseInt(pos)); //POSIÇÃO			
			memoriaVirtual.writeBytes(dados);  //ESCREVE NO ARQUIVO
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void LerB() { //LE 1 BITE NA POSIÇÃO ESPECIFICADA
		try {
			System.out.print("//-: ");
			pos = s.nextLine();
			
			
			//FUNÇÃO PARA DESCOBRIR ENDEREÇAMENTO. 
			//binário para hexa: String hexa = Integer.toString(Integer.parseInt("1111", 2), 16);
			//hexadecimal para binário: String bin = Integer.toString(Integer.parseInt("ff", 16), 2);
			
			memoriaVirtual.seek(pos.length()); 		   //POSICÃO
			System.out.println(memoriaVirtual.read()); //LER O ARQUIVO
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	