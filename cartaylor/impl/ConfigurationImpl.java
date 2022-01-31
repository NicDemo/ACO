package fr.istic.derieux.demongeot.cartaylor.impl;

import java.util.*;

import fr.istic.derieux.demongeot.cartaylor.api.*;

/**
 * class to implements Configuration interface
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */
public class ConfigurationImpl implements Configuration {

	private HashSet<PartType> selection;
	private HashSet<Category> categoryselected;
	private CompatibilityChecker comp;

	/**
	 * ConfigurationImpl constructor If param comp is null we create a new
	 * compatibilityChecker
	 *
	 * @param comp the CompatibilityChecker
	 */
	public ConfigurationImpl(CompatibilityChecker comp) {
		selection = new HashSet<PartType>();
		categoryselected = new HashSet<Category>();
		if (comp != null) {
			this.comp = comp;
		} else {
			this.comp = new CompatibilityManagerImpl();
		}
	}

	/**
	 * To check if a configuration is valid
	 *
	 * @return true if the configuration is a valid one
	 */
	@Override
	public boolean isValid() throws NullPointerException {
		for (PartType p : selection) {
			if (comp.getRequirements(p) != null && !this.getSelectedParts().containsAll(comp.getRequirements(p))) {
				// we don't have all the necessary requirements for the current selection
				return false;
			}

			for (PartType p2 : selection) {
				if (comp.getIncompatibilities(p2) != null && comp.getIncompatibilities(p).contains(p2)) {
					// we have some incompatibilities between the parTypes selected
					return false;
				}
			}
		}
		return true; // all's good
	}

	/**
	 * To check if a configuration is complete
	 *
	 * @return true if the configuration is complete,every part is selected
	 */
	@Override
	public boolean isComplete() {// look at the iterator's position at the selection.iterator();
		if (selection.size() == 4) {
			Iterator<PartType> it = selection.iterator();
			boolean engine = false;
			boolean exterior = false;
			boolean interior = false;
			boolean transmission = false;
			String cat1 = it.next().getCategory().getName();
			String cat2 = it.next().getCategory().getName();
			String cat3 = it.next().getCategory().getName();
			String cat4 = it.next().getCategory().getName();
			if (cat1.equals("Engine") || cat2.equals("Engine") || cat3.equals("Engine") || cat4.equals("Engine")) {
				engine = true;
				// engine is selected
			}
			if (cat1.equals("Exterior") || cat2.equals("Exterior") || cat3.equals("Exterior")
					|| cat4.equals("Exterior")) {
				exterior = true;
				// exterior is selected
			}
			if (cat1.equals("Interior") || cat2.equals("Interior") || cat3.equals("Interior")
					|| cat4.equals("Interior")) {
				interior = true;
				// interior is selected
			}
			if (cat1.equals("Transmission") || cat2.equals("Transmission") || cat3.equals("Transmission")
					|| cat4.equals("Transmission")) {
				transmission = true;
				// transmission is selected
			}
			return transmission && interior && exterior && engine;
		}
		// Some Parts of the car are missing
		return false;
	}

	/**
	 * To get the PartType's set of the configuration
	 *
	 * @return the PartType's set of the configuration
	 */
	@Override
	public Set<PartType> getSelectedParts() {
		return selection;
	}

	/**
	 * To add the chosen PartType to the current Configuration
	 *
	 * @param chosenPart The chosenPArt we want to add to the current configuration
	 */
	@Override
	public void selectPart(PartType chosenPart) {// if the user add a PartType which category

		if (chosenPart != null) {

			// has already been selected
			Category category = chosenPart.getCategory();// then it remove the PartType and add the chosenPart
			if (categoryselected.contains(category)) {
				// old PartType from chosenPart.getCategory() is replace by chosenPart.getName()
				this.unselectPartType(category);
				selection.add(chosenPart);
				categoryselected.add(category);
			} else
				System.out.println(chosenPart.getName() + ": added");

			selection.add(chosenPart);
			categoryselected.add(chosenPart.getCategory());
		}
	}

	/**
	 * To get the selected PartType of the category
	 *
	 * @param category The category to which we want to get the selection
	 * @return PartType from the Category category
	 */
	@Override
	public PartType getSelectionForCategory(Category category) {
		if (categoryselected.contains(category) && category != null) {
			// return the ParType wanted
			for (PartType p : selection) {
				if (p.getCategory().equals(category)) {
					return p;
				}
			}
		} else {
			System.out.println("Warning : can't get PartTYpe u get a null ref");
		}
		return null;
	}

	/**
	 * To unselect the selected PartType of a Category
	 *
	 * @param categoryToClear the category to clear
	 */
	@Override
	public void unselectPartType(Category categoryToClear) {
		if (categoryToClear != null) {
			if (categoryselected.contains(categoryToClear)) {
				Iterator<PartType> it = selection.iterator();
				// searching for the PartType to remove
				PartType to_remove = it.next();
				Category category = to_remove.getCategory();
				while (!category.equals(categoryToClear)) {
					to_remove = it.next();
					category = to_remove.getCategory();
				}
				selection.remove(to_remove);
				categoryselected.remove(categoryToClear);
			}
		}
	}

	/**
	 * Reset the Configuration
	 */
	@Override
	public void clear() {
		// give work to the garbage collector
		selection = new HashSet<PartType>();
		categoryselected = new HashSet<Category>();
	}
}
