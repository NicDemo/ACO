package fr.istic.derieux.demongeot.cartaylor.api;


/**
 * Interface that defines methods specific to PartType
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 *
 */
public interface PartType {
	/**
	 * To get the name of this PartType
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * To get the category of this PartType
	 *
	 * @return the category
	 */
	Category getCategory();

	/**
	 * To get the description of this PartType
	 * 
	 * @return the description of partType this
	 */
	String getDescription();

}