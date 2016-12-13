/**
 * 
 */

package org.iae.annecy.st1.etape1.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.iae.annecy.st1.common.mvc.Controller;
import org.iae.annecy.st1.common.mvc.DataParam;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.Model;

/**
 * Controller principal permetant de traiter les actions de l'exemple et ayant
 * acces aux modeles d'exemple.
 * 
 * @author Djer1013
 * 
 */
public class MainController implements Controller {

	/**
	 * Liste des modeles accesible via ce controller.
	 */
	private transient static Map<String, Model> models = new ConcurrentHashMap<String, Model>();

	/**
	 * Initialise le stockage des modeles accesibles.
	 */
	public MainController() {
		super();
		MainController.models = new ConcurrentHashMap<String, Model>();
	}

	/**
	 * Check Interface. {@inheritDoc}
	 */
	public DataView get(String actionName) {
		final Model model = models.get(actionName);
		return getData(model);
	}

	/**
	 * Check Interface. {@inheritDoc}
	 */
	public static DataView get(final String actionName, DataParam params) {
		final Model model = models.get(actionName);
		return getData(model, params);
	}

	/**
	 * Check Interface. {@inheritDoc}
	 */
	public void add(final String name, final Model model) {
		models.put(name, model);
	}

	private static DataView getData(final Model model) {
		DataView datas;
		datas = model.get();
		return datas;
	}

	private static DataView getData(final Model model, DataParam params) {
		DataView datas;
		datas = model.get(params);
		return datas;
	}

}
