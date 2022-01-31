package fr.istic.derieux.demongeot.cartaylor.tests;

import org.junit.jupiter.api.Test;

import fr.istic.derieux.demongeot.cartaylor.api.*;
import fr.istic.derieux.demongeot.cartaylor.impl.CompatibilityManagerImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.PartTypeImpl;

import org.junit.jupiter.api.DisplayName;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Nicolas Demongeot 
 * @author Jean Derieux
 *
 */
public class CompatibilityCheckerTest {


    CompatibilityChecker checker = new CompatibilityManagerImpl();


    @Test
    @DisplayName("checker est bien vide a l'initialisation")
    public void test1() {
        HashSet<PartType> vide= new HashSet<>();
        assertEquals(vide, checker.getIncompatibilities(null));
        assertEquals(vide, checker.getIncompatibilities(PartTypeImpl.EG100));
        assertEquals(vide, checker.getRequirements(null));
        assertEquals(vide, checker.getRequirements(PartTypeImpl.EG100));

    }

    @Test
    @DisplayName("test fonctionnalité get>Incompat et get req")
    public void test2 (){
        checker.init();
        HashSet<PartType> XSReq= new HashSet<>();
        XSReq.add(PartTypeImpl.IS);
        HashSet<PartType> XSIncompat= new HashSet<>();
        XSIncompat.add(PartTypeImpl.EG100);
        assertEquals(XSReq, checker.getRequirements(PartTypeImpl.XS));
        assertEquals(XSIncompat, checker.getIncompatibilities(PartTypeImpl.XS));

    }
}
