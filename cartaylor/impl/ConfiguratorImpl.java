package fr.istic.derieux.demongeot.cartaylor.impl;

import java.util.HashSet;
import java.util.Set;

import fr.istic.derieux.demongeot.cartaylor.api.Category;
import fr.istic.derieux.demongeot.cartaylor.api.CompatibilityChecker;
import fr.istic.derieux.demongeot.cartaylor.api.Configuration;
import fr.istic.derieux.demongeot.cartaylor.api.Configurator;
import fr.istic.derieux.demongeot.cartaylor.api.PartType;

/**
 * class to implements Configurator interface
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */
public class ConfiguratorImpl implements Configurator {
	
	Set<PartType> PartTypes;
	CompatibilityChecker comp;
	Configuration config;

	public ConfiguratorImpl(Set<PartType> Set, CompatibilityChecker comp, Configuration config) {
		this.PartTypes = Set;
		this.comp = comp;
		this.config = config;
	}

	/**
	 * To get the set of Category
	 *
	 * @return the set containing the different categories
	 */
	@Override
	public Set<Category> getCategories() {
		Set<Category> Set = new HashSet<>();
		Set.add(CategoryImpl.ENGINE);
		Set.add(CategoryImpl.EXTERIOR);
		Set.add(CategoryImpl.TRANSMISSION);
		Set.add(CategoryImpl.INTERIOR);
		return Set;
	}

	/**
	 * To get the set of PartType
	 *
	 * @param category the category to which we want all the variants
	 * 
	 * @return the set of PartType for this category
	 */
	@Override
	public Set<PartType> getVariants(Category category) {
		Set<PartType> Set = new HashSet<>();
		if (category != null) {
			for (PartType p : PartTypes) {
				if (p.getCategory().equals(category)) {
					Set.add(p);
				}
			}
		}
		return Set;
	}

	/**
	 * To get the configuration
	 *
	 * @return the current configuration
	 */
	@Override
	public Configuration getConfiguration() {
		return config;
	}

	/**
	 * To get the CompatibilityChecker
	 *
	 * @return the CompatibilityChecker
	 */
	@Override
	public CompatibilityChecker getCompatibilityChecker() {
		return comp;
	}

}
