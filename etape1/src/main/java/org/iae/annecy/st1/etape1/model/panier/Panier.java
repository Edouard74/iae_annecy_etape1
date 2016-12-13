package org.iae.annecy.st1.etape1.model.panier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.etape1.model.produit.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class Panier {
	private Client client;
	private ArrayList<Produit> produits = new ArrayList<Produit>();
	private boolean validationCommande = false;

//*********************** GETTERS ET SETTERS ***********************

	public Client getClient() {
		return client;
	}

	public boolean isValidationCommande() {
		return validationCommande;
	}

	public void setValidationCommande(boolean validationCommande) {
		this.validationCommande = validationCommande;
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}

//********************* AJOUTER PRODUIT DANS PANIER ******************
	
	public void remplirPanier(Produit achat) {
		this.produits.add(achat);
	}

//*********************** AFFICHAGE DU PANIER ************************

	public void afficherPanier() {
		String text = "";
		int i = 1;
		for (Produit pro : produits) {
			ConsoleHelper.display("Produit numero: " + i + " Ref : " + pro.getRef() + "   Description : "
					+ pro.getDesc() + "   Nom : " + pro.getNom() + "   Description longue : " + pro.getDescLongue()
					+ "   Prix : " + pro.getPrix());
			ConsoleHelper.display("Quantite : " + pro.getQuantite());
			ConsoleHelper.display("================================");
			i++;
		}
	}
	
//***************** RECHERCHE D'UN PRODUIT PAR LA REF *******************

	public Produit retrieve(String ref) {
		Iterator<Produit> it = this.getProduits().iterator();
		Produit prodTrouve = new Produit();
		while (it.hasNext()) {
			prodTrouve = it.next();
			if (ref.equals(prodTrouve.getRef())) {
				return prodTrouve;
			}
		}
		return null;
	}

//************************* PRIX PANIER *******************************		

	public void calculPrix() {
		String text = "";
		int i = 1;
		float soustotal;
		float total = 0;
		float reduction;
		if (!this.getProduits().isEmpty()) {
			for (Produit pro : produits) {
				ConsoleHelper.display("Produit numero: " + i + " Ref : " + pro.getRef() + "   Description : "
						+ pro.getDesc() + "   Nom : " + pro.getNom() + "   Description longue : " + pro.getDescLongue()
						+ "   Prix unitaire : " + pro.getPrix() + " €.");
				ConsoleHelper.display("Quantité : " + pro.getQuantite() + ".");
				soustotal = pro.getPrix() * pro.getQuantite();
				ConsoleHelper.display("Total produit : " + soustotal + " €.");
				total += soustotal;
				ConsoleHelper.display("==============================");
				i++;
			}
			ConsoleHelper.display("");
			ConsoleHelper.display("Prix total de votre panier : " + total + " €.");
			switch (this.getClient().getCodePromotionnel()) {
			case 0:
				ConsoleHelper.display("Malheureusement, vous n'avez pas de réduction !");
				ConsoleHelper.display("TOTAL PANIER : " + total + "€.");
				break;
			case 5:
				reduction = (5 * total) / 100;
				ConsoleHelper.display("Vous avez une réduction de 5%, soit : " + reduction + " €.");
				ConsoleHelper.display("TOTAL PANIER A REGLER : " + (total - reduction) + " €.");
				break;
			case 10:
				reduction = (10 * total) / 100;
				ConsoleHelper.display("Vous avez une réduction de 10%, soit : " + reduction + " €.");
				ConsoleHelper.display("TOTAL PANIER A REGLER : " + (total - reduction) + " €.");
				break;
			}
		}
	}

//************************ MENU PANIER *******************************

	public int affichageMenuPanier() {
		Scanner sca = new Scanner(System.in);
		ConsoleHelper.display("");
		ConsoleHelper.display("=========== MENU PANIER : ============");
		ConsoleHelper.display("");
		ConsoleHelper.display("1: Parcourir le catalogue");
		ConsoleHelper.display("2: Ajouter un produit");
		ConsoleHelper.display("3: Récapitulatif panier");
		ConsoleHelper.display("4: Valider le panier");
		ConsoleHelper.display("5: Fermer votre session et quitter");
		int m = sca.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + m);
		return m;
	}

//************************* MENU PANIER GENERAL ***************************

	public void menuPanier(Catalogue monCata) {
		Scanner sca = new Scanner(System.in);
		int m = this.affichageMenuPanier();
		String reference;
		Panier panierDetail = new Panier();
		Produit produitTrouvePanier = new Produit();
		int quantite;
		while (m < 5) {
			if (m == 1) {
				ConsoleHelper.display("Voici le catalogue produit :");
				monCata.afficherCat();
				m = this.affichageMenuPanier();
			} else if (m == 2) {
				ConsoleHelper.display("Entrer la ref produit souhaité :");
				reference = sca.next();
				if (monCata.retrieve(reference) != null) {
					if (this.retrieve(reference) == null) {
						produitTrouvePanier = monCata.retrieve(reference);
						ConsoleHelper.display("Entrez la quantité souhaitée :");
						quantite = sca.nextInt();
						produitTrouvePanier.setQuantite(quantite);
						this.remplirPanier(produitTrouvePanier);
						this.setValidationCommande(false);
					} else {
						ConsoleHelper.display("Le produit est déjà dans le panier !");
					}
				} else {
					ConsoleHelper.display("le produit n\'existe pas");
				}
				m = this.affichageMenuPanier();
			} else if (m == 3) {
				ConsoleHelper.display("");
				ConsoleHelper.display("====== Votre panier : =====");
				ConsoleHelper.display("");
				this.calculPrix();
				m = this.affichageMenuPanier();
			} else if (m == 4) {
				if (this.isValidationCommande() == false) {
					ConsoleHelper.display("Valider la commande: 1:Oui / 2:Non");
					int valider = sca.nextInt();
					if (valider == 1) {
						this.setValidationCommande(true);
						ConsoleHelper.display("Commande validée !");
					}
				} else {
					ConsoleHelper.display("Commande deja validée !");
				}
				m = this.affichageMenuPanier();
			}
		}
	}
}
