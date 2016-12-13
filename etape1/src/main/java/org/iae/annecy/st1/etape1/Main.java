/**
 * 
 */

package org.iae.annecy.st1.etape1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

import java.util.Scanner;

import org.iae.annecy.st1.common.mvc.BasicDataParam;
import org.iae.annecy.st1.common.mvc.ConsoleInputView;
import org.iae.annecy.st1.common.mvc.DataParam;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.CatalogueModel;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.etape1.model.client.ListeClient;
import org.iae.annecy.st1.etape1.model.panier.Panier;
import org.iae.annecy.st1.etape1.model.produit.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.model.person.PersonAddModel;
import org.iae.annecy.st1.etape1.model.person.PersonGetModel;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.etape1.view.menu.Menu;
import org.iae.annecy.st1.etape1.view.person.PersonAddFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonCreateFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonGetFrenchView;
import org.iae.annecy.st1.tools.ConsoleHelper;

/**
 * Classe permetant de tester le MVC.
 * 
 * @author Djer1013
 */
public class Main implements Serializable {

	/**
	 * COntroller permetant le traitement des actions d'exemple.
	 */
	private static MainController mainController;

	static {
		Main.mainController = new MainController();
	}

	/**
	 * Lance l'application.
	 * 
	 * @param args
	 *            command line parameters
	 */
	public static void main(final String[] args) {
		initUserModel();
		initCustomerModel();

		final Scanner scan = new Scanner(System.in, "UTF-8");

		final DataView userData = Main.mainController.get("user:display");
		final StringView userView = new UserTextFrenchView();

		// ConsoleHelper.display(userView.build(userData));

		Produit p1;
		Produit p2;
		Catalogue c1 = new Catalogue();

		Menu menu = new Menu();
		int choixmenu;
		String choixclient;
		Scanner sc = new Scanner(System.in);

		Client client1;
		Client client2;
		ListeClient listeClient1 = new ListeClient();

		Panier monPanier = new Panier();

		
//************************ DESERIALIZATION CATALOGUE / CLIENT ****************************
		
		try {
			FileInputStream fileIn = new FileInputStream("SauvegardeCatalogue.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c1 = (Catalogue) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException d) {
			ConsoleHelper.display("Le fichier -SauvegardeCatalogue- n'est pas créé !");
			p1 = new Produit("001", "Head First Java", "Livre", "", 50);
			p2 = new Produit("002", "ASUS 752", "Ordinateur", "", 1800);
			c1.ajouterProduit(p1);
			c1.ajouterProduit(p2);
			// d.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			ConsoleHelper.display("Le catalogue de produits n'est pas créé !");
			c.printStackTrace();
			return;
		}

		try {
			FileInputStream fileIn = new FileInputStream("SauvegardeClient.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			listeClient1 = (ListeClient) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException d) {
			ConsoleHelper.display("Le fichier -SauvegardeClient- n'est pas créé !");
			client1 = new Client("MARTIN", "Pierre", 01, 5);
			client2 = new Client("DUPONT", "Céline", 02, 10);
			listeClient1.ajouterClient(client1);
			listeClient1.ajouterClient(client2);
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			ConsoleHelper.display("Le client n'est pas créé !");
			c.printStackTrace();
			return;
		}

		choixmenu = menu.menuGeneral();

		while (choixmenu < 6) {
			switch (choixmenu) {
			case 1:
				c1.menuGeneral();
				choixmenu = menu.menuGeneral();
				break;
			case 2:
				listeClient1.menuClient();
				choixmenu = menu.menuGeneral();
				break;
			case 3:
				ConsoleHelper.display("Entre votre nom client pour vous connecter : ");
				choixclient = sc.next();
				if (listeClient1.retrieveClient(choixclient) != null) {
					monPanier.setClient(listeClient1.retrieveClient(choixclient));
					monPanier.menuPanier(c1);
				} else {
					ConsoleHelper.display("Le client n\'existe pas");
				}
				choixmenu = menu.menuGeneral();
				break;
			case 4:
				ConsoleHelper.display("Vous êtes dans le menu commande");
				choixmenu = menu.menuGeneral();
				break;
			case 5:
				ConsoleHelper.display("Vous avez quitté le logiciel !!!! Bonne journée !");
				choixmenu = menu.menuGeneral();
				break;
			}
		}
	}

	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
	}

	private static void initCustomerModel() {
		final PersonGetModel customerGetModel = new PersonGetModel();
		customerGetModel.register(mainController);

		final PersonAddModel customerAddModel = new PersonAddModel();
		customerAddModel.register(mainController);
	}

}
