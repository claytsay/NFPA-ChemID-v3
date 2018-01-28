package com.tsaysoft.nfpacid3;

import java.io.*;
import java.net.*;
import org.json.*;

/**
 * Handles chemical name to PubChem CID conversion.
 * <p>
 * Handles the HTTP request and JSON conversion involved in converting a chemical name to its PubChem CID.
 *
 * @author      Clay Tsay < clay.tsay @ gmail.com>
 */
public class IDGet {
    // --------------------
    // VARIABLES AND DATA
    // --------------------

    /**
     * Changes depending on the type of ID requested.
     */
    private String url;



    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    /**
     * Uses an HTTP request to convert a chemical name to a PubChem CID
     * <p>
     * If the CID in unavailable, will print error to console and return -1.
     *
     * @param chemName the common name of the chemical
     * @return the chemical's PubChem CID as an int
     */
    public static int requestID(String chemName, ChemID id) {
        String url = urlGenerator(chemName);
        String JSON = "";
        try {
            JSON = getHTML(url);
            return parseJSON(JSON);
        } catch (Exception e) {
            System.out.println(e + " - CID unavailable");
            return -1;
        }
    }
u


    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

    /**
     * Converts a chemical name to a HTTP request-ready URL.
     * @param chemName the chemical name
     * @return the URL as a String
     */
    private static String urlGenerator(String chemName) {
        String url = "http://cts.fiehnlab.ucdavis.edu/service/convert/Chemical%20Name/PubChem%20CID/";
        for(int i=0; i<chemName.length(); i++) {
            if(chemName.charAt(i) == ' ') {
                chemName = chemName.substring(0,i) + "_" + chemName.substring(i+1);
            }
        }
        url = url + chemName;
        return url;
    }

    /**
     * Takes a URL and requests the HTML information.
     * @param urlToRead the URL to be accessed
     * @return the information received as a String
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
     * Parses String input into JSON, returning a PubChem CID.
     * @param JSONInput the input containing the CID information in a specific form
     * @return the PubChem CID as an int
     */
    private static int parseJSON(String JSONInput){
        try {
            //System.out.println(JSONInput);
            JSONArray array1 = new JSONArray(JSONInput);
            JSONObject object1 = array1.getJSONObject(0);
            JSONArray array2 = object1.getJSONArray("result");
            //JSONObject object2 = array2.getJSONObject(0);

            //String chemIDStr = object2.toString();
            String chemIDStr = array2.getString(0);
            int chemID = Integer.parseInt(chemIDStr);

        /*String pageName = obj.getJSONObject("pageInfo").getString("pageName");
        JSONArray arr = obj.getJSONArray("posts");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("post_id");
        }*/

            return chemID;

        } catch (JSONException e){
            System.out.println("JSONException - CID unavailable");
            return -1;
        }
    }

}