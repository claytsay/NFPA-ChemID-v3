package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;

/**
 * Manages multiple chemical databases.
 * <p>
 *     In essence, has the same functionality as a <tt>ChemDB</tt>.
 * </p>
 *
 * @see com.tsaysoft.nfpacid3.ChemDBInterface
 * @see com.tsaysoft.nfpacid3.ChemDB
 *
 * @author Clay Tsay
 */
public class ChemDBManager implements ChemDBInterface{

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    private ArrayList<ChemDB> databases = new ArrayList<>();



    // --------------------
    // CONSTRUCTORS
    // --------------------

    /**
     * Constructs a basic <tt>ChemDBManager</tt> without any data.
     *
     * @see ChemDBManager#ChemDBManager(String)
     * @see ChemDBManager#ChemDBManager(String[])
     * @see ChemDBManager#ChemDBManager(Collection)
     */
    public ChemDBManager() {
        super();
    }

    /**
     * Constructs a <tt>ChemDBManager</tt> which takes data from a specified file.
     * <p>
     *     Uses the provided file name to construct a single <tt>ChemDB</tt> which is then
     *     added to the <tt>ChemDBManager</tt>'s "jurisdiction."
     * </p>
     *
     * @param fileName a file name to be turned into a <tt>ChemDB</tt>
     *
     * @see ChemDBManager#ChemDBManager()
     * @see ChemDBManager#ChemDBManager(String[])
     * @see ChemDBManager#ChemDBManager(Collection)
     */
    public ChemDBManager(String fileName) {
        super();
        databases.add(new ChemDB(fileName));
    }

    /**
     * Constructs a <tt>ChemDBManager</tt> which takes data from specified files.
     * <p>
     *     Uses the provided file names to construct separate <tt>ChemDB</tt>s which are then
     *     added to the <tt>ChemDBManager</tt>'s "jurisdiction."
     * </p>
     *
     * @param fileNames a <tt>Collection</tt> of file names to be turned into <tt>ChemDB</tt>s
     *
     * @see ChemDBManager#ChemDBManager()
     * @see ChemDBManager#ChemDBManager(String)
     * @see ChemDBManager#ChemDBManager(Collection)
     */
    public ChemDBManager(Collection<String> fileNames) {
        super();
        for(String s : fileNames) {
            databases.add(new ChemDB(s));
        }
    }

    /**
     * Constructs a <tt>ChemDBManager</tt> which takes data from specified files.
     * <p>
     *     Uses the provided file names to construct separate <tt>ChemDB</tt>s which are then
     *     added to the <tt>ChemDBManager</tt>'s "jurisdiction."
     * </p>
     *
     * @param fileNames a <tt>String[]</tt> of file names to be turned into <tt>ChemDB</tt>s
     *
     * @see ChemDBManager#ChemDBManager()
     * @see ChemDBManager#ChemDBManager(String)
     * @see ChemDBManager#ChemDBManager(String[])
     */
    public ChemDBManager(String[] fileNames) {
        super();
        for(String s : fileNames) {
            databases.add(new ChemDB(s));
        }
    }



    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

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
    @Override
    public ArrayList<Chemical> queryChemNFPA(Chemical query, boolean special) {
        ArrayList<Chemical> results = new ArrayList<>();
        for(ChemDB chemDB : databases) {
            results.addAll(chemDB.queryChemNFPA(query, special));
        }
        return results;
    }

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties information.
     * <p>
     * Does <b>not</b> take into account the special symbols.
     * </p>
     *
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap, EnumMap)
     */
    @Override
    public ArrayList<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties) {
        Chemical query = new Chemical(null, properties);
        ArrayList<Chemical> results = new ArrayList<>();
        for(ChemDB chemDB : databases) {
            results.addAll(chemDB.queryChemNFPA(query, false));
        }
        return results;
    }

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties and specials information.
     * <p>
     * <b>Does</b> take into account special symbols.
     * </p>
     *
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @param specials   the <tt>EnumMap</tt> with special symbols to be queried
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap)
     */
    @Override
    public ArrayList<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties, EnumMap<ChemSpecial, Boolean> specials) {
        Chemical query = new Chemical(null, properties, specials);
        ArrayList<Chemical> results = new ArrayList<>();
        for(ChemDB chemDB : databases) {
            results.addAll(chemDB.queryChemNFPA(query, true));
        }
        return results;
    }


    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------


}
