package com.tsaysoft.nfpacid3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.EnumMap;

import static com.tsaysoft.nfpacid3.ChemProp.*;
import static com.tsaysoft.nfpacid3.ChemSpecial.*;

/**
 * A database to store <tt>Chemical</tt>s.
 * <p>
 *     The fundamental unit of the database side of the program.
 *     Takes in a file name (currently, can only handle JSON),  opens that file, and uses the file's information
 *     to create a list of <tt>Chemicals</tt> with names, properties, and identifiers.
 *     Supports queries based on NFPA 704 hazard ratings (and special symbols).
 * </p>
 * @see com.tsaysoft.nfpacid3.ChemDBInterface
 * @see com.tsaysoft.nfpacid3.ChemDBManager
 *
 * @author Clay Tsay
 */
public class ChemDB implements ChemDBInterface{

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    /**
     * Stores the chemicals contained in the <tt>ChemDB</tt>'s database.
     */
    private ArrayList<Chemical> chemList = new ArrayList<>();

    /**
     * Temporary storage utilized in the processing of the database files.
     */
    private ArrayList<String[]> chemArray = new ArrayList<>();



    // --------------------
    // CONSTRUCTORS
    // --------------------

    /**
     * Constructs a <tt>ChemDB</tt> that takes information from the specified file.
     * <p>
     *     The file must be formatted in a very specific way.
     *     See the existing data sets for examples.
     * </p>
     * @param fileName the name of the file to be read
     */
    public ChemDB(String fileName) {
        //readCSV(fileName);
        readJSON(fileName);
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
        for(Chemical chem : chemList) {
            if(chem.equalsNFPA(query, special)) {
                results.add(chem);
            }
        }
        return results;
    }

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties information.
     * <p>
     *     Does <b>not</b> take into account the special symbols.
     * </p>
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap, EnumMap)
     */
    @Override
    public ArrayList<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties) {
        Chemical query = new Chemical(null, properties);
        return queryChemNFPA(query, false);
    }

    /**
     * Used to query for <tt>Chemical</tt>s that match the given <tt>EnumMap</tt>'s properties and specials information.
     * <p>
     *     <b>Does</b> take into account special symbols.
     * </p>
     * @param properties the <tt>EnumMap</tt> with properties to be queried
     * @param specials the <tt>EnumMap</tt> with special symbols to be queried
     * @return an <tt>ArrayList</tt> of <tt>Chemical</tt>s matching the properties
     *
     * @see ChemDBInterface#queryEnumMapNFPA(EnumMap)
     */
    @Override
    public ArrayList<Chemical> queryEnumMapNFPA(EnumMap<ChemProp, Integer> properties, EnumMap<ChemSpecial, Boolean> specials) {
        Chemical query = new Chemical(null, properties, specials);
        return queryChemNFPA(query, true);
    }



    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

    /**
     * NON-FUNCTIONAL - Takes a CSV file and converts it into an <tt>ArrayList</tt> of <tt>Chemical</tt>s.
     * <p>
     *     TODO: Fix this so that it works.
     * </p>
     *
     * @param fileName the name of the CSV file to be read
     */
    private void readCSV(String fileName) {
        // TODO: Fix this method: it cannot differentiate commas inside quotes and outside quotes.
        String line = "";
        String cvsSplitBy = ",";

        // Convert individual CSV lines into a 2D string array
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                // Use a comma as separator
                String[] chemDataTemp = line.split(cvsSplitBy);
                chemArray.add(chemDataTemp);
            }

        } catch (IOException e) {
            System.out.println("IOException has occurred in reading the CSV.");
            //e.printStackTrace();
        }

        // Convert the 2D array into Chemicals and put them into the ArrayList
        String specialTempString = "";
        String[] specialTempSArray;
        boolean[] specialTempBArray = {false, false, false};
        for(String[] chemData : chemArray) {
            // Access the special symbols string and put individual symbols into array spots
            if (chemData.length == 5) {
                chemData[4] = specialTempString;
                specialTempSArray = specialTempString.split(", ");

                // Convert the presence of the symbols into the boolean array taken by the Chemical() constructor
                for(String str : specialTempSArray) {
                    if(str.equalsIgnoreCase("ox")) {
                        specialTempBArray[0] = true;
                    }
                    if(str.equalsIgnoreCase("sa")) {
                        specialTempBArray[1] = true;
                    }
                    if(str.equalsIgnoreCase("w")) {
                        specialTempBArray[2] = true;
                    }
                }
            }

            // Add the new Chemical() to the list
            chemList.add(new Chemical(chemData[0],
                    Integer.parseInt(chemData[1]),
                    Integer.parseInt(chemData[2]),
                    Integer.parseInt(chemData[3]),
                    specialTempBArray));

            // Reset the temporary boolean array
            for(int i=0; i<3; i++) {
                specialTempBArray[i] = false;
            }
        }

    }

    /**
     * Reads a JSON database to convert its stored information into a list.
     * <p>
     *     Takes a very specific type of JSON file.
     *     See existing databases for JSON formatting information.
     * </p>
     * @param fileName name of the file to be accessed, as a <tt>String</tt>
     */
    private void readJSON(String fileName) {
        // Declaring variables
        JSONArray chemJSONArray;
        JSONObject chemJSONObject;

        String nameTemp = "";
        String specialTempString = "";
        EnumMap<ChemProp, Integer> propsTemp = new EnumMap<>(ChemProp.class);
        EnumMap<ChemSpecial, Boolean> specsTemp = new EnumMap<>(ChemSpecial.class);
        Chemical chemTemp;

        // Reading the actual file and processing the information.
        try {
            // Read the file and convert it into a JSONArray.
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            chemJSONArray = new JSONArray(content);
            //JSONParser parser = new JSONParser();
            //chemJSONArray = new JSONArray(new FileReader(fileName));

            // Put fields into private instance variables.
            for(Object obj : chemJSONArray) {
                try {
                    chemJSONObject = (JSONObject) obj;

                    // Convert the JSON information to processable information
                    nameTemp = chemJSONObject.get("NAME").toString();
                    propsTemp.put(HEALTH, Integer.parseInt(chemJSONObject.get("HEALTH").toString()));
                    propsTemp.put(FLAMMABILITY, Integer.parseInt(chemJSONObject.get("FLAMMABILITY").toString()));
                    propsTemp.put(REACTIVITY, Integer.parseInt(chemJSONObject.get("REACTIVITY").toString()));
                    specialTempString = chemJSONObject.get("SPECIAL").toString().toUpperCase();

                    // Access the special symbols string and put individual symbols into array spots
                    if (!specialTempString.equals("")) {
                        specsTemp = convertSpecialString(specialTempString);
                        chemTemp = new Chemical(nameTemp, propsTemp, specsTemp);

                    } else {
                        chemTemp = new Chemical(nameTemp, propsTemp);
                    }

                    // Add the new Chemical() to the list
                    chemList.add(chemTemp);

                    // Reset the EnumMaps
                    propsTemp.clear();
                    specsTemp.clear();

                } catch (NumberFormatException e){
                    System.out.println(e + " - chemical could not be properly loaded");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e + " - database could not be properly loaded");
        } catch (IOException e) {
            System.out.println(e + " - database could not be properly loaded");
        }

    }

    /**
     * Converts a <tt>String</tt> with special symbol information into an <tt>EnumMap</tt>.
     * <P>
     *     Note that the input <tt>String</tt> <b>must</b> be capitalised (i.e. <tt>toUpperCase()</tt>)
     *     for the method to work correctly.
     * </P>
     * @param specialString
     * @return
     */
    private static EnumMap<ChemSpecial, Boolean> convertSpecialString(String specialString) {
        EnumMap<ChemSpecial, Boolean> results = new EnumMap<>(ChemSpecial.class);

        results.put(OXIDIZER, specialString.contains("OX"));
        results.put(SIMPLE_ASPHYXIANT, specialString.contains("SA"));
        results.put(WATER_REACT, specialString.contains("W"));

        return results;
    }

}
