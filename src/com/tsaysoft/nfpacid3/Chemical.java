package com.tsaysoft.nfpacid3;

import java.util.EnumMap;
import java.util.Set;

import static com.tsaysoft.nfpacid3.ChemProp.*;
import static com.tsaysoft.nfpacid3.ChemSpecial.*;
import static com.tsaysoft.nfpacid3.ChemID.*;

/**
 * Represents a chemical and its associated information.
 * <p>
 *     Carries NFPA 704 information and PubChem CID information.
 * </p>
 * TODO: Fix Javadocs so that "true" is formatted correctly with <tt>somethin'</tt>
 */
public class Chemical {

    // --------------------
    // VARIABLES AND DATA
    // --------------------
    private String name;
    private EnumMap<ChemProp, Integer> properties = new EnumMap<ChemProp, Integer>(ChemProp.class);
    private EnumMap<ChemSpecial, Boolean> specials = new EnumMap<ChemSpecial, Boolean>(ChemSpecial.class);
    private EnumMap<ChemID, String> ids = new EnumMap<ChemID, String>(ChemID.class);



    // --------------------
    // CONSTRUCTORS
    // --------------------

    /**
     * Constructs an instance of {@code Chemical} without special symbols.
     *
     * @param chemName name of the chemical
     * @param health health rating of the chemical
     * @param flammability flammability rating of the chemical
     * @param reactivity reactivity rating of the chemical
     *
     * @see com.tsaysoft.nfpacid3.Chemical#Chemical(String, int, int, int, boolean[])
     */
    public Chemical(String chemName, int health, int flammability, int reactivity) {
        name = chemName;
        properties.put(HEALTH, health);
        properties.put(FLAMMABILITY, flammability);
        properties.put(REACTIVITY, reactivity);

    }

    /**
     * Constructs an instance of {@code Chemical} with special symbols.
     *
     * @param chemName name of the chemical
     * @param health health rating of the chemical
     * @param flammability flammability rating of the chemical
     * @param reactivity reactivity rating of the chemical
     * @param special array terms correspond to symbols: {@code 0} = OX, {@code 1} = SA, {@code 2} = W
     *
     * @see com.tsaysoft.nfpacid3.Chemical#Chemical(String, int, int, int)
     */
    public Chemical(String chemName, int health, int flammability, int reactivity, boolean[] special) {
        new Chemical(chemName, health, flammability, reactivity);
        try {
            if(special.length != 3) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if(special[0]) {
                isOX = true;
            }
            if(special[1]) {
                isSA = true;
            }
            if(special[2]) {
                isW = true;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("special array is wrong size");
        }


    }



    // --------------------
    // GETTERS AND SETTERS
    // --------------------

    /**
     * Gets the name of the chemical.
     * @return the chemical name as a {@code String}
     */
    public String getName() {
        return name;
    }

    /**
     * Gets an identification code of the chemical.
     * <p>
     *     Has problems with chemicals with commas in their names.
     *     (e.g. "Phosphorus, amorphous, red")
     *     //TODO Fix ID issues
     * </p>
     * @return the chemical ID code as a {@code String}
     */
    public String getID(ChemID idType) {
        return ids.get(idType);
    }

    /**
     * Gets a specific hazard rating of the chemical.
     * <p>
     *     Takes an {@code enum} that refers to one of the numbers inside the coloured diamonds of the
     *     overall fire diamond.
     * </p>
     * @param prop the specific hazard being queried
     * @return the specific chemical hazard rating as an {@code int}
     *
     * @see com.tsaysoft.nfpacid3.ChemProp
     * @see Chemical#getProps()
     */
    public int getProp(ChemProp prop) {
        return properties.get(prop);
    }

    /**
     * Gets all the hazard ratings of the chemical.
     * <p>
     *     Returns all the numbers inside the coloured diamonds of the overall fire diamond as
     *     an <tt>EnumMap</tt> utilizing <tt>ChemProp</tt> enums.
     * </p>
     * @return all of the chemical hazard ratings as an <tt>EnumMap<ChemProp, Integer></tt>
     *
     * @see com.tsaysoft.nfpacid3.ChemProp
     * @see Chemical#getProp(ChemProp)
     */
    public EnumMap<ChemProp, Integer> getProps() {
        return properties;
    }

    /**
     * Gets the presence of a specific special hazard symbol of the chemical.
     * <p>
     *     Takes an {@code enum} that refers to the symbol(s) inside the white square of the
     *     overall fire diamond.
     * </p>
     * @param special the presence of the special hazard symbol being queried
     * @return the presence of the special hazard symbol as a {@code boolean}
     *
     * @see com.tsaysoft.nfpacid3.ChemSpecial
     * @see Chemical#getSpecials()
     */
    public boolean getSpecial(ChemSpecial special) {
        return specials.get(special);
    }

    /**
     * Gets all of the presences of the special hazard symbols of the chemical.
     * <p>
     *     Returns all the numbers inside the coloured diamonds of the overall fire diamond as
     *     an <tt>EnumMap</tt> utilizing <tt>ChemSpecial</tt> enums.
     * </p>
     * @return all of the presences of the special hazard symbols as an <tt>EnumMap<ChemSpecial, Boolean></tt>
     *
     * @see com.tsaysoft.nfpacid3.ChemSpecial
     * @see Chemical#getSpecial(ChemSpecial)
     */
    public EnumMap<ChemSpecial, Boolean> getSpecials() {
        return specials;
    }



    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    /**
     * Compares two {@code Chemical}s based on the provided NFPA parameters/values only.
     * @param chem the {@code Chemical} to be compared with the current one
     * @return true if the NFPA values are the same, false if not
     */
    public boolean equalsNFPA(Chemical chem) {
        // Maybe implement way to ignore special
        boolean propSame = this.getProps().equals(chem.getProps());
        boolean specialSame = true;

        return true;
    }

    /**
     * Uses an HTTP request to get the CID for the {@code Chemical}.
     * <p>
     * By default, CID is equal to -1. After generation, the number changes.
     * Used to prevent slowdowns as only the queried {@code Chemical}s need CID's.
     *
     * @return the accessed CID as an {@code int}
     */
    public int genChemCID() {
        /*int CIDTemp;
        if(name != null && !name.equals("")) {
            CIDTemp = IDGet.requestID(name, ChemID.CID);
            if(CIDTemp != -1) {
                CID = CIDTemp;
            }
        }*/
        return 0;
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------


}
