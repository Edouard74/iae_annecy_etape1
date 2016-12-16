package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Produit implements Serializable {
	private String ref, desc, nom, descLongue;
	private float prix;
	private int quantite;
	
	
//***********GETTERS ET SETTERS**************

	public String getRef() {
		return ref;
	}

	public int getQuantite() {
		return quantite;
	}

	public String getDesc() {
		return desc;
	}

	public float getPrix() {
		return prix;
	}

	public String getNom() {
		return nom;
	}

	public String getDescLongue() {
		return descLongue;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDescLongue(String descLongue) {
		this.descLongue = descLongue;
	}

//************* CONSTRUCTEUR DE PRODUITS *****************

	public Produit() {
	}

	public Produit(String re) {
		this.ref = re;
	}

	public Produit(String re, String de) {
		this(re);
		this.desc = de;
	}

	public Produit(String re, String n, String de, String del, float pr) {
		this(re, de);
		this.prix = pr;
		this.nom = n;
		this.descLongue = del;
	}

//*************** AFFICHAGE D'UN PRODUIT *******************	

	public void afficherProduit() {
		ConsoleHelper.display("Ref produit : " + this.getRef() + ".		Nom du produit : " + this.getNom()
				+ ".	 Descriptif produit : " + this.getDesc() + ".	 Description détaillée du produit : "
				+ this.getDescLongue() + ".	 Prix produit : " + this.getPrix() + " €.");
	}
}
