package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Catalogue implements Serializable {

	private ArrayList<Produit> produits = new ArrayList<Produit>();

//****************************** GETTERS ET SETTERS ********************************
	
	public ArrayList<Produit> getProduits() {
		return produits;
	}
	
//************************ AJOUT DE PRODUIT DANS CATALOGUE *************************
	
	public void ajouterProduit(Produit p){
		this.produits.add(p);
	}
	
//************************** AFFICHAGE DU CATALOGUE ********************************

	public void afficherCat() {
		String text = "";
		int i = 1;
		for (Produit prod : produits) {
			ConsoleHelper.display("Produit numero : " + i + "   Ref : " + prod.getRef() + "   Description : "
					+ prod.getDesc() + "   Nom : " + prod.getNom() + "   Description longue : " + prod.getDescLongue()
					+ "   Prix : " + prod.getPrix() + " €.");
			i++;
		}
	}

//************************ RECHERCHE D'UN PRODUIT PAR LA REF ******************************

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

//*************************** AFFICHAGE DU PRODUIT MODIFIE *********************************

	public void afficherProduitModifie(int chProd) {
		this.getProduits().get(chProd - 1);
		ConsoleHelper.display("");
		ConsoleHelper.display("=============Modification enregistrée !!============");
		ConsoleHelper.display("");
		ConsoleHelper.display("Ref : " + this.getProduits().get(chProd - 1).getRef() + "	Description : "
				+ this.getProduits().get(chProd - 1).getDesc() + "	Nom : " + this.getProduits().get(chProd - 1).getNom()
				+ "		Description détaillée : " + this.getProduits().get(chProd - 1).getDescLongue() + "		Prix : "
				+ this.getProduits().get(chProd - 1).getPrix() + " €.");
	}

//*************************** AJOUT PRODUIT DANS LE CATALOGUE ******************************

	public void creerProduit() {
		Scanner choixAjout = new Scanner(System.in);
		ConsoleHelper.display("Créez la référence Produit : ");
		String ref = choixAjout.next();
		if (this.retrieve(ref) == null) {
			ConsoleHelper.display("Entrez le prix : ");
			Float prix = choixAjout.nextFloat();
			if (prix >= 0) {
				ConsoleHelper.display("Entrez le nom : ");
				String nom = choixAjout.next();
				ConsoleHelper.display("Entrez la description : ");
				String description = choixAjout.next();
				ConsoleHelper.display("Entrez la description longue : ");
				String descriptionLongue = choixAjout.next();
				Produit produitCree = new Produit(ref, nom, description, descriptionLongue, prix);
				ajouterProduit(produitCree);
			} else {
				ConsoleHelper.display("le prix ne peut pas être négatif !!");
			}
		} else {
			ConsoleHelper.display("le produit existe deja !!");
		}
	}

//************************ AFFICHAGE MENU PRODUIT **********************************

	public int menuProduit() {
		Scanner choixMenuProduit = new Scanner(System.in);
		ConsoleHelper.display("");
		ConsoleHelper.display("====================== INTERFACE CATALOGUE PRODUIT : ===================");
		ConsoleHelper.display("");
		ConsoleHelper.display("1: Parcourir le catalogue produits");
		ConsoleHelper.display("2: Voir le détail d'un produit");
		ConsoleHelper.display("3: Modifier un produit");
		ConsoleHelper.display("4: Ajouter un produit");
		ConsoleHelper.display("5: Enregistrer et quitter");
		int menuProduitChoisi = choixMenuProduit.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + menuProduitChoisi);
		return menuProduitChoisi;
	}
	
//******************* MENU AFFICHAGE MODIFICATION PRODUIT *************************************	

	public int menuModif() {
		Scanner choixModifProduit = new Scanner(System.in);
		ConsoleHelper.display("======== MENU MODIFICATION : =========");
		ConsoleHelper.display("1: Modif description");
		ConsoleHelper.display("2: Modif nom");
		ConsoleHelper.display("3: Modif description longue");
		ConsoleHelper.display("4: Modif prix");
		int modifProduitChoisi = choixModifProduit.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + modifProduitChoisi);
		return modifProduitChoisi;
	}

//**************************** MENU CATALOGUE PRODUIT ****************************************
	
	public void menuGeneral() {
		Scanner choixGeneralProduit = new Scanner(System.in);
		int m = this.menuProduit();
		Produit proddetail = new Produit();
		while (m < 5) {
			if (m == 1) {
				ConsoleHelper.display("");
				ConsoleHelper.display("=== Voici le catalogue produit : ===");
				ConsoleHelper.display("");
				afficherCat();
				m = this.menuProduit();
			} else if (m == 2) {
				ConsoleHelper.display("Entrer une référence (ex: 001 ou 002) :");
				Scanner choixDetail = new Scanner(System.in);
				String ref = choixDetail.nextLine();
				proddetail = this.retrieve(ref);
				if (proddetail != null) {
					ConsoleHelper.display("==== Produit trouvé ! voici ses caratéristiques : ====");
					ConsoleHelper.display("");
					proddetail.afficherProduit();
				} else {
					ConsoleHelper.display("Produit non trouvé ! Créer ce produit.");
				}
				m = this.menuProduit();
			} else if (m == 3) {
				this.afficherCat();
				ConsoleHelper.display("");
				ConsoleHelper.display("Entrer le numero du produit a modifier :");
				ConsoleHelper.display("");
				int i = choixGeneralProduit.nextInt();
				int n = this.menuModif();
				/*
				 * if (n==1){
				 * ConsoleHelper.display("Entrez la nouvelle référence :");
				 * String d = sca.next();
				 * this.getProduits().get(i-1).setRef(d); }else
				 */
				if (n == 1) {
					ConsoleHelper.display("Entrez la nouvelle description :");
					String description = choixGeneralProduit.next();
					this.getProduits().get(i - 1).setDesc(description);
					this.afficherProduitModifie(i);
				} else if (n == 2) {
					ConsoleHelper.display("Entrez le nouveau nom :");
					String nom = choixGeneralProduit.next();
					this.getProduits().get(i - 1).setNom(nom);
					this.afficherProduitModifie(i);
				} else if (n == 3) {
					ConsoleHelper.display("Entrez la nouvelle description longue :");
					String descriptionLongue = choixGeneralProduit.next();
					this.getProduits().get(i - 1).setDescLongue(descriptionLongue);
					this.afficherProduitModifie(i);
				} else if (n == 4) {
					ConsoleHelper.display("Entrez le nouveau prix :");
					Float prix = choixGeneralProduit.nextFloat();
					this.getProduits().get(i - 1).setPrix(prix);
					this.afficherProduitModifie(i);
				}
				m = this.menuProduit();
			} else if (m == 4) {
				ConsoleHelper.display("ajouter un produit :");
				creerProduit();
				ConsoleHelper.display("");
				ConsoleHelper.display("====== Produit créé !! ======");
				m = this.menuProduit();
			}
		}

//********************************* SERIALIZATION CATALOGUE ***************************************

		try {
			FileOutputStream fichier = new FileOutputStream("SauvegardeCatalogue.txt");
			ObjectOutputStream out = new ObjectOutputStream(fichier);
			out.writeObject(this);
			out.close();
			fichier.close();
			ConsoleHelper.display("");
			ConsoleHelper.display("=== Catalogue de produits sauvegardé ! ===");
		} catch (IOException i1) {
			i1.printStackTrace();
		}
	}
}
		
	
