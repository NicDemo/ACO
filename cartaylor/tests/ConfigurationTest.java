package fr.istic.derieux.demongeot.cartaylor.tests;
import fr.istic.derieux.demongeot.cartaylor.api.*;
import fr.istic.derieux.demongeot.cartaylor.impl.CategoryImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.CompatibilityManagerImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.ConfigurationImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.PartTypeImpl;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ConfigurationTest {

    private CompatibilityChecker comp = new CompatibilityManagerImpl();

    @BeforeEach
    void setup(){
        comp.init();
    }
    
    
    @Test
    @DisplayName("Config without selected PartType")
    void test01() {
    	Configuration configTest = new ConfigurationImpl(comp);
    	assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Config valide mais non complete")
    void test02() throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.ED110);
        configTest.selectPart(PartTypeImpl.TM5);
        configTest.selectPart(PartTypeImpl.XC);
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    
    @Test
    @DisplayName("Test d'une config avec uniquement EH120, sachant que EH120 requiert un TC120 ")
    void test03() throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.EH120);
        assertFalse(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test d'une config avec EH120 et TC120")
    void test04() throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.EH120);
        configTest.selectPart(PartTypeImpl.TC120);
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test d'une config avec deux Engine")
    void test05() throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.EG133);
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }

    @Test
    @DisplayName("Test d'une config avec TA5, sachant que TA5 est incompatible avec EG100")
    void test06() throws Exception{
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        assertFalse(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test d'une config avec TA5, sachant que TA5 est incompatible avec EG100. Mais on remplace EG100 avec EG133")
    void test07()throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.EG133);
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test d'une config complete et valide apres remplacement d'un engine")
    void test08() throws Exception{
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.EG133);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.IS);
        assertTrue(configTest.isValid());
        assertTrue(configTest.isComplete());
    }

    @Test
    @DisplayName("Test d'une config complete mais non valide")
    void test09()throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.EG133);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.IS);
        assertFalse(configTest.isValid());
        assertTrue(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Configuration valide et complete")
    void test10()throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG133);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.IS);
        assertTrue(configTest.isValid());
        assertTrue(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test de clear sur une configuration valide et complete")
    void test11()throws Exception {
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG133);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.IS);
        assertTrue(configTest.isValid());
        assertTrue(configTest.isComplete());
        configTest.clear();
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test de clear sur une configuration invalide et complete")
    void test12() throws Exception{
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.IS);
        assertFalse(configTest.isValid());
        assertTrue(configTest.isComplete());
        configTest.clear();
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    
    @Test
    @DisplayName("Test de unselectedPartType, permettant de retirer l'element incompatible d'une config pour rendre celle ci valide")
    void test13() throws Exception{
    	Configuration configTest = new ConfigurationImpl(comp);
    	
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.IS);
        assertFalse(configTest.isValid());
        assertTrue(configTest.isComplete());
        configTest.unselectPartType(CategoryImpl.ENGINE);
        assertTrue(configTest.isValid());
        assertFalse(configTest.isComplete());
    }
    @Test
    @DisplayName("Test de robustesse de unselectedPartType," +
            " on retire un element qui n'existe pas si ça crash pas on verifie que le config est tjrs compelete et valide ")
            void test14() throws  Exception{
        Configuration configTest = new ConfigurationImpl(comp);
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.unselectPartType(CategoryImpl.ENGINE);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(PartTypeImpl.IS);
        assertFalse(configTest.isValid());
        assertTrue(configTest.isComplete());
    }

    @Test
    @DisplayName("Test de robustesse de select PartType,on  choose  le partType null")
    void test15()throws Exception{
        Configuration configTest = new ConfigurationImpl(comp);
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.selectPart(null);
        assertFalse(configTest.isValid());
        assertFalse(configTest.isComplete());
    }

    @Test
    @DisplayName("Test de robustesse de unselect PartType,on  enleve  le partType null")
    void test16()throws Exception{
        Configuration configTest = new ConfigurationImpl(comp);
        configTest.selectPart(PartTypeImpl.TA5);
        configTest.selectPart(PartTypeImpl.EG100);
        configTest.selectPart(PartTypeImpl.XM);
        configTest.unselectPartType(null);
        assertFalse(configTest.isValid());
        assertFalse(configTest.isComplete());
    }

    @Test
    @DisplayName("Test de fonctionnalité getselectionforcategory")
    void test17()throws Exception{
        Configuration configTest = new ConfigurationImpl(comp);
        configTest.selectPart(PartTypeImpl.EG100);
        assertTrue(PartTypeImpl.EG100.equals(configTest.getSelectionForCategory(CategoryImpl.ENGINE)));
    }
    @Test
    @DisplayName("test robustesse getselectionforcategory")void test18()
            throws Exception {
        Configuration configTest = new ConfigurationImpl(comp);
        assertTrue(null ==configTest.getSelectionForCategory(null));
        assertTrue(null==(configTest.getSelectionForCategory(CategoryImpl.ENGINE)));
    }
    @Test
    @DisplayName("test robustesse getselectionforcategory avec un config crée avec ref null")
    void test19()throws Exception {
        Configuration configTest = new ConfigurationImpl(null);
        assertTrue(null ==configTest.getSelectionForCategory(null));
        assertTrue(null==(configTest.getSelectionForCategory(CategoryImpl.ENGINE)));
    }

 @Test
@DisplayName("get category de null")
void test20(){
    ConfigurationImpl C = new ConfigurationImpl(comp);
    C.getSelectionForCategory(null);
        }
}
