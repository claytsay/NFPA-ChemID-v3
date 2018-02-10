package com.tsaysoft.nfpacid3;

import java.io.*;
import java.net.*;
import org.json.*;

/**
 * A chemical name-to-ID converter (IDG) created by the Fiehn Lab.
 * <p>
 *     The <b>Fiehn Laboratory</b> is part of <u>University of California, Davis</u>.
 *     Academics there created a tool to convert between chemical names, IDs and structures.
 * </p>
 *
 * @see <a href="http://cts.fiehnlab.ucdavis.edu/conversion/">http://cts.fiehnlab.ucdavis.edu/conversion/</a>
 *
 * @author Clay Tsay
 * @version 00.01.00
 */
public class FiehnIDG extends IDGetter{

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    // N/A


    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    // N/A



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

    @Override
    protected String urlGenerator(String chemName, ChemID id) {
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
        chemName = cleanName(chemName);
        chemName = replaceSpaces(chemName,"_");
        url = url.concat(chemName);

        return url;
    }

    @Override
    protected String parseJSON(String JSONInput){
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