package br.com.cesarschool.poo.mediators;

import br.com.cesarschool.poo.entidades.Conta;

public class ContaMediador {
	public void creditar(double valor, Conta conta) {
		if(conta.getStatus() != 2) {
			if(valor > 0)
				conta.setSaldo(conta.getSaldo()+valor);
		}
	}
	public void debitar(double valor, Conta conta) {
		if(conta.getStatus() != 2) {
			if(valor > 0)
				conta.setSaldo(conta.getSaldo()-valor);
		}
	}
}
