package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * <tt>IDGManager</tt>s manage multiple {@link IDGetter}s.
 * <p>
 *     Past experience has shown that it is difficult to query chemical IDs
 *     from chemical names as many names have added terms (e.g. "inhibited")
 *     that interfere with name-to-ID conversion services. Therefore, <tt>IDGManager</tt>s
 *     take in information from numerous sources to arrive at an accurate chemical ID.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public class IDGManager implements IDGInterface{

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    private ArrayList<IDGetter> IDGs = new ArrayList<>();



    // --------------------
    // CONSTRUCTORS
    // --------------------

    /**
     * Constructs an empty <tt>IDGManager</tt>.
     *
     * @since 00.01.00
     */
    public IDGManager() {
        super();
    }

    /**
     * Constructs an <tt>IDGManager</tt> with one <tt>IDGetter</tt>.
     * <p>
     *     Does not possess the ability to determine between <tt>null</tt> and non-<tt>null</tt> inputs.
     * </p>
     *
     * @param IDG the <tt>IDGetter</tt> to be put into the <tt>IDGManager</tt>
     *
     * @since 00.01.00
     */
    public IDGManager(IDGetter IDG) {
        super();
        IDGs.add(IDG);
    }

    /**
     * Constructs an <tt>IDGManager</tt> with multiple <tt>IDGetter</tt>s.
     * <p>
     *     Does not possess the ability to determine between <tt>null</tt> and non-<tt>null</tt> inputs.
     * </p>
     *
     * @param IDGCollection a <tt>Collection</tt> of <tt>IDGetter</tt>s to be put into the <tt>IDGManager</tt>
     *
     * @since 00.01.00
     */
    public IDGManager(Collection<IDGetter> IDGCollection) {
        super();
        IDGs.addAll(IDGCollection);
    }

    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    @Override
    public String requestID(String chemName, ChemID id) {
        // Initialize variables
        ArrayList<String> results = new ArrayList<>();
        ArrayList<String> tokens = new ArrayList<>();
        HashMap<String, Integer> idFreqs = new HashMap<>();
        Integer tempInt;
        String maxToken;
        int maxFreq = 0;

        // Ask all of the IDGetters for their IDs
        for(IDGetter i : IDGs) {
            results.add(i.requestID(chemName, id));
        }

        // Find the numerical frequencies of the IDs
        for(String token : results) {
            if(!idFreqs.containsKey(token)) {
                idFreqs.put(token, 1);
            } else {
                tempInt = idFreqs.get(token);
                idFreqs.put(token, tempInt+1);
                tokens.add(token);
            }
        }

        // Find the ID with the greatest frequency
        for(String s : tokens) {
            if(idFreqs.get(s) > maxFreq) {
                maxFreq = idFreqs.get(s);
                maxToken = s;
            }
        }

        // If there is an ID with greatest frequency, return. If not, randomly choose.

        // TODO: Get this method working
        return null;

    }

    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------



}
