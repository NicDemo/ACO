package fr.istic.derieux.demongeot.cartaylor.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.derieux.demongeot.cartaylor.impl.PartTypeImpl;



import static org.junit.jupiter.api.Assertions.*;

public class PartTypeImplTest {

    @Test
    @DisplayName("test get description")
    void test0() {
        assertEquals(PartTypeImpl.ED110.getDescription(),"Diesel, 110kW");
    }

}
