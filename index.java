package main;

import java.util.Scanner;

public class index {
<<<<<<< HEAD

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String estado = "aguardo";
		while (!estado.contentEquals("finalizar"))
		{
			if (!estado.contentEquals("iniciar"))
			{
=======
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String estado = "aguardo";
		
		while (!estado.contentEquals("finalizar")){
			if (!estado.contentEquals("iniciar")){
>>>>>>> Classes 29/06
				System.out.println("Favor digite 'iniciar' para come�ar");
			}
			String comando = s.nextLine();
			estado = Comandos(comando, estado);
		}
	}
	
	public static String Comandos(String comando, String estado) {
<<<<<<< HEAD
		switch (comando)
		{
=======
		
		memoriaVirtual memoriaVirtual = new memoriaVirtual();
		
		switch (comando){
>>>>>>> Classes 29/06
			case "iniciar":
				estado = comando;
				System.out.println("O sistema foi inicializado");
				break;
				
			case "finalizar":
				estado = comando;
				System.out.println("O sistema est� se encerrando");
				break;
				
			case "fis":
				if (ProgramaIniciado(estado))
				{
				}
				break;
				
			case "vir":
				if (ProgramaIniciado(estado))
<<<<<<< HEAD
				{
					System.out.println("teste");
=======
				{					
					memoriaVirtual.CriaArquivo();
>>>>>>> Classes 29/06
				}
				break;
							
			case "ler_b":
				if (ProgramaIniciado(estado))
				{
<<<<<<< HEAD
=======
					memoriaVirtual.LerB();
>>>>>>> Classes 29/06
				}
				break;
				
			case "ler_w":
				if (ProgramaIniciado(estado))
<<<<<<< HEAD
				{
=======
				{					
>>>>>>> Classes 29/06
				}
				break;
				
			case "ler_l ":
				if (ProgramaIniciado(estado))
				{
				}
				break;
				
			case "ler_q":
				if (ProgramaIniciado(estado))
				{
				}
				break;
				
				
			case "escrever_b":
				if (ProgramaIniciado(estado))
<<<<<<< HEAD
				{
				}
=======
				{	
					memoriaVirtual.EscreverB();
				}				
>>>>>>> Classes 29/06
				break;
				
			case "escrever_w":
				if (ProgramaIniciado(estado))
				{
				}
				break;
				
			case "escrever_l":
				if (ProgramaIniciado(estado))
				{
				}
				break;
				
			case "escrever_q":
				if (ProgramaIniciado(estado))
				{
				}
				break;
							
			case "help":
				System.out.println("iniciar - avisar a aplica��o que ser�o enviadas instru��es fis, vir e leitura e escrita, serve para configurar a aplica��o");
				System.out.println("finalizar - encerrar a aplica��o e remover arquivo criado");
				System.out.println("fis [bits] - alocar em mem�ria principal a quantidade de espa�o especificada pelo n�mero de bits informado");
				System.out.println("vir [bits] [arquivo] - cria o arquivo especificado com o tamanho necess�rio para armazenar a mem�ria do sistema");
				System.out.println("ler_b [endere�o] - leitura de 1 byte no endere�o especificado");
				System.out.println("ler_w [endere�o] - leitura de 2 bytes no endere�o especificado");
				System.out.println("ler_l [endere�o] - leitura de 4 bytes no endere�o especificado");
				System.out.println("ler_q [endere�o] - leitura de 8 bytes no endere�o especificado");
				System.out.println("escrever_b [dado] [endere�o] - para escrita do dado de 1 byte no endere�o especificado");
				System.out.println("escrever_w [dado] [endere�o] - para escrita do dado de 2 bytes no endere�o especificado");
				System.out.println("escrever_l [dado] [endere�o] - para escrita do dado de 4 bytes no endere�o especificado");
				System.out.println("escrever_q [dado] [endere�o] - para escrita do dado de 8 bytes no endere�o especificado");
				break;
				
			default:
				System.out.println("Comando incorreto, digite 'help' para lista de comandos");
				break;
		}
		
		return estado;
	}
	
	public static boolean ProgramaIniciado(String estado)
	{
		if (estado.contentEquals("iniciar"))
		{
			return true;			
		}
		else 
		{
			System.out.println("Comando inv�lido, favor digite o comando 'iniciar' para come�ar");
			return false;			
<<<<<<< HEAD
		}
=======
		}		
>>>>>>> Classes 29/06
	}
}
