package fr.istic.derieux.demongeot.cartaylor.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.derieux.demongeot.cartaylor.api.Category;
import fr.istic.derieux.demongeot.cartaylor.api.CompatibilityChecker;
import fr.istic.derieux.demongeot.cartaylor.api.CompatibilityManager;
import fr.istic.derieux.demongeot.cartaylor.api.Configurator;
import fr.istic.derieux.demongeot.cartaylor.api.PartType;
import fr.istic.derieux.demongeot.cartaylor.impl.CategoryImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.CompatibilityManagerImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.ConfigurationImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.ConfiguratorImpl;
import fr.istic.derieux.demongeot.cartaylor.impl.Main;
import fr.istic.derieux.demongeot.cartaylor.impl.PartTypeImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ConfiguratorTest {

	private final CompatibilityChecker comp = new CompatibilityManagerImpl();
	private final Configurator configurator = new ConfiguratorImpl(Main.InitPartTypes(), comp,
			new ConfigurationImpl(comp));
	private CompatibilityManager manager = new CompatibilityManagerImpl();

	// __________________TEST
	// CONFIGURATOR____________________________________________
	@BeforeEach
	void setup() {
		comp.init();
		manager.init();
	}

	@Test
	@DisplayName("Test configurator 0")
	void test00() {
		Set<Category> cat = new HashSet<>();
		cat.add(CategoryImpl.INTERIOR);
		cat.add(CategoryImpl.TRANSMISSION);
		cat.add(CategoryImpl.EXTERIOR);
		cat.add(CategoryImpl.ENGINE);
		assertTrue(cat.equals(configurator.getCategories()));

	}

	// ____________________Test
	// Configuration____________________________________________________________
	@Test
	@DisplayName("___________________________test Configuration____________________________")
	void test0() {
		assertTrue(configurator.getConfiguration().isValid());
		assertFalse(configurator.getConfiguration().isComplete());

	}

	@Test
	@DisplayName("test 1")
	void test1()throws Exception {
		configurator.getConfiguration().selectPart(PartTypeImpl.ED110);
		configurator.getConfiguration().selectPart(PartTypeImpl.TM5);
		assertTrue(configurator.getConfiguration().isValid());
		assertFalse(configurator.getConfiguration().isComplete());

	}

	@Test
	@DisplayName("ajout d'incompatibilité entre les deux partTypes")
	void test1bis() throws Exception {

		manager.addIncompatibilities(PartTypeImpl.ED110, new HashSet<>(Collections.singleton(PartTypeImpl.TM5)));
		manager.addRequirements(PartTypeImpl.TM5, new HashSet<>(Collections.singleton(PartTypeImpl.IS)));
		Configurator configman = new ConfiguratorImpl(Main.InitPartTypes(), manager, new ConfigurationImpl(manager));
		configman.getConfiguration().selectPart(PartTypeImpl.TM5);
		configman.getConfiguration().selectPart(PartTypeImpl.ED110);
		assertFalse(configman.getConfiguration().isValid());

	}

	@Test
	@DisplayName("test 2 ")
	void test2()throws Exception {
		configurator.getConfiguration().selectPart(PartTypeImpl.ED110);
		configurator.getConfiguration().selectPart(PartTypeImpl.TM5);
		configurator.getConfiguration().selectPart(PartTypeImpl.XC);
		assertTrue(configurator.getConfiguration().isValid());
		assertFalse(configurator.getConfiguration().isComplete());
	}

	@Test
	@DisplayName("test 3")
	void test3valid()throws Exception {
		configurator.getConfiguration().selectPart(PartTypeImpl.ED110);
		configurator.getConfiguration().selectPart(PartTypeImpl.TM5);
		configurator.getConfiguration().selectPart(PartTypeImpl.XC);
		configurator.getConfiguration().selectPart(PartTypeImpl.IN);
		assertTrue(configurator.getConfiguration().isValid());
		assertTrue(configurator.getConfiguration().isComplete());
	}

	@Test
	@DisplayName("test 4")
	void test4()throws Exception {
		configurator.getConfiguration().selectPart(PartTypeImpl.ED110);
		configurator.getConfiguration().selectPart(PartTypeImpl.TM5);
		configurator.getConfiguration().selectPart(PartTypeImpl.XC);
		configurator.getConfiguration().selectPart(PartTypeImpl.IN);
		configurator.getConfiguration().selectPart(PartTypeImpl.EG210);
		assertTrue(configurator.getConfiguration().isValid());
		assertTrue(configurator.getConfiguration().isComplete());
	}

	@Test
	@DisplayName("test 5 complet nn valide ")
	public void test5() throws Exception{
		configurator.getConfiguration().selectPart(PartTypeImpl.EG100);
		configurator.getConfiguration().selectPart(PartTypeImpl.TM5);
		configurator.getConfiguration().selectPart(PartTypeImpl.XS);
		configurator.getConfiguration().selectPart(PartTypeImpl.IS);
		assertFalse(configurator.getConfiguration().isValid());
		assertTrue(configurator.getConfiguration().isComplete());
	}

	@Test
	@DisplayName("test 6  complet nn valide")
	void test6() throws Exception {
		// verifier pb may be
		configurator.getConfiguration().selectPart(PartTypeImpl.ED110);
		configurator.getConfiguration().selectPart(PartTypeImpl.TM5);
		configurator.getConfiguration().selectPart(PartTypeImpl.XC);
		configurator.getConfiguration().selectPart(PartTypeImpl.IS);
		assertTrue(configurator.getConfiguration().isComplete());
		assertFalse(configurator.getConfiguration().isValid());
	}

	@Test
	@DisplayName("get Variant rend bien ce que l'on attends")
	void test7() {
		Set<PartType> Set = new HashSet<>();
		Set.add(PartTypeImpl.EG100);
		Set.add(PartTypeImpl.EG133);
		Set.add(PartTypeImpl.EG210);
		Set.add(PartTypeImpl.ED110);
		Set.add(PartTypeImpl.ED180);
		Set.add(PartTypeImpl.EH120);
		;
		assertTrue(configurator.getVariants(CategoryImpl.ENGINE).equals(Set));
	}

	@Test
	void test8() {
		Set<PartType> Set = new HashSet<>();
		assertFalse(configurator.getVariants(CategoryImpl.ENGINE).equals(Set));
		Set.add(PartTypeImpl.EG100);
		Set.add(PartTypeImpl.EG133);
		Set.add(PartTypeImpl.EG210);
		Set.add(PartTypeImpl.ED110);
		Set.add(PartTypeImpl.ED180);
		Set.add(PartTypeImpl.EH120);
		;
		assertFalse(configurator.getVariants(CategoryImpl.INTERIOR).equals(Set));
	}

	@Test
	@DisplayName("test getcompatibilitychecker")
	void test9(){
	assertEquals(configurator.getCompatibilityChecker(),comp);
	}

	/*
	 * EXEMPLE
	 * 
	 * class AddressBookTest {
	 * 
	 * private AddressBookImpl book;
	 * 
	 * @BeforeEach void setUp() throws Exception { book = new AddressBookImpl();
	 * book.addNewEntry("Doe", "Jane", "1234", "there@none");
	 * book.addNewEntry("Doe", "Jim", "1234", "here@none"); book.addNewEntry("Kent",
	 * "Clark", "911", "superman@krypton.andromeda.space"); book.addNewEntry("Doe",
	 * "Jim", "9999", "here@none"); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find first one that exists") void testFindFirst1() {
	 * Optional<Entry> result = book.findFirst("Doe", "Jane");
	 * assertTrue(result.isPresent()); assertEquals("Doe",
	 * result.get().getFamilyName()); assertEquals("Jane",
	 * result.get().getFirstName()); assertEquals("1234",
	 * result.get().getPhoneNumber()); assertEquals("there@none",
	 * result.get().getEmailAddress()); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find first one with a first name that does not exist in book")
	 * void testFindFirst2() { Optional<Entry> result = book.findFirst("Doe",
	 * "Margaret"); assertFalse(result.isPresent()); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find first one with a family name that does not exist in book")
	 * void testFindFirst3() { Optional<Entry> result = book.findFirst("Lovelace",
	 * "Jane"); assertFalse(result.isPresent()); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Find all for names that exist") void testFindAll1() {
	 * Collection<Entry> result; result = book.findAll("Doe", "Jim");
	 * assertEquals(2, result.size()); // Note the use of a stream to check for all
	 * values assertTrue(result.stream() .allMatch((Entry e) ->
	 * ((e.getFamilyName().equals("Doe") && (e.getFirstName().equals("Jim")))))); }
	 * 
	 */

}
