package com.tsaysoft.nfpacid3;

import java.io.*;
import java.net.*;
import org.json.*;

/**
 * Handles chemical name to chemical ID conversion.
 * <p>
 *     Handles the HTTP request and JSON conversion involved in converting a chemical name to a specified
 *     chemical ID. ID type is specified by using <tt>enum</tt>s from {@link ChemID}.
 *     Most methods are static, removing the need to construct an instance of this class.
 * </p>
 *
 * @author Clay Tsay
 */
public class IDGet {

    // --------------------
    // VARIABLES AND DATA
    // --------------------



    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    /**
     * Uses an HTTP request to convert a chemical name to a specific type of chemical ID.
     * <p>
     *     If the ID in unavailable, will print error to console and return <tt>null</tt>.
     * </p>
     *
     * @param chemName the common name of the chemical
     * @return the chemical's requested ID as a <tt>String</tt>
     */
    public static String requestID(String chemName, ChemID id) {
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
     *     When it encounters the following, it removes everything after it:
     *     <p> ", "
     *     <p> "("
     *     By doing this, however, it could cause serious problems when processing IUPAC names.
     *     Probably want to use this only in situations where ID generation experiences issues due to
     *     the "unclean" name.
     * </p>
     * @param name the name to be cleaned as a <tt>String</tt>
     * @return the cleaned name as a <tt>String</tt>
     */
    public static String cleanName(String name) {
        if(name.contains(", ")) {
            name = name.substring(0, name.indexOf(", "));
        }
        if(name.contains("(")) {
            name = name.substring(0, name.indexOf("("));
        }

        return name;
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
    private static String urlGenerator(String chemName, ChemID id) {
        // Declaring variables
        String typeToken = "";
        String url = "http://cts.fiehnlab.ucdavis.edu/service/convert/Chemical%20Name/";

        // Find the typeToken "radical" from the provided enum and add to the URL
        // Assumes that all of the enums in ChemID will be supported
        switch(id) {
            case CASRN:
                typeToken = "CAS/";
                break;
            case CID:
                typeToken = "PubChem%20CID/";
                break;
            case InChI_Key:
                typeToken = "InChIKey/";
                break;
        }
        url = url.concat(typeToken);

        // Format and add the chemical name "radical" to the URL
        // Maybe move this to a different class, possibly cleanName
        for(int i=0; i<chemName.length(); i++) {
            if(chemName.charAt(i) == ' ') {
                chemName = chemName.substring(0,i) + "_" + chemName.substring(i+1);
            }
        }
        url = url.concat(chemName);

        return url;
    }

    /**
     * Takes a URL and requests the HTML information.
     *
     * @param urlToRead the URL to be accessed
     * @return the information received as a <tt>String</tt>
     * @throws Exception
     */
    private static String getHTML(String urlToRead) throws Exception {
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
     * Parses <tt>String</tt> input into JSON, returning a chemical ID.
     *
     * @param JSONInput the input containing the ID information in a specific form
     * @return the PubChem CID as an <tt>String</tt>
     */
    private static String parseJSON(String JSONInput){
        try {
            //System.out.println(JSONInput);
            JSONArray array1 = new JSONArray(JSONInput);
            JSONObject object1 = array1.getJSONObject(0);
            JSONArray array2 = object1.getJSONArray("result");
            //JSONObject object2 = array2.getJSONObject(0);

            //String chemIDStr = object2.toString();
            String chemID = array2.getString(0);

        /*String pageName = obj.getJSONObject("pageInfo").getString("pageName");
        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
        }*/

            return chemID;

        } catch (JSONException e){
            System.out.println(e + " - ID unavailable");
            return null;
        }
    }

}