package br.com.cesarschool.poo.telas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.cesarschool.poo.entidades.Conta;
import br.com.cesarschool.poo.mediators.ContaMediator;

public class TelaConta {
	private static final Scanner ENTRADA = new Scanner(System.in);
	private ContaMediator contaMediator = new ContaMediator();
	LocalDate dataAtual = LocalDate.now();
	
	public void executaTela() {
		for(;;) {
			System.out.println("[1] Incluir\n[2] Alterar\n[3] Encerrar\n[4] Bloquear\n[5] "
		+ "Desbloquear\n[6] Excluir\n[7] Buscar\n[8] Creditar\n[9] Debitar\n[-1] Sair");
			System.out.println(">");
			int opcao = ENTRADA.nextInt();
			if(opcao == 1)
				doIncluir();
			else if(opcao == 2) {
				System.out.print("Digite o numero da conta que deseja alterar: ");
				long numero = ENTRADA.nextLong();
				alterarConta(numero);
			}
			else if(opcao == 3) {
				System.out.print("Digite o numero da conta que deseja encerrar: ");
				long numero = ENTRADA.nextLong();
				alterarStatus(numero, 2);
			}
			else if(opcao == 4) {
				System.out.print("Digite o numero da conta que deseja bloquear: ");
				long numero = ENTRADA.nextLong();
				alterarStatus(numero, 3);
			}
			else if(opcao == 5) {
				System.out.print("Digite o numero da conta que deseja desbloquear: ");
				long numero = ENTRADA.nextLong();
				alterarStatus(numero, 1);
			}
			else if(opcao == 6) {
				System.out.print("Digite o numero da conta que deseja excluir: ");
				long numero = ENTRADA.nextLong();
				contaMediator.excluir(numero);
			}
			else if(opcao == 7) {
				System.out.print("Digite o numero da conta que deseja buscar: ");
				long numero = ENTRADA.nextLong();
				buscarConta(numero);
			}
			else if(opcao == 8) {
				System.out.print("Digite o numero da conta que deseja creditar: ");
				long numero = ENTRADA.nextLong();
				System.out.print("Digite o valor: ");
				long valor = ENTRADA.nextLong();
				contaMediator.creditar(valor, contaMediator.buscar(numero));
			}
			else if(opcao == 9) {
				System.out.print("Digite o numero da conta que deseja debitar: ");
				long numero = ENTRADA.nextLong();
				System.out.print("Digite o valor: ");
				long valor = ENTRADA.nextLong();
				contaMediator.creditar(valor, contaMediator.buscar(numero));
			}
			else if(opcao == -1) {
				System.out.println("Programa encerrado");
				System.exit(0);
			}
			else
				System.out.println("Opera??o inv?lida");
		}
	}
	private void doIncluir() {
		Conta conta = dadosConta();
		if(contaMediator.incluir(conta))
			System.out.println("Conta incluida com sucesso!");
		else
			System.out.println("N?o foi poss?vel concluir a inclus?o");
	}
	
	public Conta dadosConta() {
		System.out.print("Digite o numero: ");
		long numero = ENTRADA.nextLong();
		System.out.print("Digite o status: ");
		int status = ENTRADA.nextInt();
		System.out.print("Digite a data de abertura: ");
		String str = ENTRADA.next();
		DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dataAbertura = LocalDate.parse(str, frm);
		Conta conta = new Conta(numero, status, dataAbertura);
		return conta;
	}
	
	public void alterarConta(long numero) {
		Conta conta = contaMediator.buscar(numero);
		System.out.print("Digite a nova data de abertura: ");
		String str = ENTRADA.next();
		DateTimeFormatter frm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dataAbertura = LocalDate.parse(str, frm);
		conta.setDataAbertura(dataAbertura);
		if(contaMediator.alterar(conta))
			System.out.println("Conta alterada com sucesso!");
		else
			System.out.println("N?o foi poss?vel concluir a altera??o");
	}
	
	public void alterarStatus(long numero, int status) {
		Conta conta = contaMediator.buscar(numero);
		if(status == 1) {
			if(conta.getStatus() == 1 || conta.getStatus() == 2)
				System.out.println("N?o foi poss?vel desbloquear a conta");
			else {
				conta.setStatus(status);
				if(contaMediator.alterar(conta))
					System.out.println("Conta desbloqueada com sucesso!");
				else
					System.out.println("N?o foi poss?vel desbloquear a conta");
			}
		}
		
		else if(status == 2) {
			if(conta.getStatus() == 2)
				System.out.println("N?o foi poss?vel encerrar a conta");
			else {
				conta.setStatus(status);
				if(contaMediator.alterar(conta))
					System.out.println("Conta encerrada com sucesso!");
				else
					System.out.println("N?o foi poss?vel encerrar a conta");
			}
		}
		
		else if(status == 3) {
			if(conta.getStatus() == 2 || conta.getStatus() == 3)
				System.out.println("N?o foi poss?vel bloquear a conta");
			else {
				conta.setStatus(status);
				if(contaMediator.alterar(conta))
					System.out.println("Conta bloqueada com sucesso!");
				else
					System.out.println("N?o foi poss?vel bloquear a conta");
			}
		}
	}
	
	public void buscarConta(long numero) {
		Conta conta = contaMediator.buscar(numero);
		if(conta != null) {
			System.out.println("N?mero: " + conta.getNumero() + "\nStatus: " + conta.getStatus() 
			+ "\nData Abertura: " + conta.getDataAbertura() + "\nSaldo: " + conta.getSaldo());
			conta.calcularScore();
		}
		else
			System.out.println("Conta n?o existente");
	}
}
