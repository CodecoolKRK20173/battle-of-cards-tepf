import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContent{
    List<List<Integer>> cardsList = new ArrayList<ArrayList<Integer>>();
    
    public FileContent(String fileName) {
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
        }
    }

    private ArrayList<Integer> createRow(String line) {
        List<String> stringRow = Arrays.asList(line.split(","));
        List<Integer> integerRow;
        for(String el: stringRow) {
            integerRow.add(Integer.parseInt(el));
        }
        return integerRow;
    }
}