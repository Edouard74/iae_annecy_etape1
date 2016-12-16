package org.iae.annecy.st1.etape1.model.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Client implements Serializable {
	private String nom, prenom;
	private int numeroClient, codePromotionnel;

//*************** GETTERS ET SETTERS ****************

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getNumeroClient() {
		return numeroClient;
	}

	public int getCodePromotionnel() {
		return codePromotionnel;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNumeroClient(int numeroClient) {
		this.numeroClient = numeroClient;
	}

	public void setCodePromotionnel(int codePromotionnel) {
		this.codePromotionnel = codePromotionnel;
	}

//************************** CONSTRUCTEUR ***************************	

	public Client() {
	}

	public Client(String nom, String prenom, int numeroClient, int codePromotionnel) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroClient = numeroClient;
		this.codePromotionnel = codePromotionnel;
	}

//********************** AFFICHAGE D'UN CLIENT ************************

	public void afficherClient() {
		ConsoleHelper
				.display("Nom : " + this.getNom() + "		Prénom : " + this.getPrenom() + "		Numéro Client : "
						+ this.getNumeroClient() + "	Code promotionnel : " + this.getCodePromotionnel() + " %.");
	}
}