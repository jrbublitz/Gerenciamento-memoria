package main;

import java.util.Scanner;

public class index {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String estado = "aguardo";
		
		String parametro1 = "";
		String parametro2 = "";		
		
		while (!estado.contentEquals("finalizar")){
			if (!estado.contentEquals("iniciar")){
				System.out.println("Favor digite 'iniciar' para começar");
			}
			
			String input = s.nextLine();
			
			String[] inputVetor = input.split(" ");
			
			String comando = inputVetor[0]; 
			
			if(inputVetor.length > 1)
				parametro1 = inputVetor[1]; 
			if(inputVetor.length == 3)
				parametro2 = inputVetor[2]; 
			
			estado = Comandos(comando, parametro1, parametro2, estado);
		}
	}
	
	public static String Comandos(String comando, String parametro1, String parametro2, String estado) {	
		
		switch (comando){
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
				{					
					memoriaVirtual.CriaArquivo(parametro1, parametro2);
				}
				break;
							
			case "ler_b":
				if (ProgramaIniciado(estado))
				{
					controlador.LerB(parametro1);					
				}
				break;
				
			case "ler_w":
				if (ProgramaIniciado(estado))
				{					
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
				{	
					controlador.EscreverB(parametro1, parametro2);					
				}				
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
		}		
	}
}
