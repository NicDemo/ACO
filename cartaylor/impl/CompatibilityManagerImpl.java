package fr.istic.derieux.demongeot.cartaylor.impl;

import java.util.*;

import fr.istic.derieux.demongeot.cartaylor.api.CompatibilityManager;
import fr.istic.derieux.demongeot.cartaylor.api.PartType;

/**
 * class to implements the CompatibilityManage interface
 * 
 * @author Nicolas Demongeot
 * @author Jean Derieux
 */
public class CompatibilityManagerImpl implements CompatibilityManager {

	private HashMap<PartType, Set<PartType>> incompatibilities;
	private HashMap<PartType, Set<PartType>> requirements;

	public CompatibilityManagerImpl() {
		this.requirements = new HashMap<PartType, Set<PartType>>();
		this.incompatibilities = new HashMap<PartType, Set<PartType>>();
	}

	/**
	 * This method is useful to add incompatibilities to a PartType
	 * 
	 * Side effect, add incompatibilities between the PartType reference and the
	 * target partType's set
	 *
	 * @param reference the reference to which we want to add incompatibilities
	 * @param target    the incompatibilities that we want to add to the reference
	 */
	@Override
	public void addIncompatibilities(PartType reference, Set<PartType> target) {
		if (reference != null && target != null) {
			if (this.incompatibilities.containsKey(reference)) {
				Set<PartType> Set = target;
				Set.addAll(this.incompatibilities.get(reference));
				incompatibilities.put(reference, Set);
			} else {
				incompatibilities.put(reference, target);
			}
		}
	}

	/**
	 * This method is useful to remove an incompatibility from a PartType
	 *
	 * @param reference the reference to which we want to remove an incompatibility
	 * @param target    the incompatibility that we want to remove to the reference
	 */
	@Override
	public void removeIncompatibility(PartType reference, PartType target) {
		if (reference != null && target != null) {
			if (incompatibilities.containsKey(reference)) {
				incompatibilities.get(reference).remove(target);
				if (incompatibilities.get(reference).isEmpty()) {
					// after target has been removed the key point to a empty set so we kick the
					// useless key out
					incompatibilities.remove(reference);
				}
			}
		}
	}

	/**
	 * Add requirements to a PartType
	 * 
	 * @param reference the reference to which we want to add requirements
	 * @param target    the requirements that we want to add to the reference
	 */
	@Override
	public void addRequirements(PartType reference, Set<PartType> target) {
		if (reference != null && target != null) {
			if (requirements.containsKey(reference)) {
				Set<PartType> to_add = target;
				to_add.addAll(requirements.get(reference));
				requirements.replace(reference, to_add);
			} else {
				requirements.put(reference, target);
			}
		}
	}

	@Override
	public void removeRequirement(PartType reference, PartType target) {
		if (reference != null && target != null) {
			if (requirements.containsKey(reference)) {
				requirements.remove(target);
			}
		}
	}

	/**
	 * This method makes it possible to obtain the incompatibilities of a PartType
	 *
	 * @param reference The reference whose incompatibilities we want.
	 * 
	 * @return The Set of PartType that are incompatible with the PartType
	 *         reference.
	 */
	@Override
	public Set<PartType> getIncompatibilities(PartType reference) {
		if (reference != null && this.incompatibilities.get(reference) != null) {
			return this.incompatibilities.get(reference);
		}
		HashSet<PartType> a = new HashSet<PartType>();
		return a;
	}

	/**
	 * To obtain the requirements of a PartType
	 *
	 * @param reference The reference whose requirements we want
	 * 
	 * @return The PartType's set dependencies of the PartType references
	 */
	@Override
	public Set<PartType> getRequirements(PartType reference) {
		if (reference != null && this.requirements.get(reference) != null) {
			return this.requirements.get(reference);
		}
		HashSet<PartType> a = new HashSet<PartType>();
		return a;
	}

	/**
	 * Initialise the dependencies
	 */
	@Override
	public void init() {
		requirements.put(PartTypeImpl.EH120, new HashSet<PartType>(Collections.singleton(PartTypeImpl.TC120)));
		incompatibilities.put(PartTypeImpl.TA5, new HashSet<PartType>(Collections.singleton(PartTypeImpl.EG100)));
		Set<PartType> Set = new HashSet<>(Arrays.asList(PartTypeImpl.EG100, PartTypeImpl.EG133, PartTypeImpl.ED110));
		incompatibilities.put(PartTypeImpl.TSF7, Set);
		requirements.put(PartTypeImpl.TC120, new HashSet<>(Collections.singleton(PartTypeImpl.EH120)));
		incompatibilities.put(PartTypeImpl.XC, Collections.singleton(PartTypeImpl.EG100));
		incompatibilities.put(PartTypeImpl.XM, Collections.singleton(PartTypeImpl.EG100));
		incompatibilities.put(PartTypeImpl.XS, Collections.singleton(PartTypeImpl.EG100));
		requirements.put(PartTypeImpl.XS, Collections.singleton(PartTypeImpl.IS));
		Set<PartType> Set1 = new HashSet<>(Arrays.asList(PartTypeImpl.EG100, PartTypeImpl.TM5));
		incompatibilities.put(PartTypeImpl.IS, Set1);
	}
}