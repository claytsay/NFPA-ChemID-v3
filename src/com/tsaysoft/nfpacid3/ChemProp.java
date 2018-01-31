package com.tsaysoft.nfpacid3;

/**
 * Specifies the three main properties identified by the NFPA 704 fire diamond.
 * <p>
 *     The properties are as follows:
 *     <p> - <tt>HEALTH</tt>: how toxic or poisonous the compound is (blue)</p>
 *     <p> - <tt>FLAMMABILITY</tt>: how flammable the compound is (red)</p>
 *     <p> - <tt>REACTIVITY</tt>: how reactive the compound is, apart from being flammable (yellow)</p>
 *     For each chemical, each property is ranked on a scale from 0 - 4, with 0 being least
 *     dangerous and 4 being most dangerous.
 * </p>
 */
public enum ChemProp {
    HEALTH,
    FLAMMABILITY,
    REACTIVITY;
}
