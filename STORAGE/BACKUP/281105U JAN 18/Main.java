package com.tsaysoft.nfpacid3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] databaseNames = {"DataSet_1_NFPA704.json","DataSet_2_NFPA704.json"};
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
        }

    }
}
