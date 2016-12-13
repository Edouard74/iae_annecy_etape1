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

//****************************** MODIFICATION DU PRODUIT *****************************

	public void modifAttribut(int i, int j) {
		Scanner sc = new Scanner(System.in);
		/*
		 * if (j==1){ ConsoleHelper.display("Entrez la nouvelle référence :");
		 * String d = sc.nextLine(); this.getProduits().get(i-1).setRef(d);
		 * }else
		 */
		if (j == 1) {
			ConsoleHelper.display("Entrez la nouvelle description :");
			String d = sc.nextLine();
			this.getProduits().get(i - 1).setDesc(d);
			this.afficherProduitModifie(i);
		} else if (j == 2) {
			ConsoleHelper.display("Entrez le nouveau nom :");
			String t = sc.nextLine();
			this.getProduits().get(i - 1).setNom(t);
			this.afficherProduitModifie(i);
		} else if (j == 3) {
			ConsoleHelper.display("Entrez la nouvelle description longue :");
			String r = sc.next();
			this.getProduits().get(i - 1).setDescLongue(r);
			this.afficherProduitModifie(i);
		} else if (j == 4) {
			ConsoleHelper.display("Entrez le nouveau prix :");
			Float f = sc.nextFloat();
			this.getProduits().get(i - 1).setPrix(f);
			this.afficherProduitModifie(i);
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
		Scanner sm = new Scanner(System.in);
		ConsoleHelper.display("Entrez la référence Produit : ");
		String ref = sm.next();
		if (this.retrieve(ref) == null) {
			ConsoleHelper.display("Entrez le prix : ");
			Float pr = sm.nextFloat();
			if (pr >= 0) {
				ConsoleHelper.display("Entrez le nom : ");
				String n = sm.next();
				ConsoleHelper.display("Entrez la description : ");
				String de = sm.next();
				ConsoleHelper.display("Entrez la description longue : ");
				String del = sm.next();
				Produit produitCree = new Produit(ref, n, de, del, pr);
				ajouterProduit(produitCree);
			} else {
				ConsoleHelper.display("le prix doit être positif !!");
			}
		} else {
			ConsoleHelper.display("le produit existe deja !!");
		}
	}

//************************ MENU AFFICHAGE RESPONSABLE PRODUIT **********************************

	public int menuGen() {
		Scanner sca = new Scanner(System.in);
		ConsoleHelper.display("");
		ConsoleHelper.display("====================== INTERFACE CATALOGUE PRODUIT : ===================");
		ConsoleHelper.display("");
		ConsoleHelper.display("1: Parcourir le catalogue produits");
		ConsoleHelper.display("2: Voir le détail d'un produit");
		ConsoleHelper.display("3: Modifier un produit");
		ConsoleHelper.display("4: Ajouter un produit");
		ConsoleHelper.display("5: Enregistrer et quitter");
		int m = sca.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + m);
		return m;
	}
	
//******************* MENU AFFICHAGE MODIFICATION PRODUIT *************************************	

	public int menuModif() {
		Scanner sca = new Scanner(System.in);
		ConsoleHelper.display("======== MENU MODIFICATION : =========");
		ConsoleHelper.display("1: Modif description");
		ConsoleHelper.display("2: Modif nom");
		ConsoleHelper.display("3: Modif description longue");
		ConsoleHelper.display("4: Modif prix");
		int n = sca.nextInt();
		ConsoleHelper.display("Vous avez fait le choix : " + n);
		return n;
	}

//**************************** MENU CATALOGUE PRODUIT ****************************************
	
	public void menuGeneral() {
		Scanner sca = new Scanner(System.in);
		int m = this.menuGen();
		Produit proddetail = new Produit();
		while (m < 5) {
			if (m == 1) {
				ConsoleHelper.display("");
				ConsoleHelper.display("=== Voici le catalogue produit : ===");
				ConsoleHelper.display("");
				afficherCat();
				m = this.menuGen();
			} else if (m == 2) {
				ConsoleHelper.display("Entrer une référence (ex: 001 ou 002) :");
				Scanner stef = new Scanner(System.in);
				String ref = stef.nextLine();
				proddetail = this.retrieve(ref);
				if (proddetail != null) {
					ConsoleHelper.display("==== Produit trouvé ! voici ses caratéristiques : ====");
					ConsoleHelper.display("");
					proddetail.afficherProduit();
				} else {
					ConsoleHelper.display("Produit non trouvé ! Créer ce produit.");
				}
				m = this.menuGen();
			} else if (m == 3) {
				this.afficherCat();
				ConsoleHelper.display("Entrer le numero du produit a modifier :");
				ConsoleHelper.display("");
				int i = sca.nextInt();
				int n = this.menuModif();
				/*
				 * if (j==1){
				 * ConsoleHelper.display("Entrez la nouvelle référence :");
				 * String d = sc.nextLine();
				 * this.getProduits().get(i-1).setRef(d); }else
				 */
				if (n == 1) {
					ConsoleHelper.display("Entrez la nouvelle description :");
					String d = sca.next();
					this.getProduits().get(i - 1).setDesc(d);
					this.afficherProduitModifie(i);
				} else if (n == 2) {
					ConsoleHelper.display("Entrez le nouveau nom :");
					String t = sca.next();
					this.getProduits().get(i - 1).setNom(t);
					this.afficherProduitModifie(i);
				} else if (n == 3) {
					ConsoleHelper.display("Entrez la nouvelle description longue :");
					String r = sca.next();
					this.getProduits().get(i - 1).setDescLongue(r);
					this.afficherProduitModifie(i);
				} else if (n == 4) {
					ConsoleHelper.display("Entrez le nouveau prix :");
					Float f = sca.nextFloat();
					this.getProduits().get(i - 1).setPrix(f);
					this.afficherProduitModifie(i);
				}
				m = this.menuGen();
			} else if (m == 4) {
				ConsoleHelper.display("ajouter un produit :");
				creerProduit();
				m = this.menuGen();
			}
		}

//********************************* SERIALIZATION CATALOGUE ***************************************

		try {
			FileOutputStream fichier = new FileOutputStream("SauvegardeCatalogue.txt");
			ObjectOutputStream out = new ObjectOutputStream(fichier);
			out.writeObject(this);
			out.close();
			fichier.close();
			ConsoleHelper.display("Catalogue de produits sauvegardé !");
		} catch (IOException i1) {
			i1.printStackTrace();
		}
	}
}
		
	
