package com.tsaysoft.nfpacid3;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.text.NumberFormat;
import java.util.*;

public class ChemDB implements ChemDBInterface{

    // --------------------
    // VARIABLES AND DATA
    // --------------------

    private ArrayList<Chemical> chemList = new ArrayList<>();
    private ArrayList<String[]> chemArray = new ArrayList<>();

    // --------------------
    // CONSTRUCTORS
    // --------------------

    /**
     * TODO: Write this Javadoc
     * @param fileName
     */
    public ChemDB(String fileName) {
        //readCSV(fileName);
        readJSON(fileName);
    }

    // --------------------
    // PUBLIC UTILITY METHODS
    // --------------------

    /**
     * Returns a list of Chemicals matching the queried NFPA values
     * <p>
     * Query is a Chemical with certain NFPA values.
     * This method finds all chemicals in the database with the same NFPA value.
     * Returns the chemicals in an ArrayList.
     *
     * @param query a Chemical with the NFPA values to be queried
     * @return the matching chemicals in an ArrayList
     */
    @Override
    public ArrayList<Chemical> queryChemNFPA(Chemical query) {
        ArrayList<Chemical> results = new ArrayList<>();
        for(Chemical chem : chemList) {
            if(chem.equalsNFPA(query)) {
                results.add(chem);
            }
        }
        return results;
    }

    // --------------------
    // PRIVATE UTILITY METHODS
    // --------------------

    /**
     * NON-FUNCTIONAL - Takes a CSV file and converts it into an ArrayList of Chemical()s.
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
     * TODO: Write this Javadoc.
     * @param fileName
     */
    private void readJSON(String fileName) {
        // Declaring variables
        JSONArray chemJSONArray;
        JSONObject chemJSONObject;

        String nameTemp = "";
        int healthTemp = 0;
        int flammabilityTemp = 0;
        int reactivityTemp = 0;
        String specialTempString = "";
        String[] specialTempSArray;
        boolean[] specialTempBArray = {false, false, false};

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

                    nameTemp = chemJSONObject.get("FIELD1").toString();
                    healthTemp = Integer.parseInt(chemJSONObject.get("FIELD2").toString());
                    flammabilityTemp = Integer.parseInt(chemJSONObject.get("FIELD3").toString());
                    reactivityTemp = Integer.parseInt(chemJSONObject.get("FIELD4").toString());
                    specialTempString = chemJSONObject.get("FIELD5").toString();

                    // Access the special symbols string and put individual symbols into array spots
                    if (!specialTempString.equals("")) {
                        specialTempSArray = specialTempString.split(", ");

                        // Convert the presence of the symbols into the boolean array taken by the Chemical() constructor
                        for (String str : specialTempSArray) {
                            if (str.equalsIgnoreCase("ox")) {
                                specialTempBArray[0] = true;
                            }
                            if (str.equalsIgnoreCase("sa")) {
                                specialTempBArray[1] = true;
                            }
                            if (str.equalsIgnoreCase("w")) {
                                specialTempBArray[2] = true;
                            }
                        }
                    }

                    // Add the new Chemical() to the list
                    chemList.add(new Chemical(nameTemp,
                            healthTemp,
                            flammabilityTemp,
                            reactivityTemp,
                            specialTempBArray));

                    // Reset the temporary boolean array
                    for (int i = 0; i < 3; i++) {
                        specialTempBArray[i] = false;
                    }
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

}
