package fr.istic.derieux.demongeot.cartaylor.impl;

import java.util.Objects;

import fr.istic.derieux.demongeot.cartaylor.api.Category;
import fr.istic.derieux.demongeot.cartaylor.api.PartType;

/**
 * @author Nicolas Demongeot 
 * @author Jean Derieux
 *
 */

public enum PartTypeImpl implements PartType {

    //Engine
        EG100("EG100",CategoryImpl.ENGINE,"Gasoline, 100kW"),
        EG133("EG133",CategoryImpl.ENGINE,"Gasoline, 133kW"),
        EG210("EG210",CategoryImpl.ENGINE,"Gasoline, 210kW"),
        ED110("ED110",CategoryImpl.ENGINE,"Diesel, 110kW"),
        ED180("ED180",CategoryImpl.ENGINE,"Diesel, 180kW"),
        EH120("EH120",CategoryImpl.ENGINE,"Gasoline/Electric hybrid, 120kW"),	// Req TC120
    //Transmission
        TM5("TM5",CategoryImpl.TRANSMISSION,"Manual, 5 gears"),
        TM6("TM6",CategoryImpl.TRANSMISSION,"Manual, 6 gears"),
        TA5("TA5",CategoryImpl.TRANSMISSION,"Automatic, 5 gears"),//Incompatible with EG100
        TS6("TS6",CategoryImpl.TRANSMISSION,"Sequential, 6 gears"),
        TSF7("TS7",CategoryImpl.TRANSMISSION,"Sequential, 7 gears,4 wheels drive"),//Incompatible EG100, EG133, ED110
        TC120("TC120",CategoryImpl.TRANSMISSION,"Gasoline/Electric hybrid, 120kW"),//Req EH120
    //Exterior
        XC("XC",CategoryImpl.EXTERIOR,"Classic Paint"),//Incompatible with EG100
        XM("XM",CategoryImpl.EXTERIOR,"Metallic pain"),// incompatible with EG100 and req IS interior
        XS("XS",CategoryImpl.EXTERIOR,"Red paint and sport decoration"),
    //Interior
        IN("IN", CategoryImpl.INTERIOR,"Standard interior"),
        IH("IH",CategoryImpl.INTERIOR,"High-end interior"),
        IS("IS",CategoryImpl.INTERIOR,"Sport finish")// incompatible with EG100 and TM5, REQ XS Exterior
        ;
        private String name;
        private Category category;
        private String Description;

        PartTypeImpl(String name, Category category, String description) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(category);

        this.name = name;
        this.category = category;
        this.Description= description;

    }
        
        

     //   PartTypeImpl(String name,Category category,String Description){
       //     this.name=name; this.category=category;this.Description=Description;
        //}
        @Override
        public String getDescription (){
            return this.Description;
        }
        @Override
        public String getName(){
            return this.name;
        }
        @Override
        public Category getCategory(){
            return this.category;
        }
    }


