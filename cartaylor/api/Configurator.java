package fr.istic.derieux.demongeot.cartaylor.api;

import java.util.Set;

/**
 * Interface that defines methods specific to Configurator
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */
public interface Configurator {
	/**
	 * To get the set of Category
	 *
	 * @return the set containing the different categories
	 */
	Set<Category> getCategories();

	/**
	 * To get the set of PartType
	 *
	 * @param category the category to which we want all the variants
	 * 
	 * @return the set of PartType for this category
	 */
	Set<PartType> getVariants(Category category);

	/**
	 * To get the configuration
	 *
	 * @return the current configuration
	 */
	Configuration getConfiguration();

	/**
	 * To get the CompatibilityChecker
	 *
	 * @return the CompatibilityChecker
	 */
	CompatibilityChecker getCompatibilityChecker();

}
