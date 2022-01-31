package fr.istic.derieux.demongeot.cartaylor.api;

import java.util.Set;

/**
 * Interface that defines methods specific to CompatibilityManager
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */
public interface CompatibilityManager extends CompatibilityChecker {

	/**
	 * This method is useful to add incompatibilities to a PartType
	 * 
	 * Side effect, add incompatibilities between the PartType reference and the
	 * target partType's set
	 *
	 * @param reference the reference to which we want to add incompatibilities
	 * @param target    the incompatibilities that we want to add to the reference
	 */
	void addIncompatibilities(PartType reference, Set<PartType> target);

	/**
	 * This method is useful to remove an incompatibility from a PartType
	 *
	 * @param reference the reference to which we want to remove an incompatibility
	 * @param target    the incompatibility that we want to remove to the reference
	 */
	void removeIncompatibility(PartType reference, PartType target);

	/**
	 * Add requirements to a PartType
	 * 
	 * @param reference the reference to which we want to add requirements
	 * @param target    the requirements that we want to add to the reference
	 */
	void addRequirements(PartType reference, Set<PartType> target);

	/**
	 * Remove a requirement from a PartType
	 * 
	 * @param reference the reference to which we want to remove a requirement
	 * @param target    the requirement that we want to remove to the reference
	 */
	void removeRequirement(PartType reference, PartType target);

}
