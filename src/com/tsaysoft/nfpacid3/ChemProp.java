package com.tsaysoft.nfpacid3;

/**
 * Specifies the three main properties identified by the NFPA 704 fire diamond.
 * <p>
 *     The properties are as follows:
 *     <ul>
 *         <li><tt>HEALTH</tt>: how toxic or poisonous the compound is (blue)</li>
 *         <li><tt>FLAMMABILITY</tt>: how flammable the compound is (red)</li>
 *         <li><tt>REACTIVITY</tt>: how reactive the compound is, apart from being flammable (yellow)</li>
 *     </ul>
 *     For each chemical, each property is ranked on a scale from 0 - 4, with 0 being least
 *     dangerous and 4 being most dangerous.
 *     Individual <tt>enum</tt> descriptions are taken from NFPA guidelines posted on Wikipedia.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 *
 * @see <a href="https://en.wikipedia.org/wiki/NFPA_704#Codes">NFPA 704 - Wikipedia</a>
 */
public enum ChemProp {

    /**
     * Represents the health hazard rating of a chemical.
     * <p>
     *     Indicated on the NFPA 704 fire diamond by a <u>blue</u> diamond.
     *     <ul>
     *         <li><b>0</b> - Poses no health hazard, no precautions necessary and would offer no
     *         hazard beyond that of ordinary combustible materials.</li>
     *         <li><b>1</b> - Exposure would cause irritation with only minor residual injury.</li>
     *         <li><b>2</b> - Intense or continued but not chronic exposure could cause temporary
     *         incapacitation or possible residual injury.</li>
     *         <li><b>3</b> - Short exposure could cause serious temporary or moderate residual injury.</li>
     *         <li><b>4</b> - Very short exposure could cause death or major residual injury.</li>
     *     </ul>
     * </p>
     *
     * @since 00.01.00
     */
    HEALTH,

    /**
     * Represents the flammability hazard rating of a chemical.
     * <p>
     *     Indicated on the NFPA 704 fire diamond by a <u>red</u> diamond.
     *     <ul>
     *         <li><b>0</b> - Will not burn under typical fire conditions.</li>
     *         <li><b>1</b> -  Requires considerable preheating, under all ambient temperature conditions
     *         before ignition and combustion can occur.</li>
     *         <li><b>2</b> - Must be moderately heated or exposed to relatively high ambient
     *         temperature before ignition can occur.</li>
     *         <li><b>3</b> - Can be ignited under almost all ambient temperature conditions.</li>
     *         <li><b>4</b> - Will rapidly or completely vaporize at normal atmospheric
     *         pressure and temperature, or is readily dispersed in air and will burn readily.</li>
     *     </ul>
     * </p>
     *
     * @since 00.01.00
     */
    FLAMMABILITY,

    /**
     * Represents the reactivity hazard rating of a chemical.
     * <p>
     *     Indicated on the NFPA 704 fire diamond by a <u>yellow</u> diamond.
     *     <ul>
     *         <li><b>0</b> - Normally stable, even under fire exposure conditions, and is not reactive with water.</li>
     *         <li><b>1</b> - Normally stable, but can become unstable at elevated temperatures and pressures.</li>
     *         <li><b>2</b> - Undergoes violent chemical change at elevated temperatures and pressures, reacts
     *         violently with water, or may form explosive mixtures with water.</li>
     *         <li><b>3</b> - Capable of detonation or explosive decomposition but requires a strong initiating
     *         source, must be heated under confinement before initiation, reacts explosively with water, or
     *         will detonate if severely shocked.</li>
     *         <li><b>4</b> - Readily capable of detonation or explosive decomposition at normal temperatures
     *         and pressures.</li>
     *     </ul>
     * </p>
     *
     * @since 00.01.00
     */
    REACTIVITY;
}
