package com.tsaysoft.nfpacid3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An abstract class outlining classes that handle chemical name to chemical ID conversion.
 * <p>
 *     Handles the HTTP request involved in converting a chemical name to a specified chemical ID.
 *     ID type is specified by using <tt>enum</tt>s from {@link ChemID}.
 *     Subclasses are denoted by the name of the service they use and the letters "IDG".
 *     TODO: Maybe rename this to <i>IDG</i> to avoid confusion.
 * </p>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public abstract class IDGetter implements IDGInterface{

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    // N/A



    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    @Override
    public String requestID(String chemName, ChemID id) {
        // Make sure to remove cleanName() if it causes problems with ID generation
        String url = urlGenerator(cleanName(chemName), id);
        String JSON;
        try {
            JSON = getHTML(url);
            return parseJSON(JSON);
        } catch (Exception e) {
            System.out.println(e + " - ID unavailable");
            return null;
        }
    }

    /**
     * Takes a chemical name and cleans it of any extraneous content.
     * <p>
     *     When it encounters the following characters, it removes everything after it:
     *     <p> ", "
     *     <p> "("
     *     By doing this, however, it could cause serious problems when processing IUPAC names.
     *     As of now the latter function is disabled.
     *     Probably want to use this only in situations where ID generation experiences issues due to
     *     the "unclean" name.
     *     TODO: Fix this method so that it is actually useful.
     * </p>
     * @param name the name to be cleaned as a <tt>String</tt>
     * @return the cleaned name as a <tt>String</tt>
     */
    public static String cleanName(String name) {
        /*if(name.contains(", ")) {
            name = name.substring(0, name.indexOf(", "));
        }
        if(name.contains("(")) {
            name = name.substring(0, name.indexOf("("));
        }*/


        return name;
    }

    /**
     * Replaces the spaces in a <tt>String</tt> with a provided replacement <tt>String</tt>.
     * <p>
     *     For example, if <tt>name</tt> is <tt>Hello_World</tt> and <tt>replacement</tt>
     *     is <tt>%20</tt>, the program will return <tt>Hello%20World</tt>.
     * </p>
     * @param name the <tt>String</tt> to be processed
     * @param replacement the <tt>String</tt> that will replace the spaces in <tt>name</tt>
     * @return the "de-spaced" <tt>name</tt> as a <tt>String</tt>
     */
    public static String replaceSpaces(String name, String replacement) {
        // Not sure if this anti-null precaution is necessary
        if(name == null || replacement == null) {
            throw new RuntimeException("either name or replacement was null");
        } else {
            for(int i=0; i<name.length(); i++) {
                if(name.charAt(i) == ' ') {
                    name = name.substring(0,i) + replacement + name.substring(i+1);
                }
            }

            return name;
        }
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

    /**
     * Converts a chemical name to a HTTP request-ready URL.
     *
     * @param chemName the chemical name
     * @return the URL as a <tt>String</tt>
     */
    protected abstract String urlGenerator(String chemName, ChemID id);

    /**
     * Takes a URL and requests the HTML information.
     *
     * @param urlToRead the URL to be accessed
     * @return the information received as a <tt>String</tt>
     * @throws Exception if the method fails for whatever reason
     */
    protected static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /**
     * Parses <tt>String</tt> input into JSON and takes information from the latter, returning a chemical ID.
     *
     * @param JSONInput the input containing the ID information in a specific form
     * @return the requested chemical ID as an <tt>String</tt>
     */
    protected abstract String parseJSON(String JSONInput);

}
