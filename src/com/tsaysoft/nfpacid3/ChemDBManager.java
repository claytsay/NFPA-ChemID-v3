package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Manages multiple chemical databases.
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

    @Override
    public ArrayList<Chemical> queryChemNFPA(Chemical query, boolean special) {
        ArrayList<Chemical> results = new ArrayList<>();
        for(ChemDB chemDB : databases) {
            results.addAll(chemDB.queryChemNFPA(query, special));
        }
        return results;
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------


}
