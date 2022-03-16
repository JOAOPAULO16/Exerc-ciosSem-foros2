package view;
import java.util.Random;
import controller.Pratos;

public class Principal {
	public static void main(String[] args) {
		int qtd_pratos = (int) ((Math.random() * 10) + 5);

		for (int i = 0; i < qtd_pratos; i++) {
			for(Prato pratos : Pratos)
				pratos.novoPrato();			
		}
	}
}