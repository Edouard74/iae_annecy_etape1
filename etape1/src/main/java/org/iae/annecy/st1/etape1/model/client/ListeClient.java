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

	public Client retrieveClient(String id) {
		Iterator<Client> it = this.getClients().iterator();
		Client clientTrouve = new Client();
		while (it.hasNext()) {
			clientTrouve = it.next();
			if (id.equals(clientTrouve.getNom())) {

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

//**************************** AFFICHAGE D'UN CLIENT ******************************

	public void afficherClient(int chCli) {
		this.getClients().get(chCli - 1);
		ConsoleHelper.display("");
		ConsoleHelper.display("==========Modification enregistrée !!==========");
		ConsoleHelper.display("Nom : " + this.getClients().get(chCli - 1).getNom() + "	Prénom : "
				+ this.getClients().get(chCli - 1).getPrenom() + "	Numéro Client : "
				+ this.getClients().get(chCli - 1).getNumeroClient() + "	Code promotionnel : "
				+ this.getClients().get(chCli - 1).getCodePromotionnel() + " %.");
	}

//************************* CREATION ET AJOUT D'UN CLIENT **************************	

	public void creerClient() {
		Scanner sm = new Scanner(System.in);
		ConsoleHelper.display("Entrez le nom du client à ajouter : ");
		String rnom = sm.next();
		if (this.retrieveClient(rnom) == null) {
			ConsoleHelper.display("OK, ce client n'est pas encore créé !");
			ConsoleHelper.display("Confirmer le nom : ");
			String name = sm.next();
			ConsoleHelper.display("Entrez le prénom : ");
			String n = sm.next();
			ConsoleHelper.display("Entrez le numéro Client : ");
			int de = sm.nextInt();
			ConsoleHelper.display("Entrez le nouveau code promotionnel :  1=0%  2=5% 3=10%");
			int del = sm.nextInt();
			switch (del) {
			case 1:
				del = 0;
				break;
			case 2:
				del = 5;
				break;
			case 3:
				del = 10;
				break;
			}
			Client clientCree = new Client(name, n, de, del);
			ajouterClient(clientCree);
		}
	}

//******************************** MENU CLIENT *************************************	
	
	public int menuCli() {
		Scanner sca = new Scanner(System.in);
		ConsoleHelper.display("                                      ");
		ConsoleHelper.display("=========== MENU CLIENT : ============");
		ConsoleHelper.display("                                      ");
		ConsoleHelper.display("1: Parcourir le listing client");
		ConsoleHelper.display("2: Rechercher un client");
		ConsoleHelper.display("3: Modification d'un client");
		ConsoleHelper.display("4: Créer un client");
		ConsoleHelper.display("5: Détail d'un client");
		ConsoleHelper.display("6: Enregistrer et Quitter");
		int m = sca.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + m);
		return m;
	}
	
//***************************** MENU MODIF CLIENT ********************************

	public int menuModifClient() {
		Scanner sca = new Scanner(System.in);
		ConsoleHelper.display("");
		ConsoleHelper.display("========== MENU MODIFICATION CLIENT : =========");
		ConsoleHelper.display("");
		ConsoleHelper.display("1: Modif nom");
		ConsoleHelper.display("2: Modif prénom");
		ConsoleHelper.display("3: Modif numéro client");
		ConsoleHelper.display("4: Modif Code Promotionnel");
		int n = sca.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + n);
		return n;
	}

//****************************** MENU CLIENT ***********************************
	
	public void menuClient() {
		Scanner sca = new Scanner(System.in);
		int m = this.menuCli();
		Client clientDetail = new Client();
		while (m < 6) {
			if (m == 1) {
				ConsoleHelper.display("");
				ConsoleHelper.display("=== Voici le listing client : ===");
				ConsoleHelper.display("");
				afficherListeClient();
				m = this.menuCli();
			} else if (m == 2) {
				ConsoleHelper.display("Entrer un numéro client :");
				Scanner stefi = new Scanner(System.in);
				int rnom = stefi.nextInt();
				clientDetail = this.retrieveClientId(rnom);
				if (clientDetail != null) {
					ConsoleHelper.display("==== Client trouvé ! Voici ses caractéristiques : ====");
					ConsoleHelper.display("");
					clientDetail.afficherClient();
				} else {
					ConsoleHelper.display("client non trouvé ! ");
				}
				m = this.menuCli();
			}
			else if (m == 3) {
				this.afficherListeClient();
				ConsoleHelper.display("enter le numero du client à modifier :");
				int i = 0;
				i = sca.nextInt();
				int n = this.menuModifClient();
				if (n == 1) {
					ConsoleHelper.display("Entrez le nouveau nom :");
					String d = sca.next();
					this.getClients().get(i - 1).setNom(d);
					this.afficherClient(i);
				} else if (n == 2) {
					ConsoleHelper.display("Entrez le nouveau prénom :");
					String t = sca.next();
					this.getClients().get(i - 1).setPrenom(t);
					this.afficherClient(i);
				} else if (n == 3) {
					ConsoleHelper.display("Entrez le numéro client :");
					int r = sca.nextInt();
					this.getClients().get(i - 1).setNumeroClient(r);
					this.afficherClient(i);
				} else if (n == 4) {
					ConsoleHelper.display("Entrez le nouveau code promotionnel :  1=0%  2=5% 3=10%");
					int f = sca.nextInt();
					switch (f) {
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
				m = this.menuCli();
			} else if (m == 4) {
				ConsoleHelper.display("ajouter un client :");
				creerClient();
				m = this.menuCli();
			} else if (m == 5) {
				ConsoleHelper.display("Entrer le numéro client :");
				Scanner stef = new Scanner(System.in);
				int ref = stef.nextInt();
				clientDetail = this.retrieveClientId(ref);
				if (clientDetail != null) {
					ConsoleHelper.display("Voici les informations du client : ");
					clientDetail.afficherClient();
				} else {
					ConsoleHelper.display("client non trouvé !!");
				}
				m = this.menuCli();
			}
		}
		
//**************************** SERIALIZATION CLIENT ************************************

		try {
			FileOutputStream fichier = new FileOutputStream("SauvegardeClient.txt");
			ObjectOutputStream out = new ObjectOutputStream(fichier);
			out.writeObject(this);
			out.close();
			fichier.close();
			ConsoleHelper.display("Liste de clients Sauvegardée !");
		} catch (IOException i1) {
			i1.printStackTrace();
		}
	}
}