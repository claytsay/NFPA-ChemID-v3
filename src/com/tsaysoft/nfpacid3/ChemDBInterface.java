package com.tsaysoft.nfpacid3;

import java.util.Collection;
import java.util.EnumMap;

/**
 * An interface specifying the functions of {@link Chemical} databases.
 * <p>
 *     Applies to chemical databases and super-databases (collections of databases).
 * </p>
 *
 * @see com.tsaysoft.nfpacid3.ChemDB
 * @see com.tsaysoft.nfpacid3.ChemDBManager
 *
 * @author Clay Tsay
 * @version 00.01.01
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
     * @return a <tt>Collection</tt> of <tt>Chemical</tt>s matching the properties and/or specials
     *
     * @since 00.01.00
     */
    Collection<Chemical> queryChemNFPA(Chemical query, boolean special);

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties information.
     * <p>
     *     Does <b>not</b> take into account the special symbols.
     * </p>
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @return a <tt>Collection</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap, EnumMap)
     * @since 00.01.00
     */
    Collection<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties);

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties and specials information.
     * <p>
     *     <b>Does</b> take into account special symbols.
     * </p>
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @param specials the <tt>EnumMap</tt> with special symbols to be queried
     * @return a <tt>Collection</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap)
     * @since 00.01.00
     */
    Collection<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties,
                                         EnumMap<ChemSpecial, Boolean> specials);

}
