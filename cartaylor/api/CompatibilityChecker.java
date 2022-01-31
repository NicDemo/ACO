package fr.istic.derieux.demongeot.cartaylor.api;

import java.util.Set;

/**
 * 
 * Interface that defines methods specific to CompatibilityChecker
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 *
 */
public interface CompatibilityChecker {

	/**
	 * This method makes it possible to obtain the incompatibilities of a PartType
	 *
	 * @param reference The reference whose incompatibilities we want.
	 * 
	 * @return The Set of PartType that are incompatible with the PartType
	 *         reference.
	 */
	Set<PartType> getIncompatibilities(PartType reference);

	/**
	 * To obtain the requirements of a PartType
	 *
	 * @param reference The reference whose requirements we want
	 * @return The PartType's set dependencies of the PartType references
	 */
	Set<PartType> getRequirements(PartType reference);

	/**
	 * Initialise the dependencies
	 */
	void init();

}
