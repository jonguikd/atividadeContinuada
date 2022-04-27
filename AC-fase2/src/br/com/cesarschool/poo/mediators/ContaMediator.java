package br.com.cesarschool.poo.mediators;

import java.time.LocalDate;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.repositorios.RepositorioConta;

public class ContaMediator {
	LocalDate dataAtual = LocalDate.now();
	private RepositorioConta repositorioConta = new RepositorioConta();
	
	public boolean incluir(Conta conta) {
		if(validar(conta))
			return repositorioConta.incluir(conta);
		else
			return false;
	}
	
	public boolean alterar(Conta conta) {
		if(validar(conta))
			return repositorioConta.alterar(conta);
		else
			return false;
	}
	
	public boolean excluir(long numero) {
		return repositorioConta.excluir(numero);
	}
	
	public Conta buscar(long numero) {
		return repositorioConta.buscar(numero);
	}
	
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
	
	public boolean validar(Conta conta) {
		if(conta == null)
			return false;
		else if(conta.getNumero() <= 0)
			return false;
		else if(dataAtual.isBefore(conta.getDataAbertura()) || dataAtual.isEqual(conta.getDataAbertura()))
			return false;
		else
			return true;
	}
}
