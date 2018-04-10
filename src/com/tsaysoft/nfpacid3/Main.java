package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.Collection;
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

        String[] fileNames = {"NFPA704_DataSet_1.json", "NFPA704_DataSet_2.json"};
        ChemDBManager database = new ChemDBManager(fileNames);

        //int[] queryArray = new int[3];
        EnumMap<ChemProp, Integer> queryEnumMap = new EnumMap<>(ChemProp.class);
        EnumMap<ChemSpecial, Boolean> queryEnumMap2 = new EnumMap<>(ChemSpecial.class);

        queryEnumMap.put(HEALTH, 1);
        queryEnumMap.put(FLAMMABILITY, 0);
        queryEnumMap.put(REACTIVITY, 3);

        queryEnumMap2.put(ChemSpecial.OXIDIZER, true);

        Collection<Chemical> results = database.queryEnumMapNFPA(queryEnumMap, queryEnumMap2);

        if(results.size() == 0) {
            System.out.println("No results.");
        } else {
            for(Chemical chem : results) {
                chem.genChemID(InChI_Key);
                System.out.println(chem.getName() + " - " + chem.getID(InChI_Key));
            }
        }

    }

    private static EnumMap<ChemProp, Integer> askChemicalProps() {
        Scanner scanner = new Scanner(System.in);

        EnumMap<ChemProp, Integer> q = new EnumMap<>(ChemProp.class);

        System.out.println("Health: ");
        q.put(HEALTH,scanner.nextInt());

        System.out.println("Flammability: ");
        q.put(FLAMMABILITY,scanner.nextInt());

        System.out.println("Reactivity: ");
        q.put(REACTIVITY,scanner.nextInt());

        return q;
    }
}
