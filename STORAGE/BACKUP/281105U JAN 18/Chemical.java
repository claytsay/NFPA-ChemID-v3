package com.tsaysoft.nfpacid3;

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
    private int CID = -1; // The PubChem CID of the chemical; derived from the chemical name
    private int heal;
    private int flam;
    private int reac;
    private boolean isOX;
    private boolean isSA;
    private boolean isW;



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
        heal = health;
        flam = flammability;
        reac = reactivity;
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
        name = chemName;
        heal = health;
        flam = flammability;
        reac = reactivity;
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
     * Gets the CID of the chemical.
     * <p>
     * Has problems with chemicals with commas in their names.
     * e.g. "Phosphorus, amorphous, red"
     * TODO Fix CID issues
     *
     * @return the chemical CID as an {@code int} (-1 if not valid)
     */
    public int getCID() {
        return CID;
    }

    /**
     * Gets the health rating of the chemical.
     * @return the chemical health rating as an {@code int}
     */
    public int getHealth() {
        return heal;
    }

    /**
     * Gets the flammability rating of the chemical.
     * @return the chemical flammability rating as an {@code int}
     */
    public int getFlammability() {
        return flam;
    }

    /**
     * Gets the reactivity rating of the chemical.
     * @return the chemical reactivity rating as an {@code int}
     */
    public int getReactivity() {
        return reac;
    }

    /**
     * Returns {@code true} if the chemical is an oxidizer.
     * @return {@code true} if is an oxidizer, {@code false} if not
     */
    public boolean isOxidizer() {
        return isOX;
    }

    /**
     * Returns {@code true} if the chemical is a simple asphyxiant.
     * @return {@code true} if is an SA, {@code false} if not
     */
    public boolean isSimAsphyx() {
        return isSA;
    }

    /**
     * Returns {@code true} if the chemical reacts with water.
     * @return {@code true} if W, {@code false} if not
     */
    public boolean isWaterReact() {
        return isW;
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
        if(this.getHealth() != chem.getHealth()) {
            return false;
        }
        if(this.getFlammability() != chem.getFlammability()) {
            return false;
        }
        if(this.getReactivity() != chem.getReactivity()) {
            return false;
        }
        if(this.isOxidizer() != chem.isOxidizer()) {
            return false;
        }
        if(this.isSimAsphyx() != chem.isSimAsphyx()) {
            return false;
        }
        if(this.isWaterReact() != chem.isWaterReact()) {
            return false;
        }

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
        int CIDTemp;
        if(name != null && !name.equals("")) {
            CIDTemp = IDGet.requestID(name, ChemID.CID);
            if(CIDTemp != -1) {
                CID = CIDTemp;
            }
        }
        return CID;
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

}
