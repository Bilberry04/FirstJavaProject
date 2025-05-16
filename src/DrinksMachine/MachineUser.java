package DrinksMachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class MachineUser {
    //[B1] MACHINEUSER CHOOSE YOUR OPTIONS
    static Scanner input = new Scanner(System.in);

    static void userChoiceB1() throws IOException {
        boolean inStock = true;
        boolean inSubMenu = true;
        while (inSubMenu) {

            System.out.println("Hello user!");
            System.out.println("Enter command: ");
            System.out.println("[1] Choose drink");
            System.out.println("[0] Return to main menu");

            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    MachineUser.drinkChoice();
                    inSubMenu = false;
                    break;
                case "0":
                    inSubMenu = false;
                    MachineCommands.mainMenuChoice();
                    break;
                default:
                    System.out.println("Invalid command");
            }

        }

    }

    //[drinkChoice] MACHINEUSER OPTION [1] CHOOSE DRINK
    static void drinkChoice() throws IOException {
        List<MachineDrinks> drinks = DrinkLoader.loadDrinks("src/DrinksMachine/MachineDrinks.txt"); //POBIERA PRODUKTY Z PLIKU I ZAPISUJE JE W LISCIE
        System.out.println("Choose your drink: ");
        for (MachineDrinks drink : drinks) {
            drink.display();
        }
        System.out.println("[0] Return");
        System.out.println("Enter drink ID: ");
        int idChoice = Integer.parseInt(input.nextLine()); //ZAMIANA ZE STRINGA NA LICZBĘ CAŁKOWITĄ
        boolean found = false;
        for (MachineDrinks drink : drinks) {
            if (drink.getdrinkID() == idChoice) {
                found = true;
                if (drink.getdrinkQuantity() > 0) { //SPRAWDZAMY DOSTEPNOSC PRODUKTU
                    drink.setdrinkQuantity(drink.getdrinkQuantity()-1); //SYMULACJA ZAKUPU 1 SZTUKI
                    System.out.println("You bought: " + drink.getdrinkName());

                    //NADPISANIE PLIKU TXT Z NOWA ILOSCIA
                    PrintWriter writer = new PrintWriter("src/DrinksMachine/MachineDrinks.txt");
                    for (MachineDrinks d : drinks) {
                        writer.println(d.getdrinkID() + ";" + d.getdrinkName() + ";" + d.getdrinkPrice()+ ";" + d.getdrinkQuantity());
                    }

                    MachineBalance.topUpMachine(drink.drinkPrice);
                    writer.close();
                    System.out.println("Returning to buy menu...");
                    userChoiceB1();


                } else {
                    System.out.println("Sorry, " + drink.getdrinkName() + " is out of stock.");
                }


            } else if (idChoice == 0) {
                MachineCommands.mainMenuChoice();
            }
        }
        if (!found) {
            System.out.println("Invalid drink ID");
        }

    }
}
