package fr.istic.derieux.demongeot.cartaylor.impl;

import fr.istic.derieux.demongeot.cartaylor.api.Category;

/**
 * enum that defines all possible Category
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */

public enum CategoryImpl implements Category {
	ENGINE("Engine"), TRANSMISSION("Transmission"), EXTERIOR("Exterior"), INTERIOR("Interior");

	private String name;

	CategoryImpl(String name) {
		this.name = name;
	}

	/**
	 * This method makes it possible to obtain the Category name.
	 * 
	 * @return the name of this category
	 */
	@Override
	public String getName() {
		return this.name;
	}

}
