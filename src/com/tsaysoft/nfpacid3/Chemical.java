package com.tsaysoft.nfpacid3;

import com.sun.istack.internal.Nullable;
import java.util.EnumMap;

import static com.tsaysoft.nfpacid3.ChemProp.*;
import static com.tsaysoft.nfpacid3.ChemSpecial.*;

/**
 * Represents a chemical and its associated information.
 * <p>
 *     Carries NFPA 704 information and chemical ID information.
 *     Can ask an {@link IDGAbstract} to retrieve a chemical ID from a name.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public class Chemical {

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    private String name;
    private EnumMap<ChemProp, Integer> properties = new EnumMap<>(ChemProp.class);
    private EnumMap<ChemSpecial, Boolean> specials = new EnumMap<>(ChemSpecial.class);
    private EnumMap<ChemID, String> ids = new EnumMap<>(ChemID.class);

    private static IDGAbstract idg = new FiehnIDG();



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
     * @deprecated This function is relatively depreciated due to the switch to using <tt>EnumMap</tt>s to
     *     store information.
     * @since 00.01.00
     */
    public Chemical(@Nullable String chemName, int health, int flammability, int reactivity) {
        this(chemName, health, flammability, reactivity, null);
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
     * @deprecated This function is relatively depreciated due to the switch to using <tt>EnumMap</tt>s to
     *     store information.
     * @since 00.01.00
     */
    public Chemical(@Nullable String chemName, int health, int flammability, int reactivity, boolean[] special) {
        super();

        name = chemName;
        properties.put(HEALTH, (Integer)health);
        properties.put(FLAMMABILITY, (Integer)flammability);
        properties.put(REACTIVITY, (Integer)reactivity);

        try {
            // Make sure to alter this for special non-NFPA 704 symbols that will be added later
            if(special == null || special.length != 3) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                specials.put(OXIDIZER, special[0]);
                specials.put(SIMPLE_ASPHYXIANT, special[1]);
                specials.put(WATER_REACT, special[2]);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("special array is wrong size - Chemical constructed without specials");
        }

    }

    /**
     * Constructs an instance of <tt>Chemical</tt> <b>without</b> special symbols.
     * <p>
     *     Takes an <tt>EnumMap</tt> that <b>must</b> have the the following <tt>enums</tt>:
     *     <p> - {@link ChemProp#HEALTH} </p>
     *     <p> - {@link ChemProp#FLAMMABILITY} </p>
     *     <p> - {@link ChemProp#REACTIVITY} </p>
     *     Otherwise, will throw <tt>IllegalArgumentException</tt>.
     * </p>
     * @param chemName name of the chemical
     * @param props <tt>EnumMap</tt> containing the chemical's properties information
     * @throws IllegalArgumentException if the input <tt>EnumMap</tt> is invalid
     *
     * @since 00.01.00
     */
    public Chemical(@Nullable String chemName, EnumMap<ChemProp, Integer> props) throws IllegalArgumentException{
        this(chemName, props, null);
    }

    /**
     * Constructs an instance of <tt>Chemical</tt> <b>with</b> special symbols.
     * <p>
     *     Takes a properties <tt>EnumMap</tt> that <b>must</b> have the the following <tt>enums</tt>:
     *     <p> - {@link ChemProp#HEALTH} </p>
     *     <p> - {@link ChemProp#FLAMMABILITY} </p>
     *     <p> - {@link ChemProp#REACTIVITY} </p>
     *     Otherwise, will throw <tt>IllegalArgumentException</tt>.
     *     The specials <tt>EnumMap</tt> is not checked in order to provide versatility (i.e. for
     *     non-NFPA 704 special symbols).
     * </p>
     * @param chemName name of the chemical
     * @param props <tt>EnumMap</tt> containing the chemical's properties information
     * @param specs <tt>EnumMap</tt> containing the chemical's special properties/symbols information
     * @throws IllegalArgumentException if the input <tt>EnumMap</tt> is invalid
     *
     * @since 00.01.00
     */
    public Chemical(@Nullable String chemName, EnumMap<ChemProp, Integer> props, EnumMap<ChemSpecial, Boolean> specs) throws IllegalArgumentException{
        name = chemName;
        if(props.containsKey(HEALTH) &&
                props.containsKey(FLAMMABILITY) &&
                props.containsKey(REACTIVITY)) {
            properties.put(HEALTH, props.get(HEALTH));
            properties.put(FLAMMABILITY, props.get(FLAMMABILITY));
            properties.put(REACTIVITY, props.get(REACTIVITY));
        } else {
            throw new IllegalArgumentException("wrong EnumMap - Chemical not constructed");
        }

        if (specs == null) {
            for (ChemSpecial symbol : ChemSpecial.values()) {
                this.specials.put(symbol, false);
            }
        } else {
            for (ChemSpecial symbol : ChemSpecial.values()) {
                Boolean tempBool = specs.get(symbol);
                if (tempBool == null) {
                    specials.put(symbol, false);
                } else {
                    specials.put(symbol, tempBool);
                }
            }
        }

    }



    // --------------------
    // GETTERS AND SETTERS
    // --------------------

    /**
     * Gets the name of the chemical.
     * @return the chemical name as a {@code String}
     *
     * @since 00.01.00
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the <tt>Chemical</tt>.
     * @param newName the new name of the chemical is a <tt>String</tt>
     *
     * @since 00.01.00
     */
    public void setName(@Nullable String newName) {
        name = newName;
    }

    /**
     * Gets an identification code of the chemical.
     * <p>
     *     Has problems with chemicals with commas in their names.
     *     (e.g. "Phosphorus, amorphous, red")
     *     TODO Fix ID issues
     * </p>
     * @return the chemical ID code as a {@code String}
     *
     * @since 00.01.00
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
     * @since 00.01.00
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
     * @since 00.01.00
     */
    public EnumMap<ChemProp, Integer> getProps() {
        return properties;
    }

    /**
     * Sets a specific hazard rating of the <tt>Chemical</tt>.
     * @param prop the <tt>ChemProp</tt> property to be set
     * @param newRating the new rating of the property to be set
     *
     * @since 00.01.00
     */
    public void setProp(ChemProp prop, int newRating) {
        properties.put(prop, newRating);
    }

    /**
     * Sets the hazard ratings of the <tt>Chemical</tt>.
     * <p>
     *     The provided <tt>EnumMap</tt> argument <b>must</b> contain all of the required <tt>enum</tt>s
     *     as specified in the <tt>ChemID</tt> class.
     * </p>
     * @param newProperties an <tt>EnumMap</tt> that contains <tt>ChemProp</tt> and <tt>Integer</tt> information
     * @return whether the attempted operation succeeded or not
     *
     * @since 00.01.00
     */
    public boolean setProps(EnumMap<ChemProp, Integer> newProperties) {
        if(newProperties.containsKey(HEALTH) &&
                newProperties.containsKey(FLAMMABILITY) &&
                newProperties.containsKey(REACTIVITY)) {
            properties.put(HEALTH, newProperties.get(HEALTH));
            properties.put(FLAMMABILITY, newProperties.get(FLAMMABILITY));
            properties.put(REACTIVITY, newProperties.get(REACTIVITY));
            return true;
        } else {
            return false;
        }
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
     * @see Chemical#getSpecials
     * @since 00.01.00
     */
    public boolean getSpecial(ChemSpecial special) {
        return specials.get(special);
    }

    /**
     * Gets all of the presences of the special hazard symbols of the chemical.
     * <p>
     *     Returns all the special hazard symbols inside the white diamond of the overall fire diamond as
     *     an <tt>EnumMap</tt> utilizing <tt>ChemSpecial</tt> enums.
     * </p>
     * @return all of the presences of the special hazard symbols as an <tt>EnumMap<ChemSpecial, Boolean></tt>
     *
     * @see com.tsaysoft.nfpacid3.ChemSpecial
     * @see Chemical#getSpecial(ChemSpecial)
     * @since 00.01.00
     */
    public EnumMap<ChemSpecial, Boolean> getSpecials() {
        return specials;
    }

    /**
     * Sets a specific special symbol of the <tt>Chemical</tt>.
     * @param spec the <tt>ChemSpecial</tt> special symbol to be set
     * @param newBoolean the new <tt>boolean</tt> of the special symbol to be set
     *
     * @since 00.01.00
     */
    public void setSpecial(ChemSpecial spec, boolean newBoolean) {
        specials.put(spec, newBoolean);
    }

    /**
     * Sets the special symbols of the <tt>Chemical</tt>.
     * <p>
     *     There are no restrictions on what the provided <tt>EnumMap</tt> must contain.
     * </p>
     * @param newSpecials an <tt>EnumMap</tt> that contains <tt>ChemSpecial</tt> and <tt>Boolean</tt> information
     *
     * @since 00.01.00
     */
    public void setSpecials(EnumMap<ChemSpecial, Boolean> newSpecials) {
        specials = newSpecials;
    }



    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    /**
     * Compares two {@code Chemical}s based on the provided NFPA parameters/values only.
     * <p>
     *     The <tt>special</tt> argument determines whether special symbol should be considered in
     *     comparing chemicals:
     *     <p> - <tt>true</tt> means that special symbols will be taken into account</p>
     *     <p> - <tt>false</tt> means that special symbols will <b>not</b> be taken into account</p>
     * </p>
     * @param chem the {@code Chemical} to be compared with the current one
     * @param special whether the special symbols of the {@code Chemical}s should be considered
     * @return <tt>true</tt> if the NFPA values are the same, <tt>false</tt> if not
     *
     * @since 00.01.00
     */
    public boolean equalsNFPA(Chemical chem, boolean special) {
        // TODO: Fix this so that it works. Currently, cannot "iterate" through EnumMap
        /*EnumMap<ChemProp, Integer> chemProp = chem.getProps();
        EnumMap<ChemSpecial, Boolean> chemSpecial = chem.getSpecials();
        boolean propSame = true;
        boolean specialSame = true;
        Boolean tempSpecialChem;
        Boolean tempSpecialThis;

        for (ChemProp category : chemProp.keySet()) {
            if (!chemProp.get(category).equals(this.properties.get(category))) {
                propSame = false;
            }
        }
        for (ChemSpecial symbol : chemSpecial.keySet()) {
            tempSpecialChem = chemSpecial.get(symbol);
            tempSpecialThis = this.getSpecial(symbol);

            if (!chemSpecial.get(symbol).equals(this.specials.get(symbol))) {
                specialSame = false;
            }
        }
*/
        boolean propSame = this.getProps().equals(chem.getProps());
        boolean specialSame = this.getSpecials().equals(chem.getSpecials());

        if(special) {
            return propSame && specialSame;
        } else {
            return propSame;
        }

    }

    /**
     * Uses an HTTP request to get the ID for the {@code Chemical}.
     * <p>
     *     After generation, IDs are stored in an <tt>EnumMap</tt> named <tt>ids</tt>.
     *     By default, there are no IDs available.
     *     Used to prevent slowdowns as only the queried {@code Chemical}s need ID's.
     *     Note that a chemical with a <tt>null</tt> or blank name will <b>not</b> attempt to generate an ID.
     * </p>
     * @param id the ID type requested to be generated
     * @return the generated ID as a <tt>String</tt>
     *
     * @since 00.01.00
     */
    public String genChemID(ChemID id) {
        String idTemp = null;
        if(name != null && !name.equals("")) {
            idTemp = idg.requestID(name, id);
            if(idTemp != null) {
                ids.put(id, idTemp);
            } else {
                System.out.println("ID null - ChemID not generated");
            }
        } else {
            System.out.println("name null or blank - ChemID not generated");
        }
        return idTemp;
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------



}
