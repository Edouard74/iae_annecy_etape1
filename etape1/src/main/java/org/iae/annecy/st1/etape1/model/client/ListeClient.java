package org.iae.annecy.st1.etape1.model.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class ListeClient implements Serializable {

	private ArrayList<Client> clients = new ArrayList<Client>();

//********************* GETTER ET SETTER **********************************	

	public ArrayList<Client> getClients() {
		return clients;
	}
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

//****************** AJOUT DE CLIENT DANS LA LISTE CLIENT *****************

	public void ajouterClient(Client cli) {
		this.clients.add(cli);
	}

//*********************** AFFICHER LISTE CLIENT ***************************

	public void afficherListeClient() {
		String text = "";
		int i = 1;
		for (Client clie : clients) {
			ConsoleHelper.display("Client numero : " + i + " Nom : " + clie.getNom() + "   Prénom : " + clie.getPrenom()
					+ "   Numéro Client: " + clie.getNumeroClient() + "   Code promotionnel : "
					+ clie.getCodePromotionnel() + " %.");
			i++;
		}
	}

//*********************** RECHERCHE CLIENT PAR LE NOM *************************

	public Client retrieveClient(String nom) {
		Iterator<Client> it = this.getClients().iterator();
		Client clientTrouve = new Client();
		while (it.hasNext()) {
			clientTrouve = it.next();
			if (nom.equals(clientTrouve.getNom())) {

				return clientTrouve;
			}
		}
		return null;
	}

//******************* RECHERCHE CLIENT PAR LE NUMERO CLIENT ********************

	public Client retrieveClientId(int id) {
		Iterator<Client> it = this.getClients().iterator();
		Client clientTrouveId = new Client();
		while (it.hasNext()) {
			clientTrouveId = it.next();
			if (id == (clientTrouveId.getNumeroClient())) {
				return clientTrouveId;
			}
		}
		return null;
	}

//**************************** AFFICHAGE D'UN CLIENT APRES MODIF ******************************

	public void afficherClient(int chCli) {
		this.getClients().get(chCli - 1);
		ConsoleHelper.display("");
		ConsoleHelper.display("========== Modification enregistrée !! ==========");
		ConsoleHelper.display("Nom : " + this.getClients().get(chCli - 1).getNom() + "	Prénom : "
				+ this.getClients().get(chCli - 1).getPrenom() + "	Numéro Client : "
				+ this.getClients().get(chCli - 1).getNumeroClient() + "	Code promotionnel : "
				+ this.getClients().get(chCli - 1).getCodePromotionnel() + " %.");
	}

//************************* CREATION ET AJOUT D'UN CLIENT **************************	

	public void creerClient() {
		Scanner choixCreerClient = new Scanner(System.in);
		ConsoleHelper.display("Entrez le nom du client à ajouter : ");
		String nom = choixCreerClient.next();
		if (this.retrieveClient(nom) == null) {
			ConsoleHelper.display("OK, ce client n'est pas encore créé !");
			ConsoleHelper.display("Confirmer le nom : ");
			String name = choixCreerClient.next();
			ConsoleHelper.display("Entrez le prénom : ");
			String prenom = choixCreerClient.next();
			ConsoleHelper.display("Entrez le numéro Client : ");
			int numClient = choixCreerClient.nextInt();
			ConsoleHelper.display("Entrez le nouveau code promotionnel :  1=0%  2=5% 3=10%");
			int codePromo = choixCreerClient.nextInt();
			switch (codePromo) {
			case 1:
				codePromo = 0;
				break;
			case 2:
				codePromo = 5;
				break;
			case 3:
				codePromo = 10;
				break;
			}
			Client clientCree = new Client(name, prenom, numClient, codePromo);
			ajouterClient(clientCree);
		}
	}

//******************************** AFFICHAGE MENU CLIENT *************************************	
	
	public int menuClientGeneral() {
		Scanner choixMenuClientGen = new Scanner(System.in);
		ConsoleHelper.display("                                      ");
		ConsoleHelper.display("=========== MENU CLIENT : ============");
		ConsoleHelper.display("                                      ");
		ConsoleHelper.display("1: Parcourir le listing client");
		ConsoleHelper.display("2: Rechercher un client");
		ConsoleHelper.display("3: Modification d'un client");
		ConsoleHelper.display("4: Créer un client");
		ConsoleHelper.display("5: Détail d'un client");
		ConsoleHelper.display("6: Enregistrer et Quitter");
		int m = choixMenuClientGen.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + m);
		return m;
	}
	
//***************************** MENU MODIF CLIENT ********************************

	public int menuModifClient() {
		Scanner scanModif = new Scanner(System.in);
		ConsoleHelper.display("");
		ConsoleHelper.display("========== MENU MODIFICATION CLIENT : =========");
		ConsoleHelper.display("");
		ConsoleHelper.display("1: Modif nom");
		ConsoleHelper.display("2: Modif prénom");
		ConsoleHelper.display("3: Modif numéro client");
		ConsoleHelper.display("4: Modif Code Promotionnel");
		int choixModif = scanModif.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + choixModif);
		return choixModif;
	}

//****************************** MENU CLIENT ***********************************
	
	public void menuClient() {
		Scanner choixMenuClient = new Scanner(System.in);
		int m = this.menuClientGeneral();
		Client clientDetail = new Client();
		while (m < 6) {
			if (m == 1) {
				ConsoleHelper.display("");
				ConsoleHelper.display("=== Voici le listing client : ===");
				ConsoleHelper.display("");
				afficherListeClient();
				m = this.menuClientGeneral();
			} else if (m == 2) {
				ConsoleHelper.display("Entrer un numéro client :");
				Scanner choixRecherche = new Scanner(System.in);
				int id = choixRecherche.nextInt();
				clientDetail = this.retrieveClientId(id);
				if (clientDetail != null) {
					ConsoleHelper.display("==== Client trouvé ! Voici ses caractéristiques : ====");
					ConsoleHelper.display("");
					clientDetail.afficherClient();
				} else {
					ConsoleHelper.display("client non trouvé ! ");
				}
				m = this.menuClientGeneral();
			}
			else if (m == 3) {
				this.afficherListeClient();
				ConsoleHelper.display("enter le numero du client à modifier :");
				int i = 0;
				i = choixMenuClient.nextInt();
				int n = this.menuModifClient();
				if (n == 1) {
					ConsoleHelper.display("Entrez le nouveau nom :");
					String nom = choixMenuClient.next();
					this.getClients().get(i - 1).setNom(nom);
					this.afficherClient(i);
				} else if (n == 2) {
					ConsoleHelper.display("Entrez le nouveau prénom :");
					String prenom = choixMenuClient.next();
					this.getClients().get(i - 1).setPrenom(prenom);
					this.afficherClient(i);
				} else if (n == 3) {
					ConsoleHelper.display("Entrez le numéro client :");
					int numClient = choixMenuClient.nextInt();
					this.getClients().get(i - 1).setNumeroClient(numClient);
					this.afficherClient(i);
				} else if (n == 4) {
					ConsoleHelper.display("Entrez le nouveau code promotionnel :  1=0%  2=5% 3=10%");
					int codePromo = choixMenuClient.nextInt();
					switch (codePromo) {
					case 1:
						this.getClients().get(i - 1).setCodePromotionnel(0);
						break;
					case 2:
						this.getClients().get(i - 1).setCodePromotionnel(5);
						break;
					case 3:
						this.getClients().get(i - 1).setCodePromotionnel(10);
						break;
					}
					this.afficherClient(i);
				}
				m = this.menuClientGeneral();
			} else if (m == 4) {
				ConsoleHelper.display("ajouter un client :");
				creerClient();
				ConsoleHelper.display("====== Client créé !! ======");
				m = this.menuClientGeneral();
			} else if (m == 5) {
				ConsoleHelper.display("Entrer le numéro client :");
				Scanner choixAffichClient = new Scanner(System.in);
				int ref = choixAffichClient.nextInt();
				clientDetail = this.retrieveClientId(ref);
				if (clientDetail != null) {
					ConsoleHelper.display("===== Voici les informations du client : =====");
					ConsoleHelper.display("");
					clientDetail.afficherClient();
				} else {
					ConsoleHelper.display("client non trouvé !!");
				}
				m = this.menuClientGeneral();
			}
		}
		
//**************************** SERIALIZATION CLIENT ************************************

		try {
			FileOutputStream fichier = new FileOutputStream("SauvegardeClient.txt");
			ObjectOutputStream out = new ObjectOutputStream(fichier);
			out.writeObject(this);
			out.close();
			fichier.close();
			ConsoleHelper.display("");
			ConsoleHelper.display("=== Liste de clients Sauvegardée ! ===");
		} catch (IOException i1) {
			i1.printStackTrace();
		}
	}
}