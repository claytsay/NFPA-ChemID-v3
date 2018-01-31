package com.tsaysoft.nfpacid3;

import java.util.ArrayList;

/**
 * An interface specifying the functions of <tt>Chemical</tt> databases.
 * <p>
 *     Applies to chemical databases and super-databases (collections of databases).
 * </p>
 */
public interface ChemDBInterface {

    /**
     * Used to query for <tt>Chemical</tt>s that match each other in their NFPA 704 ratings.
     * <p>
     *     The <tt>boolean</tt> argument determines whether the special symbols
     *     (specified in {@link ChemSpecial}) should be considered in determining equality.
     * </p>
     * @param query the chemical with properties to be queried
     * @param special whether the special symbols should be taken into account in comparisons
     * @return
     */
    ArrayList<Chemical> queryChemNFPA(Chemical query, boolean special);
}
