/**
 * 
 */

package org.iae.annecy.st1.common.mvc;

/**
 * Défini le comportement des controllers.
 * 
 * @author Djer1013
 */
public interface Controller {
	/**
	 * Recherche et retourne les données à representer pour une action
	 * 
	 * @param actionName
	 *            Action que doit traiter le modele associé.
	 * @return Une representation des données généré par l'action.
	 */
	 DataView get(String actionName);

	/**
	 * Ajoute la compacité à ce controller de traiter une nouvelle action
	 * 
	 * @param actionName
	 *            Nom de l'action qui pourra être traitée
	 * @param model
	 *            Modele responsable d'effectuer l'action.
	 */
	void add(String actionName, Model model);
}
