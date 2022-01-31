package fr.istic.derieux.demongeot.cartaylor.tests;
import fr.istic.derieux.demongeot.cartaylor.api.*;
import fr.istic.derieux.demongeot.cartaylor.impl.CompatibilityManagerImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.PartTypeImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CompatibilityManagerTest {

	CompatibilityManager compMa = new CompatibilityManagerImpl();




    @BeforeEach
    void setup(){
    	//compMa.init();
    }
    
    
	@Test
    @DisplayName("we check if without using .init() the set of incompatibilities is indeed empty")
    void test01() {	  
		
        HashSet<PartType> setInc = (HashSet<PartType>) compMa.getIncompatibilities(PartTypeImpl.EH120);
      

        assertTrue(setInc.isEmpty());

		

    }
	
	@Test
    @DisplayName("we add an incompatibility and we check if the set is not empty")
    void test02() {
    	
	
		compMa.addIncompatibilities(PartTypeImpl.EH120, new HashSet<PartType>(Collections.singleton(PartTypeImpl.EG100)));
		HashSet<PartType> setInc = (HashSet<PartType>) compMa.getIncompatibilities(PartTypeImpl.EH120);
        assertFalse(setInc.isEmpty());

		

    }
	
	@Test
    @DisplayName("we check if without using .init() the set of requirements is indeed empty")
    void test03() {	  
		
        HashSet<PartType> setReq = (HashSet<PartType>) compMa.getRequirements(PartTypeImpl.EH120);
      

        assertTrue(setReq.isEmpty());
        
    }
	
	@Test
    @DisplayName("we add an requirement and we check if the set is not empty")
    void test04() {
    	
	
		compMa.addRequirements(PartTypeImpl.EH120, new HashSet<PartType>(Collections.singleton(PartTypeImpl.EG100)));
		HashSet<PartType> setReq = (HashSet<PartType>) compMa.getRequirements(PartTypeImpl.EH120);
        assertFalse(setReq.isEmpty());

    }

    @Test
    @DisplayName("remove imcompatibility")
    void test5(){
        compMa.init();
        Set<PartTypeImpl> vide = new HashSet<>();
        compMa.removeIncompatibility(PartTypeImpl.TA5,PartTypeImpl.EG100);
        assertEquals(compMa.getIncompatibilities(PartTypeImpl.TA5),vide);

    }
    @Test
    @DisplayName("remove Req")
    void test6(){
        compMa.init();
        Set<PartTypeImpl> vide = new HashSet<>();
        compMa.removeRequirement(PartTypeImpl.EH120,PartTypeImpl.TC120);
        assertEquals(compMa.getRequirements(PartTypeImpl.TC120),vide);

    }
    @Test
    @DisplayName("ajout de req a un partype contenant deja des req")
    void t7(){
        compMa.addRequirements(PartTypeImpl.EH120,new HashSet<PartType>(Collections.singleton(PartTypeImpl.IS)));
        compMa.addRequirements(PartTypeImpl.EH120, new HashSet<PartType>(Collections.singleton(PartTypeImpl.EG100)));
    }
    @Test
    @DisplayName("ajout de incompat a une partype ayant deja des incompat")
    void t8(){
        compMa.addIncompatibilities(PartTypeImpl.EH120,new HashSet<PartType>(Collections.singleton(PartTypeImpl.IS)));
        compMa.addIncompatibilities(PartTypeImpl.EH120, new HashSet<PartType>(Collections.singleton(PartTypeImpl.EG100)));
    }
    @Test
    @DisplayName("coverageline+")
    void t9() {
        compMa.removeRequirement(null,null);
        compMa.removeIncompatibility(null,null);
        compMa.addRequirements(null,null);
    }
    


}
