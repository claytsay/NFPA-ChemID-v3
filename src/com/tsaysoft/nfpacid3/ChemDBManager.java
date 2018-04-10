package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;

/**
 * Manages multiple chemical databases.
 * <p>
 *     In essence, has the same functionality as a {@link ChemDB}.
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

    private List<ChemDB> databases = new ArrayList<>();



    // --------------------
    // CONSTRUCTORS
    // --------------------

    /**
     * Constructs a basic <tt>ChemDBManager</tt> without any data.
     *
     * @see ChemDBManager#ChemDBManager(String)
     * @see ChemDBManager#ChemDBManager(String[])
     * @see ChemDBManager#ChemDBManager(Collection)
     * @since 00.01.00
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
     * @since 00.01.00
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
     * @since 00.01.00
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
     * @since 00.01.00
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
     * @return a <tt>Collection</tt> of <tt>Chemical</tt>s matching the properties and/or specials
     *
     * @since 00.01.00
     */
    @Override
    public Collection<Chemical> queryChemNFPA(Chemical query, boolean special) {
        List<Chemical> results = new ArrayList<>();
        for(ChemDB chemDB : databases) {
            results.addAll(chemDB.queryChemNFPA(query, special));
        }

        return results;
        //return removeDuplicates(results, CID);
    }

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties information.
     * <p>
     * Does <b>not</b> take into account the special symbols.
     * </p>
     *
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @return a <tt>Collection</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap, EnumMap)
     * @since 00.01.00
     */
    @Override
    public Collection<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties) {
        Chemical query = new Chemical(null, properties);
        return queryChemNFPA(query, false);
    }

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties and specials information.
     * <p>
     * <b>Does</b> take into account special symbols.
     * </p>
     *
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @param specials   the <tt>EnumMap</tt> with special symbols to be queried
     * @return a <tt>Collection</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap)
     * @since 00.01.00
     */
    @Override
    public Collection<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties, EnumMap<ChemSpecial, Boolean> specials) {
        Chemical query = new Chemical(null, properties, specials);
        return queryChemNFPA(query, true);
    }


    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

    /**
     * TODO: Fix this so that it works. As of now, will fail if chemical IDs cannot be obtained.
     * Removes duplicate <tt>Chemical</tt>s from an <tt>ArrayList</tt>.
     * <p>
     *     Duplication is determined by comparing chemical ID tokens.
     *     The type of <tt>ChemID</tt> compared is determined by a method argument.
     * </p>
     * @param chemList the <tt>List</tt> of <tt>Chemical</tt>s to be cleaned
     * @param id the <tt>ChemID</tt> type to be used for duplicate determination
     * @return the cleaned <tt>Collection</tt> of <tt>Chemical</tt>s, free from duplicates
     *
     * @since N/A
     */
    private Collection<Chemical> removeDuplicates(List<Chemical> chemList, ChemID id) {
        // Declare variables
        Chemical tempOrigin;
        Chemical tempInsertion;

        // Generate IDs for each of the chemicals in the list
        for(Chemical c : chemList) {
            c.genChemID(id);
        }

        // Set the chemicals that match each other in ID to have null names
        for(int i = 0; i < chemList.size()-1; i++) {
            tempOrigin = chemList.get(i);
            for (int j = i+1; j < chemList.size(); j++) {
                tempInsertion = chemList.get(j);
                if(tempInsertion.getName() != null &&
                        tempInsertion.getID(id).equals(tempOrigin.getID(id))) {
                    tempInsertion.setName(null);
                }
            }
        }

        // Remove all chemicals that have null names
        for(int i = chemList.size()-1; i >= 0; i--) {
            if(chemList.get(i).getName() == null) {
                chemList.remove(i);
            }
        }

        return chemList;
    }

}
