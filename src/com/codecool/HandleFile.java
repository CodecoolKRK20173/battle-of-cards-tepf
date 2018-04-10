package com.codecool;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HandleFile{
    private List<ArrayList<Integer>> cardsList;
    
    public HandleFile(String fileName) {
        this.cardsList = new ArrayList<ArrayList<Integer>>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;            

            while((line = br.readLine()) != null) {
                cardsList.add(createRow(line));
            }
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
    }

    public Map<String, ArrayList<Integer>> getCardsMap() {
        Map<String, ArrayList<Integer>> cardsHashMap = new HashMap<String, ArrayList<Integer>>();
        int i = 0;

        for(ArrayList<Integer> row: this.cardsList) {
            String cardName = "Card" + i;
            cardsHashMap.put(cardName, row);
            i++;
        }
        return cardsHashMap;
    }

    private ArrayList<Integer> createRow(String line) {
        List<String> stringRow = Arrays.asList(line.split(","));
        ArrayList<Integer> integerRow = new ArrayList<Integer>();
        for(String el: stringRow) {
            integerRow.add(Integer.parseInt(el));
        }
        return integerRow;
    }

    public ArrayList<ArrayList<Integer>> getCardsList() {
        return this.cardsList;
    }
}