package main;

public class controlador {
	/*	
	private static memoriaVirtual memoriaVirtual = new memoriaVirtual();
	private static memoriaFisica memoriaFisica = new memoriaFisica();

	public static void EscreverB(String hex, String valor) {
		long bin = Long.valueOf(HexToBin(hex));//hex para bin
		//separar 3 primeiros do bin para identificar a pagina
		int pagina = 001;// pagina é os 3 digitos da pagina
		if(memoriaFisica.existe(pagina));// procurar na fisica, senão procurara na virtual
		//escrever na fisica
		else
		memoriaVirtual.EscreverB(bin, valor);

	}
	*/
	/*
	public static String LerB(String hex) {
		 long bin = Long.valueOf(HexToBin(hex));//hex para bin
		//separar 3 primeiros do bin para identificar a pagina
		int pagina = 001;	
		if(memoriaFisica.existe(pagina));// procurar na fisica, senão procurara na virtual
		//ler na fisica
		else
		memoriaVirtual.LerB(bin);
		return ""; 		
	}
	
	public static String HexToBin(String hex) {		
		return Long.toString(Long.parseLong(hex, 16), 2);
	}

	public static long somentePagina(long bin) {
		return Long.valueOf(String.valueOf(bin).substring(0, 3));
	}
	*/
}
