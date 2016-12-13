package org.iae.annecy.st1.etape1.model;

import org.iae.annecy.st1.common.mvc.Controller;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.Model;
import org.iae.annecy.st1.etape1.model.produit.Catalogue;

public class CatalogueModel{
	
	Catalogue cat = new Catalogue();
	public CatalogueModel(Catalogue c){
		this.cat=c;
	}
	//public String get(){
	//	return cat.AfficherCat();
	//}
	public DataView get() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	public void register(Controller controller) {
		// TODO Auto-generated method stub
	}
}
