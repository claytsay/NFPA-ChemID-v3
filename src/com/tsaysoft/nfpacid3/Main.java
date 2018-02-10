package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

import static com.tsaysoft.nfpacid3.ChemID.*;
import static com.tsaysoft.nfpacid3.ChemProp.*;

/**
 * This is really just a class to test out the functionality of the program.
 * <p>
 *     There's not much else to this. (>_<)
 * </p>
 */
public class Main {

    public static void main(String[] args) {
        // TODO: Fix the code below AFTER fixing the IDGet class so that it can be used for debugging.

        //String[] databaseNames = {"DataSet_1_NFPA704.json","DataSet_2_NFPA704.json"};
        //ChemDBManager database = new ChemDBManager(databaseNames);

        String[] fileNames = {"NFPA704_DataSet_1.json", "NFPA704_DataSet_2.json"};
        ChemDBManager database = new ChemDBManager(fileNames);
        Scanner scanner = new Scanner(System.in);

        //int[] queryArray = new int[3];
        EnumMap<ChemProp, Integer> queryEnumMap = new EnumMap<>(ChemProp.class);
        EnumMap<ChemSpecial, Boolean> queryEnumMap2 = new EnumMap<>(ChemSpecial.class);

        System.out.println("Health: ");
        queryEnumMap.put(HEALTH,scanner.nextInt());

        System.out.println("Flammability: ");
        queryEnumMap.put(FLAMMABILITY,scanner.nextInt());

        System.out.println("Reactivity: ");
        queryEnumMap.put(REACTIVITY,scanner.nextInt());

        // TODO: Conduct proper testing with special symbols to ensure that they work when querying
        //queryEnumMap2.put(ChemSpecial.SIMPLE_ASPHYXIANT, true);

        ArrayList<Chemical> results = database.queryEnumMapNFPA(queryEnumMap/*, queryEnumMap2*/);

        for(Chemical chem : results) {
            chem.genChemID(InChI_Key);
            System.out.println(chem.getName() + " - " + chem.getID(InChI_Key));
        }

    }
}
