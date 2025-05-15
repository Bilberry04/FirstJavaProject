package DrinksMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class DrinkLoader {
    public static List<MachineDrinks> loadDrinks(String filePath) throws IOException {
        List<MachineDrinks> drinks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 4) {
                    int drinkId = Integer.parseInt(parts[0]);
                    String drinkName = parts[1];
                    double drinkPrice = Double.parseDouble(parts[2]);
                    int drinkQuantity = Integer.parseInt(parts[3]);

                    drinks.add(new MachineDrinks(drinkId, drinkName, drinkPrice, drinkQuantity));
                }
            }
        } catch (IOException e) {
            System.out.println("File reading error " + e.getMessage());
        }
        return drinks;
    }
}
