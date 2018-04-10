package com.tsaysoft.nfpacid3;

/**
 * Specifies the special properties identified by the NFPA 704 fire diamond.
 * <p>
 *     The NFPA 704-standardized properties are as follows:
 *     <ul>
 *         <li><tt>OXIDIZER</tt>: the compound is an oxidiser (OX)</li>
 *         <li><tt>SIMPLE_ASPHYXIANT</tt>: the compound is a simple asphyxiant gas (SA)</li>
 *         <li><tt>WATER_REACT</tt>: the compound reacts violently with water (<s>W</s>)</li>
 *     </ul>
 *     There are other symbols that are not NFPA 704-standardised. They are currently unsupported.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 *
 * @see <a href="https://en.wikipedia.org/wiki/NFPA_704#Codes">NFPA 704 - Wikipedia</a>
 */
public enum ChemSpecial {
    /**
     * Denotes that the chemical is an oxidizer.
     * <p>
     *     Examples:
     *     <ul>
     *         <li>KNO<sub>3</sub></li>
     *         <li>KClO<sub>3</sub></li>
     *     </ul>
     * </p>
     *
     * @since 00.01.00
     */
    OXIDIZER,

    /**
     * Denotes that the chemical is a simple asphyxiant gas.
     * <p>
     *     Examples:
     *     <ul>
     *         <li>N<sub>2</sub></li>
     *         <li>Ar</li>
     *     </ul>
     * </p>
     *
     * @since 00.01.00
     */
    SIMPLE_ASPHYXIANT,

    /**
     * Denotes that the chemical reacts with water.
     * <p>
     *     Examples:
     *     <ul>
     *         <li>CH<sub>3</sub>NCO (a.k.a. MIC)</li>
     *         <li>CaC<sub>2</sub></li>
     *     </ul>
     * </p>
     *
     * @since 00.01.00
     */
    WATER_REACT;
}
