package fr.istic.derieux.demongeot.cartaylor.api;

import java.util.Optional;
import java.util.Set;

/**
 * Interface that defines methods specific to Configuration
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */
public interface Configuration {

	/**
	 * To check if a configuration is valid
	 *
	 * @return true if the configuration is a valid one
	 */
	boolean isValid();

	/**
	 * To check if a configuration is complete
	 *
	 * @return true if the configuration is complete,every part is selected
	 */
	boolean isComplete();

	/**
	 * To get the PartType's set of the configuration
	 *
	 * @return the Part's Set of the configuration
	 */
	Set<PartType> getSelectedParts();

	/**
	 * To add the chosen PartType to the current Configuration
	 *
	 * @param chosenPart The chosenPArt we want to add to the current configuration
	 * 
	 * @throws Exception exception
	 */
	void selectPart(PartType chosenPart) throws Exception;

	/**
	 * To get the selected PartType of the category
	 *
	 * @param category The category to which we want to get the selection
	 * @return PartType from the Category category
	 */
	PartType getSelectionForCategory(Category category);

	/**
	 * To unselect the selected PartType of a Category
	 *
	 * @param categoryToClear the category to clear
	 */
	void unselectPartType(Category categoryToClear);

	/**
	 * Reset the Configuration
	 */
	void clear();

}
