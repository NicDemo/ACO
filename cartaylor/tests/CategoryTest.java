package fr.istic.derieux.demongeot.cartaylor.tests;
import fr.istic.derieux.demongeot.cartaylor.api.*;
import fr.istic.derieux.demongeot.cartaylor.impl.CategoryImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {
    @Test
    @DisplayName("Category")
    void test0(){

        assertTrue(CategoryImpl.ENGINE.getName().equals("Engine"));
        assertTrue(CategoryImpl.EXTERIOR.getName().equals("Exterior"));
        assertTrue(CategoryImpl.INTERIOR.getName().equals("Interior"));
        assertTrue(CategoryImpl.TRANSMISSION.getName().equals("Transmission"));
    }
}
