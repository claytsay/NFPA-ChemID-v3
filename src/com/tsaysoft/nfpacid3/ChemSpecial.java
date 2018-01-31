package com.tsaysoft.nfpacid3;

/**
 * Specifies the special properties identified by the NFPA 704 fire diamond.
 * <p>
 *     The NFPA 704-approved properties are as follows:
 *     <p> - <tt>OXIDIZER</tt>: the compound is an oxidiser (OX)</p>
 *     <p> - <tt>SIMPLE_ASPHYXIANT</tt>: the compound is a simple asphyxiant gas (SA)</p>
 *     <p> - <tt>WATER_REACT</tt>: the compound reacts violently with water (<s>W</s>)</p>
 * </p>
 */
public enum ChemSpecial {
    OXIDIZER,
    SIMPLE_ASPHYXIANT,
    WATER_REACT;
}
