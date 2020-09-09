package main;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Paginacao {

	int tamPagina = 0;
	int nPagVirtuais = 0;
	int nPagReais = 0;
	PaginaVirtual paginasVirtuais[] = null;
	PaginaFisica memoriaFisica[] = null;
	RandomAccessFile memoriaVirtual = null;

	public Paginacao(int tamPagina, int nPagVirtuais, int nPagReais, String nomeArquivo) {
		this.tamPagina = tamPagina;
		this.nPagVirtuais = nPagVirtuais;
		this.nPagReais = nPagReais;
		paginasVirtuais = new PaginaVirtual[nPagVirtuais];
		memoriaFisica = new PaginaFisica[nPagReais];

		for (int i = 0; i < nPagReais; i++) {
			memoriaFisica[i] = new PaginaFisica(tamPagina);
			memoriaFisica[i].moldura = i;
		}
		
		for (int i = 0; i < nPagVirtuais; i++) {
			paginasVirtuais[i] = new PaginaVirtual();
		}
		
		criaArquivo(tamPagina * nPagVirtuais, nomeArquivo);

		for (int i = 0; i < nPagReais; i++) {
			carregarPaginaVirtual(i, i);
		}
	}

	public void criaArquivo(long tam, String nomeArquivo) { 					// CRIA ARQUIVO DE ACORDO COM NOME INSERIDO E
		try { 																	// E IMPLEMENTADO FOR PARA PREENCHER COM 0's DE
			memoriaVirtual = new RandomAccessFile(nomeArquivo + ".bin", "rw");  // ACORDO COM TAMANHO INSERIDO
			for (long i = 0; i < tam; i++) {
				memoriaVirtual.writeByte(48);
			}
			System.out.println("... Arquivo Criado com Sucesso ! ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void carregarPaginaVirtual(int nPagReal, int nPagVirtual) { 			// Carrega pagina virtual na pagina real
		try {																	// CARREGA 
			memoriaVirtual.seek(nPagVirtual * tamPagina);
			memoriaVirtual.read(memoriaFisica[nPagReal].pagina, 0, tamPagina);
			paginasVirtuais[nPagVirtual].moldura = nPagReal;
			paginasVirtuais[nPagVirtual].sujo = false;
			paginasVirtuais[nPagVirtual].disponivel = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Salvar no Arquivo virtual pagina real
	public void salvarPaginaVirtual(int nPagReal, int nPagVirtual) {
		if (paginasVirtuais[nPagVirtual].sujo) {
			try {
				memoriaVirtual.seek(nPagVirtual * tamPagina);
				memoriaVirtual.write(memoriaFisica[nPagReal].pagina, 0, tamPagina);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		paginasVirtuais[nPagVirtual].moldura = -1;
		paginasVirtuais[nPagVirtual].sujo = false;
		paginasVirtuais[nPagVirtual].disponivel = false;
	}

	public void EscreverB(String dado, String bin, int QtdByte) {
		// String bin = String.valueOf(Long.toString(Long.parseLong(enderecoHex, 16),
		// 2));//hex para bin	
		String dado2 = "";
		String dado4 = "";
		String dado8 = "";
		
		if(QtdByte >= 1) {
			 dado = dado.split("-")[0];					
		}else if(QtdByte >= 2) {
			 dado2 = dado.split("-")[1];			
		}else if(QtdByte >= 4){			
			 dado4 = dado.split("-")[2];			 			
		}else if(QtdByte >= 8) {
			 dado8 = dado.split("-")[3];
		}

		int moldura = Integer.valueOf(String.valueOf(bin).substring(0, 3));
		String endereco = String.valueOf(bin).substring(3, 12);

		// tenta encontrar na memoria física
		for (int i = 0; i < nPagReais; i++) {
			if (memoriaFisica[i].moldura == moldura) {
				if(QtdByte >= 1) {
					memoriaFisica[i].pagina[Integer.parseInt(endereco, 2)] = Byte.valueOf(dado);	
				} else if(QtdByte >= 2) {
					memoriaFisica[i].pagina[Integer.parseInt(endereco+1, 2)] = Byte.valueOf(dado2);
				}else if(QtdByte >= 4){
					memoriaFisica[i].pagina[Integer.parseInt(endereco+2, 2)] = Byte.valueOf(dado4);
				}else if(QtdByte >= 8){
					memoriaFisica[i].pagina[Integer.parseInt(endereco+3, 2)] = Byte.valueOf(dado8);
				}				
				return;
			}
		}
		// se nao estiver a moldura na fisica, tenta escrever na virtual
		try {
			for (int i = 0; i < paginasVirtuais.length; i++) {
				if (paginasVirtuais[i].moldura == moldura) {
					memoriaVirtual.seek(i * tamPagina);
					if(QtdByte >= 1) {
						memoriaVirtual.writeByte(Integer.valueOf(dado));
					}else if(QtdByte >= 2) {
						memoriaVirtual.writeByte(Integer.valueOf(dado2));
					}else if(QtdByte >= 4) {
						memoriaVirtual.writeByte(Integer.valueOf(dado4));
					}else if(QtdByte >= 8) {
						memoriaVirtual.writeByte(Integer.valueOf(dado8));
					}					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// se nao tiver na virtual criar na primeira posição disponivel virtual
		try {
			for (int i = 0; i < paginasVirtuais.length; i++) {
				if (paginasVirtuais[i].disponivel) {
					memoriaVirtual.seek(i * tamPagina + Integer.parseInt(endereco, 2));
					if(QtdByte >= 1) {
						memoriaVirtual.writeByte(Integer.valueOf(dado));
					}else if(QtdByte >= 2) {
						memoriaVirtual.writeByte(Integer.valueOf(dado2));
					}else if(QtdByte >= 4) {
						memoriaVirtual.writeByte(Integer.valueOf(dado4));
					}else if(QtdByte >= 8) {
						memoriaVirtual.writeByte(Integer.valueOf(dado8));
					}
					
					memoriaVirtual.seek(0);					

					paginasVirtuais[i].disponivel = false;

					int emUso = 0;
					for (int j = 0; j < paginasVirtuais.length; j++) {
						if (!paginasVirtuais[j].disponivel)
							emUso++;
					}
					paginasVirtuais[i].moldura = emUso + nPagReais;
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//		public void EscreverW(String dado, String bin) {
//		// String bin = String.valueOf(Long.toString(Long.parseLong(enderecoHex, 16),
//		// 2));//hex para bin
//		
//				String dado2 = dado.split("-")[0];
//				dado = dado.split("-")[1];
//				
//				int moldura = Integer.valueOf(String.valueOf(bin).substring(0, 3));
//				String endereco = String.valueOf(bin).substring(3, 12);
//
//				// tenta encontrar na memoria física
//				for (int i = 0; i < nPagReais; i++) {
//					if (memoriaFisica[i].moldura == moldura) {
//						memoriaFisica[i].pagina[Integer.parseInt(endereco, 2)] = Byte.valueOf(dado);
//						memoriaFisica[i].pagina[Integer.parseInt(endereco+1, 2)] = Byte.valueOf(dado2);
//						return;
//					}
//				}
//				// se nao estiver a moldura na fisica, tenta escrever na virtual
//				try {
//					for (int i = 0; i < paginasVirtuais.length; i++) {
//						if (paginasVirtuais[i].moldura == moldura) {
//							memoriaVirtual.seek(i * tamPagina);
//							memoriaVirtual.writeByte(Integer.valueOf(dado));
//							memoriaVirtual.writeByte(Integer.valueOf(dado2));
//						}
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//				// se nao tiver na virtual criar na primeira posição disponivel virtual
//				try {
//					for (int i = 0; i < paginasVirtuais.length; i++) {
//						if (paginasVirtuais[i].disponivel) {
//							memoriaVirtual.seek(i * tamPagina + Integer.parseInt(endereco, 2));
//							memoriaVirtual.writeByte(Integer.valueOf(dado));
//							memoriaVirtual.writeByte(Integer.valueOf(dado2));
//							memoriaVirtual.seek(0);					
//
//							paginasVirtuais[i].disponivel = false;
//
//							int emUso = 0;
//							for (int j = 0; j < paginasVirtuais.length; j++) {
//								if (!paginasVirtuais[j].disponivel)
//									emUso++;
//							}
//
//							paginasVirtuais[i].moldura = emUso + nPagReais;
//
//							return;
//						}
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//	}
	
	public void LerB(String bin, int QntByte) {
		// String bin = String.valueOf(Long.toString(Long.parseLong(enderecoHex, 16),
		// 2));//hex para bin

		int moldura = Integer.valueOf(String.valueOf(bin).substring(0, 3));
		String endereco = String.valueOf(bin).substring(3, 12);
		
		//memoriaFisica.procurarMoldura();
		
		// procurar moldura na fisica
		for (int i = 0; i < nPagReais; i++) {
			if (memoriaFisica[i].moldura == moldura) {
				System.out.println(memoriaFisica[i].pagina[Integer.parseInt(endereco, 2)]);
				return;
			}
		}

		// se nao procurar moldura na virtual
		for (int i = 0; i < paginasVirtuais.length; i++) {
			// se achar, salvar na fisica(random)
			if (paginasVirtuais[i].moldura == moldura) {
				// valor random para salvar na fisica
				int random = (int) (Math.random() * nPagReais);
				// seta a pagina como disponível
				paginasVirtuais[i].disponivel = true;
				int emUso = 0;
				// achar pagina virtual para salvar o valor da pagina de moldura random
				for (int j = 0; j < paginasVirtuais.length; j++) {
					if (paginasVirtuais[j].disponivel) {
						salvarPaginaVirtual(random, j);
						// carregarPaginaVirtual(i, random)
						carregarPaginaVirtual(i, random);						
						if(QntByte >= 1) {
							String binario = Integer.toBinaryString(memoriaFisica[random].pagina[Integer.parseInt(endereco, 2)]);
							System.out.println(binario);
						}else if(QntByte >= 2){
							String binario2 = Integer.toBinaryString(memoriaFisica[random].pagina[Integer.parseInt(endereco+2, 2)]);
							System.out.println(binario2);
						}else if(QntByte >= 4){
							String binario4 = Integer.toBinaryString(memoriaFisica[random].pagina[Integer.parseInt(endereco+4, 2)]);
							System.out.println(binario4);
						}else if(QntByte >= 8){
							String binario8 = Integer.toBinaryString(memoriaFisica[random].pagina[Integer.parseInt(endereco+8, 2)]);
							System.out.println(binario8);
						}
						return;						
					}
				}
				System.out.println("Não há disponibilidade...");
				return;
			}
		}

		// se nao estoura
		System.out.println("não encontrado!");
	}
	
	/*
	 * public void testar() { for(int pos = 0; pos < tamPagina; pos++) {
	 * memoriaFisica[pos] = (byte)(pos%127); } 0/10, resultado = 0, resto = 0 1/10,
	 * resultado = 0, resto = 1 2/10, resultado = 0, resto = 2 9/10, resultado = 0,
	 * resto = 9 10/10, resultado = 1, resto = 0
	 * 
	 * for(int pos = 0; pos < tamPagina; pos++) { System.out.println("POS: " + pos +
	 * ", VALOR: " + (int) memoriaFisica[pos]); }
	 * 
	 * salvarPaginaVirtual(0, 2);
	 * 
	 * for(int pos = 0; pos < tamPagina; pos++) { memoriaFisica[pos] = 0; }
	 * carregarPaginaVirtual(0, 2); System.out.println("LIDO: "); for(int pos = 0;
	 * pos < tamPagina; pos++) { System.out.println("POS: " + pos + ", VALOR: " +
	 * (int) memoriaFisica[pos]); } }
	 * 
	 * 
	 * public void acessar(int pos) { int nPagVirtual = pos/tamPagina; //POS >>
	 * BITS(TAMPAGS) System.out.println("PAG: "+ nPagVirtual +" - DISPONIVEL: " +
	 * paginasVirtuais[nPagVirtual].disponivel);
	 * 
	 * if(!paginasVirtuais[nPagVirtual].disponivel) { //Escolher Pagina carregada e
	 * salvar int escolhida = 0; for(escolhida = 0; escolhida < nPagVirtual;
	 * escolhida++) { if(paginasVirtuais[escolhida].disponivel) { break; } }
	 * 
	 * int molduraEscolhida = paginasVirtuais[escolhida].moldura;
	 * salvarPaginaVirtual(molduraEscolhida, escolhida);
	 * 
	 * //Carregar a pagina para a pagina escolhida
	 * carregarPaginaVirtual(molduraEscolhida, nPagVirtual); } }
	 * 
	 * public void testarDisponivel(int pos) { int nPagVirtual = pos/tamPagina;
	 * System.out.println("PAG: "+ nPagVirtual +" - DISPONIVEL: " +
	 * paginasVirtuais[nPagVirtual].disponivel);
	 * 
	 * }
	 */

}
