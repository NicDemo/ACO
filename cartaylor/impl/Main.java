package fr.istic.derieux.demongeot.cartaylor.impl;

import java.util.HashSet;
import fr.istic.derieux.demongeot.cartaylor.api.Category;
import fr.istic.derieux.demongeot.cartaylor.api.CompatibilityChecker;
import fr.istic.derieux.demongeot.cartaylor.api.PartType;
/**
 * @author Nicolas Demongeot 
 * @author Jean Derieux
 *
 */
public class Main {

    public static HashSet<PartType> InitPartTypes(){
        HashSet<PartType> PartTypes = new HashSet<PartType>();
        PartTypes.add(PartTypeImpl.EH120);
        PartTypes.add(PartTypeImpl.TC120);
        PartTypes.add((PartTypeImpl.ED110));
        PartTypes.add(PartTypeImpl.ED180);
        PartTypes.add(PartTypeImpl.EG100);
        PartTypes.add(PartTypeImpl.EG133);
        PartTypes.add(PartTypeImpl.EG210);
        PartTypes.add(PartTypeImpl.IH);
        PartTypes.add(PartTypeImpl.IN);
        PartTypes.add(PartTypeImpl.IS);
        PartTypes.add(PartTypeImpl.TA5);
        PartTypes.add(PartTypeImpl.TM5);
        PartTypes.add(PartTypeImpl.TM6);
        PartTypes.add(PartTypeImpl.TSF7);
        PartTypes.add(PartTypeImpl.TS6);
        PartTypes.add(PartTypeImpl.XC);
        PartTypes.add(PartTypeImpl.XM);
        PartTypes.add(PartTypeImpl.XS);
        return PartTypes;
    }

    public static void main(String[] args) {

       Category cat = CategoryImpl.ENGINE;
        System.out.println(cat.getName());
        CompatibilityChecker comp = new CompatibilityManagerImpl();
        comp.init();//verifier pb may be

        }
    }