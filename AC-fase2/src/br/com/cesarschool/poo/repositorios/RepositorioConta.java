package br.com.cesarschool.poo.repositorios;

import br.com.cesarschool.poo.entidades.Conta;

public class RepositorioConta {
	private static final int MAX = 1000;
	private int posicaoAtual = 0;
	private Conta[] repositorio = new Conta[MAX];
	
	private int buscarIndice(long numero) {
		for(int i = 0; i < MAX; i++) {
			if(repositorio[i] != null && repositorio[i].getNumero() == numero)
				return i;
		}
		return -1;
	}
	
	public boolean incluir(Conta conta) {
		if(buscarIndice(conta.getNumero()) != -1) {
			return false;
		}
		else if(posicaoAtual == MAX-1) {
			return false;
		}
		else {
			for(int i = 0; i < MAX; i++) {
				if(repositorio[i] == null) {
					repositorio[i] = conta;
					break;
				}
			}
			posicaoAtual++;
			return true;
		}
	}
	
	public boolean alterar(Conta conta) {
		int indice = buscarIndice(conta.getNumero());
		if(indice != -1) {
			repositorio[indice] = conta;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean excluir(long numero) {
		int indice = buscarIndice(numero);
		if(indice != -1) {
			repositorio[indice] = null;
			posicaoAtual--;
			return true;
		}
		else {
			return false;
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
