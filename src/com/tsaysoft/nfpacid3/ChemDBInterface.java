package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.EnumMap;

/**
 * An interface specifying the functions of <tt>Chemical</tt> databases.
 * <p>
 *     Applies to chemical databases and super-databases (collections of databases).
 * </p>
 *
 * @author Clay Tsay
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
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties and/or specials
     */
    ArrayList<Chemical> queryChemNFPA(Chemical query, boolean special);

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties information.
     * <p>
     *     Does <b>not</b> take into account the special symbols.
     * </p>
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap, EnumMap)
     */
    ArrayList<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties);

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties and specials information.
     * <p>
     *     <b>Does</b> take into account special symbols.
     * </p>
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @param specials the <tt>EnumMap</tt> with special symbols to be queried
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap)
     */
    ArrayList<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties,
                                         EnumMap<ChemSpecial, Boolean> specials);

}
