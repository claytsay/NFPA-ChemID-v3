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

    public ChemDBManager() {
        super();
    }

    public ChemDBManager(String fileName) {
        super();
        databases.add(new ChemDB(fileName));
    }

    public ChemDBManager(Collection<String> fileNames) {
        super();
        for(String s : fileNames) {
            databases.add(new ChemDB(s));
        }
    }

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
    public ArrayList<Chemical> queryChemNFPA(Chemical query) {
        ArrayList<Chemical> results = new ArrayList<>();
        for(ChemDB chemDB : databases) {
            results.addAll(chemDB.queryChemNFPA(query));
        }
        return results;
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------


}
