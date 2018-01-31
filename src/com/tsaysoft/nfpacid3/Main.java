package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // TODO: Remove this code sometime later -- it is not necessary.
        EnumMap<ChemProp, Integer> prop1 = new EnumMap<ChemProp, Integer>(ChemProp.class);
        EnumMap<ChemProp, Integer> prop2 = new EnumMap<ChemProp, Integer>(ChemProp.class);

        prop1.put(ChemProp.HEALTH, 4);
        prop2.put(ChemProp.HEALTH, 4);

        System.out.println(prop1.equals(prop2));

        // TODO: Fix the code below AFTER fixing the IDGet class so that it can be used for debugging.
        /*String[] databaseNames = {"DataSet_1_NFPA704.json","DataSet_2_NFPA704.json"};
        ChemDBManager database = new ChemDBManager(databaseNames);
        Scanner scanner = new Scanner(System.in);
        int[] queryArray = new int[3];

        System.out.println("Health: ");
        queryArray[0] = scanner.nextInt();
        System.out.println("Flammability: ");
        queryArray[1] = scanner.nextInt();
        System.out.println("Reactivity: ");
        queryArray[2] = scanner.nextInt();

        Chemical query = new Chemical(null, queryArray[0], queryArray[1], queryArray[2]);

        ArrayList<Chemical> results = database.queryChemNFPA(query);

        for(Chemical chem : results) {
            chem.genChemCID();
            System.out.println(chem.getName() + " - " + chem.getCID());
        }*/

    }
}
