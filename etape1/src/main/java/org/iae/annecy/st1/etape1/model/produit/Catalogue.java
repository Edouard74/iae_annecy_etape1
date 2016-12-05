package org.iae.annecy.st1.etape1.model.produit;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Catalogue implements Serializable{
	private ArrayList<Produit>produits = new ArrayList<Produit>();
	
	
//********************GETTERS ET SETTERS**********************************
	
	public ArrayList<Produit> getProduits() {
		return produits;
	}
	
//*******************AJOUT DE PRODUIT DANS CATALOGUE**********************
	
	public void ajouterProduit(Produit p){
		this.produits.add(p);
	}
	
//*********************AFFICHER CATALOGUE*********************************
	
	public void AfficherCat(){
		String text = "";
		for(Produit prod: produits){
		System.out.println("Ref : " + prod.getRef()+ "   Description : " + prod.getDesc() + "   Nom : " + prod.getNom() + "   Description longue : " + prod.getDescLongue() + "   Prix : " + prod.getPrix());
			}
		
	}
	
//*************************AFFICHAGE PRODUIT CHOISI*********************************
	
	public void afficherProduitChoisi(int chProd) {
		if (chProd==1){
			this.getProduits().get(chProd-1);
			System.out.println ("=============Détails du produit============="+"\nRef : " + this.getProduits().get(chProd-1).getRef() + "\nDescription : " + this.getProduits().get(chProd-1).getDesc() +"\nNom : " + this.getProduits().get(chProd-1).getNom()+ "\nDescription détaillée : " + this.getProduits().get(chProd-1).getDescLongue() + "\nPrix : " + this.getProduits().get(chProd-1).getPrix() + " €.");
		}else if (chProd==2){
			this.getProduits().get(chProd-1);
			System.out.println ("=============Détails du produit============="+"\nRef : " + this.getProduits().get(chProd-1).getRef() + "\nDescription : " + this.getProduits().get(chProd-1).getDesc() +"\nNom : " + this.getProduits().get(chProd-1).getNom()+ "\nDescription détaillée : " + this.getProduits().get(chProd-1).getDescLongue() + "\nPrix : " + this.getProduits().get(chProd-1).getPrix() + " €.");
		}
			AfficherCat();
		}
		
	

		
//****************************MODIFICATION DU PRODUIT**************************
	
	public void modifAttribut(int i, int j) {
		Scanner sc = new Scanner(System.in);
		/*if (j==1){
			System.out.println("Entrez la nouvelle référence :");
			String d = sc.nextLine();
			this.getProduits().get(i-1).setRef(d);	
		}else */
		if (j==1){
			System.out.println("Entrez la nouvelle description :");
			String d = sc.nextLine();
			this.getProduits().get(i-1).setDesc(d);
			this.afficherProduitModifie(i);
		}else if (j==2){
			System.out.println("Entrez le nouveau nom :");
			String t = sc.nextLine();
			this.getProduits().get(i-1).setNom(t);
			this.afficherProduitModifie(i);
		}else if (j==3){
			System.out.println("Entrez la nouvelle description longue :");
			String r = sc.nextLine();
			this.getProduits().get(i-1).setDescLongue(r);
			this.afficherProduitModifie(i);
		}else if (j==4){
			System.out.println("Entrez le nouveau prix :");
			Float f = sc.nextFloat();
			this.getProduits().get(i-1).setPrix(f);
			this.afficherProduitModifie(i);
		}else if (j==5){
			this.afficherProduitChoisi(i);
		}
	}
	
//************************RECHERCHE D'UN PRODUIT PAR LA REF******************************
	
	public Produit retrieve(String ref){
		Iterator<Produit> it = this.getProduits().iterator();
		while(it.hasNext()){
			if(it.next().getRef() == ref){
				System.out.println("produit trouvé");
				return it.next();
			}
		}
		return null;
	}

//***************************AFFICHAGE DU PRODUIT MODIFIE**********************************
	
	public void afficherProduitModifie(int chProd) {
		this.getProduits().get(chProd-1);
		System.out.println ("==========Modification enregistrée !!=========="+"\nRef : " + this.getProduits().get(chProd-1).getRef() + "\nDescription : " + this.getProduits().get(chProd-1).getDesc() +"\nNom : " + this.getProduits().get(chProd-1).getNom()+ "\nDescription détaillée : " + this.getProduits().get(chProd-1).getDescLongue() + "\nPrix : " + this.getProduits().get(chProd-1).getPrix() + " €.");
		}
//***************************AJOUT PRODUIT*****************************************
	public void creerProduit(){
		System.out.println("Entrez la reference Produit : ");
		Scanner sm = new Scanner(System.in);
		String k = sm.nextLine();
		Produit produitCree = new Produit(k);
		ajouterProduit(produitCree);
		}

}
		
		
	
