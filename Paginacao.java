package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Paginacao {
	
	int tamPagina = 0;
	int nPagVirtuais = 0;
	int nPagReais = 0;
	PaginaVirtual paginasVirtuais [] = null;
	byte memoriaFisica [] = null;
	RandomAccessFile memoriaVirtual = null;
	
	public Paginacao(int tamPagina, int nPagVirtuais, int nPagReais) {
		this.tamPagina = tamPagina;
		this.nPagVirtuais = nPagVirtuais;
		this.nPagReais = nPagReais;
		paginasVirtuais = new PaginaVirtual[nPagVirtuais];
		memoriaFisica = new byte[nPagReais*tamPagina]; 
				
		for(int i = 0; i < nPagVirtuais; i++) {
			paginasVirtuais[i] = new PaginaVirtual();
		}	
		criaArquivo(tamPagina*nPagVirtuais, "MemoriaVirtual.bin");
		
		for(int i = 0; i < nPagReais; i++) {
			carregarPaginaVirtual(i, i);			
		}			
	}
	
	public void criaArquivo(long tam, String nomeArquivo) {					//CRIA ARQUIVO DE ACORDO COM NOME INSERIDO E		
		try {																//E IMPLEMENTADO FOR PARA PREENCHER COM 0's DE	    	   
			memoriaVirtual = new RandomAccessFile(nomeArquivo+".bin", "rw");//ACORDO COM TAMANHO INSERIDO			
			for(long i = 0; i < tam; i++) {
				memoriaVirtual.writeByte(48); 
			}			
			System.out.println("... Arquivo Criado com Sucesso ! ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Carrega pagina virtual na pagina real
	public void carregarPaginaVirtual(int nPagReal, int nPagVirtual) {
		try {
			memoriaVirtual.seek(nPagVirtual*tamPagina);
			memoriaVirtual.read(memoriaFisica, nPagReal*tamPagina, tamPagina);
			paginasVirtuais[nPagVirtual].moldura = nPagReal;
			paginasVirtuais[nPagVirtual].sujo = false;
			paginasVirtuais[nPagVirtual].disponivel = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Salvar no Arquivo virtual pagina real
	public void salvarPaginaVirtual(int nPagReal, int nPagVirtual) {
		if(paginasVirtuais[nPagVirtual].sujo) {
			try {
				memoriaVirtual.seek(nPagVirtual*tamPagina);
				memoriaVirtual.write(memoriaFisica, nPagReal*tamPagina, tamPagina);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		paginasVirtuais[nPagVirtual].moldura = -1;
		paginasVirtuais[nPagVirtual].sujo = false;
		paginasVirtuais[nPagVirtual].disponivel = false;
	}
	
	public void testar() {
		for(int pos = 0; pos < tamPagina; pos++) {
			memoriaFisica[pos] = (byte)(pos%127);			
		}
		/*
		 * 0/10, resultado = 0, resto = 0
		1/10, resultado = 0, resto = 1
		2/10, resultado = 0, resto = 2
		9/10, resultado = 0, resto = 9
		10/10, resultado = 1, resto = 0
		*/
		for(int pos = 0; pos < tamPagina; pos++) {	
			System.out.println("POS: " + pos + ", VALOR: " + (int) memoriaFisica[pos]);
		}
		
		salvarPaginaVirtual(0, 2);
		
		for(int pos = 0; pos < tamPagina; pos++) {
			memoriaFisica[pos] = 0;			
		}		
		carregarPaginaVirtual(0, 2);
		System.out.println("LIDO: ");		
		for(int pos = 0; pos < tamPagina; pos++) {	
			System.out.println("POS: " + pos + ", VALOR: " + (int) memoriaFisica[pos]);
		}
	}
	
	public void acessar(int pos) {
		int nPagVirtual = pos/tamPagina; //POS >> BITS(TAMPAGS)
		System.out.println("PAG: "+ nPagVirtual +" - DISPONIVEL: " + paginasVirtuais[nPagVirtual].disponivel);
		
		if(!paginasVirtuais[nPagVirtual].disponivel) {
			//Escolher Pagina carregada e salvar
			int escolhida = 0;
			for(escolhida = 0; escolhida < nPagVirtual; escolhida++) {
				if(paginasVirtuais[escolhida].disponivel) {
					break;
				}
			}
			
			int molduraEscolhida = paginasVirtuais[escolhida].moldura;
			salvarPaginaVirtual(molduraEscolhida, escolhida);
			
			//Carregar a pagina para a pagina escolhida
			carregarPaginaVirtual(molduraEscolhida, nPagVirtual);
		}
	}
	
	public void testarDisponivel(int pos) {
		int nPagVirtual = pos/tamPagina;
		System.out.println("PAG: "+ nPagVirtual +" - DISPONIVEL: " + paginasVirtuais[nPagVirtual].disponivel);
		
	}
}
