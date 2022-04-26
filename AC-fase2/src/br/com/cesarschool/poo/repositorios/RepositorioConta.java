package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;

public class RepositorioConta {
	private static final int MAX = 1000;
	
	private Conta[] repositorio = new Conta[MAX];
	int posicaoAtual = 0;
	
	private int buscarIndice(long numero) {
		for(int i = 0; i < MAX; i++) {
			Conta conta = repositorio[i];
			if(conta != null && conta.getNumero() == numero) {
				return i;
			}
		}
		return -1;
	}
	
	public void incluir(Conta conta) {
		if(buscarIndice(conta.getNumero()) != -1)
			System.out.println("Conta já existente");
		else if(posicaoAtual < MAX-1) {
			for(int i = 0; i < MAX; i++) {
				if(repositorio[i] == null) {
					repositorio[i] = conta;
					break;
				}
			}
			posicaoAtual++;
		}
	}
	
	public void alterar(Conta conta) {
		int indice = buscarIndice(conta.getNumero());
		if(indice != -1)
			repositorio[indice] = conta;
	}
	
	public void excluir(long numero) {
		int indice = buscarIndice(numero);
		if(indice != -1) {
			repositorio[indice] = null;
			posicaoAtual--;
		}		
	}
	
	public Conta buscar(long numero) {
		int indice = buscarIndice(numero);
		if (indice != -1)
			return repositorio[indice];
		else
			return null;
	}
}
