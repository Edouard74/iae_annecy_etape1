/**
 * 
 */

package org.iae.annecy.st1.etape1;

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

import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.CatalogueModel;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.produit.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.tools.ConsoleHelper;

/**
 * Classe permetant de tester le MVC.
 * 
 * @author Djer1013
 */
public class Main implements Serializable{

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

		//final DataView userData = MainController.get("user:display");
		//final StringView userView = new UserTextFrenchView();

		//ConsoleHelper.display(userView.build(userData));
		
		
//*********************************DESERIALIZATION**********************************************
		Catalogue c1 = new Catalogue();
		Produit p1;
		Produit p2;
		      try {
		         FileInputStream fileIn = new FileInputStream("SauvegardeCatalogue.txt");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         c1 = (Catalogue) in.readObject();
		         in.close();
		         fileIn.close();
		      } catch(FileNotFoundException d) {
			         System.out.println("Le fichier n'est pas créé !");
			          p1= new Produit("001","Head First Java","Livre","",50);
			 		  p2 = new Produit("002","ASUS 752","Ordinateur","",1800);
			 		
			 		
			 		c1.ajouterProduit(p1);
			 		c1.ajouterProduit(p2);
			         //d.printStackTrace();
			       
		      }catch(IOException i) {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c) {
		         System.out.println("Le catalogue n'est pas créé !");
		         c.printStackTrace();
		       return;
		      }
		
		
		
//**********************************************************************************************		
		
		//c1.AfficherCat();
		//CatalogueModel catController = new CatalogueModel(c1);
		//System.out.println (catController.get());	
//*****************************AFFICHAGE DU CATALOGUE******************************************		
		System.out.println("=========== CATALOGUE GENERAL : ============");
		c1.AfficherCat();
				
//*******************************CHOIX DU PRODUIT**********************************************		
		System.out.println("=========== Choix du produit : ============");
		System.out.println("1: Livre");
	    System.out.println("2: Ordinateur");
	    System.out.println("3: Ajouter un produit");
	    System.out.println("Votre choix ?");
	    Scanner sc = new Scanner(System.in);
	    int i = sc.nextInt();
	    System.out.println("Vous avez fait le choix : " + i);
	    c1.afficherProduitChoisi(i);
	    
//*******************************SERIALIZATION*********************************************
  		try {
  			FileOutputStream fichier = new FileOutputStream("SauvegardeCatalogue.txt");
  	        ObjectOutputStream out = new ObjectOutputStream(fichier);
  	        out.writeObject(c1);
  	        out.close();
  	        fichier.close();
  	        System.out.printf("Catalogue Sauvegardé");
  	      }catch(IOException i1) {
  	         i1.printStackTrace();
  	        }	
	    
//***************************CHOIX DE LA MODIFICATION**************************************	    
	    System.out.println("==========Choix de la modification==========");
	    //System.out.println("1: Référence");
	    System.out.println("1: Description");
	    System.out.println("2: Nom");
	    System.out.println("3: Description longue");
	    System.out.println("4: Prix");
	    System.out.println("5: Détails du produit");
	    System.out.println("Votre choix ?");
	    int j = sc.nextInt();
	    System.out.println("Vous avez fait le choix : " + j);
		c1.modifAttribut(i,j);
	    
//*************************AFFICHAGE DU CATALOGUE******************************************		
		System.out.println("=======Voici le catalogue mis à jour :========");
		c1.AfficherCat();
	    

	
		
		//catController.get();
	    //c1.retrieve("001");
	  
	    
	}

	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
	}

}
