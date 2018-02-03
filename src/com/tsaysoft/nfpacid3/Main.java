package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;

import static com.tsaysoft.nfpacid3.ChemID.*;
import static com.tsaysoft.nfpacid3.ChemProp.*;

public class Main {

    public static void main(String[] args) {
        // TODO: Fix the code below AFTER fixing the IDGet class so that it can be used for debugging.

        //String[] databaseNames = {"DataSet_1_NFPA704.json","DataSet_2_NFPA704.json"};
        //ChemDBManager database = new ChemDBManager(databaseNames);

        ChemDB database = new ChemDB("DataSet_1_NFPA704.json");
        Scanner scanner = new Scanner(System.in);

        //int[] queryArray = new int[3];
        EnumMap<ChemProp, Integer> queryEnumMap = new EnumMap<>(ChemProp.class);

        System.out.println("Health: ");
        queryEnumMap.put(HEALTH,scanner.nextInt());

        System.out.println("Flammability: ");
        queryEnumMap.put(FLAMMABILITY,scanner.nextInt());

        System.out.println("Reactivity: ");
        queryEnumMap.put(REACTIVITY,scanner.nextInt());

        ArrayList<Chemical> results = database.queryEnumMapNFPA(queryEnumMap);

        for(Chemical chem : results) {
            chem.genChemID(InChI_Key);
            System.out.println(chem.getName() + " - " + chem.getID(InChI_Key));
        }

    }
}
