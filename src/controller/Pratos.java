package controller;

import java.util.concurrent.Semaphore;

public class Pratos extends Thread {
	private String refeicao;
	private Semaphore pratos;
	private Semaphore entrega;

	public void Prato(Semaphore _pratos, Semaphore _entrega) {
		pratos = _pratos;
		entrega = _entrega;
	}

	public void run() {
		switch ( ( (int) this.getId() ) % 2) {
		case 0:
			refeicao = "Lasanha a Bolonhesa";
			try {
				pratos.acquire();
				cozinharLasanha();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				pratos.release();
			}
			entregarPrato();
			break;
		case 1:
			refeicao = "Sopa de Cebola";
			try {
				pratos.acquire();
				cozinharSopa();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				pratos.release();
			}
			entregarPrato();
			break;
		}
	}

	private void cozinharSopa() {
		int tempopreparo = (int) ((Math.random() * 301) + 500);
		int pronto = 0;
		while (pronto < tempopreparo) {
			try {
				if (pronto + 100 <= tempopreparo)
					pronto += 100;
				else
					pronto += tempopreparo - pronto;
				System.out.println("O prato " + refeicao + " está " + (pronto * 100) / tempopreparo + "% concluido!");
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void cozinharLasanha() {
		// 600ms - 1200ms = 0,6s a 1,2s
		int tempopreparo = (int) ((Math.random() * 601) + 600);
		int pronto = 0;
		while (pronto < tempopreparo) {
			try {
				if (pronto + 100 <= tempopreparo)
					pronto += 100;
				else
					pronto += tempopreparo - pronto;
				System.out.println("O prato " + refeicao + " está " + (pronto * 100) / tempopreparo + "% concluido!");
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void entregarPrato() {
		try {
			System.out.println("O prato " + refeicao + " está sendo entregue");
			entrega.acquire();
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			entrega.release();
			System.out.println("PRONTO! O prato:  " + refeicao + " está entregue!");
		}
	}
}
