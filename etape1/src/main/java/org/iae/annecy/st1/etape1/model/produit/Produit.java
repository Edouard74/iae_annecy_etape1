package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

public class Produit implements Serializable {
	private String ref,desc,nom,descLongue;
	private float prix;
	
	
//***********GETTERS ET SETTERS**************
	
	public String getRef() {
		return ref;
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
	
	
//*************CONSTRUCTEUR DE PRODUITS**********************
	
	public Produit(){
	}
	public Produit(String re, String n, String de, String del, float pr){
		this.ref = re;
		this.desc = de;
		this.prix = pr;
		this.nom = n;
		this.descLongue = del;
	}
	public Produit (String rf){
		this.ref = rf;
	}
	public Produit (String reff, String des){
		this.desc = des;
		this.ref = reff;
	}
//***********AFFICHAGE D'UN PRODUIT****************************	
	
	public void afficher(){
		System.out.println("Ref produit : " + this.getRef()+". Nom du produit : " + this.getNom()+". Descriptif produit : " + this.getDesc() +". Description détaillée du produit : " + this.getDescLongue() + ". Prix produit : " + this.getPrix()+" €.");
	}
	
		
	
}
