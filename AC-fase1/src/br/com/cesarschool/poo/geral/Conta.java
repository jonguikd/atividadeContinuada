package br.com.cesarschool.poo.geral;
import java.time.LocalDate;

public class Conta {
	private long numero;
	private int status;
	private LocalDate dataAbertura;
	private double saldo;
	LocalDate dataAtual = LocalDate.now();
	
	public Conta(long numero, int status, LocalDate dataAbertura) {
		super();
		this.numero = numero;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.saldo = 0;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void creditar(double valor) {
		if(this.getStatus() != 2) {
			if(valor > 0)
				this.setSaldo(this.getSaldo()+valor);
		}
	}
	public void debitar(double valor) {
		if(this.getStatus() != 2) {
			if(valor > 0)
				this.setSaldo(this.getSaldo()-valor);
		}
	}
	public void calcularScore() {
		if(this.getStatus() != 3) {
			if(this.getStatus() == 2)
				System.out.println("Score: 0");
			else if(this.getStatus() == 1) {
				int tempoVida = dataAtual.getDayOfMonth() - dataAbertura.getDayOfMonth();
				double F = this.getSaldo()*3 + tempoVida*2;
				if(F < 5800)
					System.out.println("Score: 1");
				if(F >= 5800 && F <= 13000)
					System.out.println("Score: 2");
				if(F >= 13001 && F <= 39000)
					System.out.println("Score: 3");
				if(F > 39000)
					System.out.println("Score: 4");
			}
		}
	}
}
