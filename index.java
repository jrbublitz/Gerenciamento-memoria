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
				System.out.println("Favor digite 'iniciar' para começar");
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
				System.out.println("O sistema está se encerrando");
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
				System.out.println("iniciar - avisar a aplicação que serão enviadas instruções fis, vir e leitura e escrita, serve para configurar a aplicação");
				System.out.println("finalizar - encerrar a aplicação e remover arquivo criado");
				System.out.println("fis [bits] - alocar em memória principal a quantidade de espaço especificada pelo número de bits informado");
				System.out.println("vir [bits] [arquivo] - cria o arquivo especificado com o tamanho necessário para armazenar a memória do sistema");
				System.out.println("ler_b [endereço] - leitura de 1 byte no endereço especificado");
				System.out.println("ler_w [endereço] - leitura de 2 bytes no endereço especificado");
				System.out.println("ler_l [endereço] - leitura de 4 bytes no endereço especificado");
				System.out.println("ler_q [endereço] - leitura de 8 bytes no endereço especificado");
				System.out.println("escrever_b [dado] [endereço] - para escrita do dado de 1 byte no endereço especificado");
				System.out.println("escrever_w [dado] [endereço] - para escrita do dado de 2 bytes no endereço especificado");
				System.out.println("escrever_l [dado] [endereço] - para escrita do dado de 4 bytes no endereço especificado");
				System.out.println("escrever_q [dado] [endereço] - para escrita do dado de 8 bytes no endereço especificado");
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
			System.out.println("Comando inválido, favor digite o comando 'iniciar' para começar");
			return false;			
<<<<<<< HEAD
		}
=======
		}		
>>>>>>> Classes 29/06
	}
}
