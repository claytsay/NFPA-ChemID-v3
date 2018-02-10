package com.tsaysoft.nfpacid3;

/**
 * Specifies the special properties identified by the NFPA 704 fire diamond.
 * <p>
 *     The NFPA 704-standardized properties are as follows:
 *     <p> - <tt>OXIDIZER</tt>: the compound is an oxidiser (OX)</p>
 *     <p> - <tt>SIMPLE_ASPHYXIANT</tt>: the compound is a simple asphyxiant gas (SA)</p>
 *     <p> - <tt>WATER_REACT</tt>: the compound reacts violently with water (<s>W</s>)</p>
 *
 *     There are other symbols that are not NFPA 704-standardized. They are currently unsupported.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public enum ChemSpecial {
    /**
     * Denotes that the chemical is an oxidizer.
     * <p>
     *     e.g. KNO3, KClO3
     * </p>
     *
     * @since 00.01.00
     */
    OXIDIZER,

    /**
     * Denotes that the chemical is a simple asphyxiant gas.
     * <p>
     *     e.g. N2, Ar
     * </p>
     *
     * @since 00.01.00
     */
    SIMPLE_ASPHYXIANT,

    /**
     * Denotes that the chemical reacts with water.
     * <p>
     *     e.g. CH3NCO (a.k.a. MIC), CaC2
     * </p>
     *
     * @since 00.01.00
     */
    WATER_REACT;
}
