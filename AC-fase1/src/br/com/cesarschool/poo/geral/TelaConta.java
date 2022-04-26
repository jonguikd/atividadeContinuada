package br.com.cesarschool.poo.geral;
import java.time.LocalDate;
import java.util.Scanner;

public class TelaConta {
	private static final Scanner ENTRADA = new Scanner(System.in);
	private RepositorioConta repositorioConta = new RepositorioConta();
	LocalDate dataAtual = LocalDate.now();
	
	public void executaTela() {
		for(;;) {
			System.out.println("[1] Incluir\n[2] Alterar\n[3] Encerrar\n[4] Bloquear\n[5] "
					+ "Desbloquear\n[6] Excluir\n[7] Buscar\n[8] Creditar\n[9] Debitar\n[-1] Sair");
			int opcao = ENTRADA.nextInt();
			if(opcao == 1)
				repositorioConta.incluir(dadosConta());
			else if(opcao == 2) {
				System.out.print("Digite o numero da conta que deseja alterar: ");
				long numero = ENTRADA.nextLong();
				alterarData(numero);
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
				repositorioConta.excluir(numero);
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
				repositorioConta.buscar(numero).creditar(valor);
			}
			else if(opcao == 9) {
				System.out.print("Digite o numero da conta que deseja debitar: ");
				long numero = ENTRADA.nextLong();
				System.out.print("Digite o valor: ");
				long valor = ENTRADA.nextLong();
				repositorioConta.buscar(numero).debitar(valor);
			}
			else if(opcao == -1) {
				System.out.println("Programa encerrado");
				System.exit(0);
			}
			else
				System.out.println("Operação inválida");
		}
	}
	
	public Conta dadosConta() {
		System.out.print("Digite o numero: ");
		long numero = ENTRADA.nextLong();
		System.out.print("Digite o status: ");
		int status = ENTRADA.nextInt();
		System.out.print("Digite a data de abertura: ");
		int dataAbertura = ENTRADA.nextInt();
		if(numero > 0 && dataAbertura <= dataAtual.getDayOfMonth())
			return new Conta(numero, status, dataAbertura);
		else {
			System.out.println("Não foi possível inserir a conta");
			return null;
		}
	}
	
	public void alterarData(long numero) {
		Conta conta = repositorioConta.buscar(numero);
		System.out.print("Digite a nova data de abertura: ");
		int dataAbertura = ENTRADA.nextInt();
		if(dataAbertura <= dataAtual.getDayOfMonth()) {
			conta.setDataAbertura(dataAbertura);
			repositorioConta.alterar(conta);
		}
		else
			System.out.println("Não foi possível alterar a data");
	}
	
	public void alterarStatus(long numero, int status) {
		Conta conta = repositorioConta.buscar(numero);
		if(status == 1) {
			if(conta.getStatus() == 1 || conta.getStatus() == 2)
				System.out.println("Não é possível desbloquear a conta");
			else
				conta.setStatus(status);
		}
		else if(status == 2) {
			if(conta.getStatus() == 2)
				System.out.println("Não é possível encerrar a conta");
			else
				conta.setStatus(status);
		}
		else if(status == 3) {
			if(conta.getStatus() == 2 || conta.getStatus() == 3)
				System.out.println("Não é possível bloquear a conta");
			else
				conta.setStatus(status);
		}
		repositorioConta.alterar(conta);
	}
	
	public void buscarConta(long numero) {
		Conta conta = repositorioConta.buscar(numero);
		if(conta != null) {
			System.out.println("Número: " + conta.getNumero() + "\nStatus: " + conta.getStatus() 
			+ "\nData Abertura: " + conta.getDataAbertura() + "\nSaldo: " + conta.getSaldo());
			conta.calcularScore();
		}
	}
}
