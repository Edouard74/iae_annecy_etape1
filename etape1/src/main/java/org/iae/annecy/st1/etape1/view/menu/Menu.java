package org.iae.annecy.st1.etape1.view.menu;

import java.util.Scanner;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Menu {
	private Scanner sca = new Scanner(System.in);

	public Menu() {
	}

	public int menuGeneral() {
		ConsoleHelper.display("																		");
		ConsoleHelper.display("====================== MENU GENERAL : ===================");
		ConsoleHelper.display("                                                                     ");
		ConsoleHelper.display("1: Interface catalogue produits");
		ConsoleHelper.display("2: Fichier client");
		ConsoleHelper.display("3: Panier client");
		ConsoleHelper.display("4: Commande client");
		ConsoleHelper.display("5: Quitter");
		int m = sca.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + m);
		return m;
	}
}
