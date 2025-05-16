package DrinksMachine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import static DrinksMachine.inputValidator.readSafeDouble;
import static DrinksMachine.inputValidator.readSafeInt;

public class MachineAdministrator {
    static Scanner input = new Scanner(System.in);

    //MENU ADMINISTRATORA
    static void AdministratorMenu() throws IOException {
    boolean status = false;

        while (!status) {
        System.out.println("Select an option: ");
        System.out.println("[1] Check list of products");
        System.out.println("[2] Add new product");
        System.out.println("[3] Delete product");
        System.out.println("[4] Edit product");
        System.out.println("[5] View balance");
        System.out.println("[0] Log out");

        String choice = input.nextLine();

        switch (choice) {
            case "1":
                checkProductsList();
                break;
                case "2":
                    addNewProduct();
                    break;
                    case "3":
                        deleteProduct();
                        break;
                        case "4":
                            editProduct();
                            break;
                            case "5":
                                break;
                                case "0":
                                    status = true;
                                    MachineCommands.mainMenuChoice();
                                    break;
            }
        }
    }

    //OPCJA 1 WYSWIETLANIE
    static void checkProductsList() throws IOException {
        boolean checking = true;

        while (checking) {
            System.out.println("Products List: ");
            System.out.println("[1] Show all products");
            System.out.println("[2] Show available products");
            System.out.println("[3] Show unavailable products");
            System.out.println("[4] Sort by price (low to high)");
            System.out.println("[5] Sort by price (high to low)");
            System.out.println("[0] Return");

            String listChoice = input.nextLine();
            List<MachineDrinks> drinks = DrinkLoader.loadDrinks("src/DrinksMachine/MachineDrinks.txt");

            switch (listChoice) {

                case "1":
                    System.out.println("\nAll products:");
                    for (MachineDrinks drink : drinks) {
                        drink.display();
                        }
                    break;

                case "2":
                    System.out.println("\nAvailable products:");
                    for (MachineDrinks drink : drinks) {
                        if (drink.getdrinkQuantity() > 0) {
                            drink.display();
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nUnavailable products:");
                    for (MachineDrinks drink : drinks) {
                        if (drink.getdrinkQuantity() == 0) {
                            drink.display();
                        }
                    }
                    break;

                    case "4":
                        drinks.sort((d1, d2) -> Double.compare(d1.getdrinkPrice(), d2.getdrinkPrice())); //POROWNUJE DRINK1 ORAZ DRINK2 I SPRAWDZA, KTÓRY JEST MNIEJSZY ORAZ SORTUJE
                        System.out.println("\nSort by price (low to high)");
                        for (MachineDrinks drink : drinks) {
                            drink.display();
                        }
                    break;

                    case "5":
                        drinks.sort((d1, d2) -> Double.compare(d2.getdrinkPrice(), d1.getdrinkPrice()));
                        System.out.println("\nSort by price (high to low)");
                        for (MachineDrinks drink : drinks) {
                            drink.display();
                        }
                    break;

                    case "0":
                        checking = false;
                        break;
            }
                      AdministratorMenu();
        }
    }

    //OPCJA 2 DODAWANIE
    static void addNewProduct() throws IOException {

        List<MachineDrinks> drinks = DrinkLoader.loadDrinks("src/DrinksMachine/MachineDrinks.txt"); //WCZYTANIE LISTY PRODUKTOW

        String newProductName;
        double newProductPrice;
        int newProductQuantity;
        int maxId = 0;

        //TWORZENIE NOWEGO ID DLA PRODUKTU
        for (MachineDrinks drink : drinks) {
            if (drink.getdrinkID() > maxId) {
                maxId = drink.getdrinkID();
            }
        }
        int newProductId = maxId + 1;

        System.out.println("\nAdd new product: ");
        System.out.print("Name: ");
        newProductName = input.nextLine();
        newProductPrice = readSafeDouble(input, "Price: ");
        newProductQuantity = readSafeInt(input, "Quantity: ");

        MachineDrinks newDrink = new MachineDrinks(newProductId, newProductName, newProductPrice, newProductQuantity); //UTWORZENIE OBIEKTU
        drinks.add(new MachineDrinks(newProductId, newProductName, newProductPrice, newProductQuantity)); //DODANIE OBIEKTU

        //ZAPISANIE OBIEKTU W PLIKU TXT
        PrintWriter writer = new PrintWriter("src/DrinksMachine/MachineDrinks.txt");
        for (MachineDrinks d : drinks) {
            writer.println(d.getdrinkID() + ";" + d.getdrinkName() + ";" + d.getdrinkPrice() + ";" + d.getdrinkQuantity());
        }
        writer.close();

        System.out.print("New product added: ");
        newDrink.display();
        System.out.println();
        AdministratorMenu();
    }

    //OPCJA 3 USUWANIE
    static void deleteProduct() throws IOException {

        List<MachineDrinks> drinks = DrinkLoader.loadDrinks("src/DrinksMachine/MachineDrinks.txt");

        System.out.println("\nDelete product: ");
        System.out.print("ID: ");
        int deleteID = input.nextInt();
        input.nextLine();

        String productName = null;
        double productPrice = 0;
        int productQuantity = 0;

        //SZUKANIE PRODUKTU O PODANYM ID I ZAPISANIE JEGO DANYCH
        for (MachineDrinks drink : drinks) {
                if (drink.getdrinkID() == deleteID) {
                    drink.display();
                    productName = drink.getdrinkName();
                    productPrice = drink.getdrinkPrice();
                    productQuantity = drink.getdrinkQuantity();
                }
            }
        System.out.println("\nAre you sure you want to delete the product " + "[" + deleteID + "] " + productName + " " + productPrice + " " + productQuantity + "?");
        System.out.println("Type (yes or no)");
        String decision = input.nextLine();

        if (decision.equals("yes")) {
            drinks.removeIf(drink -> drink.getdrinkID() == deleteID); //FUNKCJA USUWAJACA KONKRETNY PRODUKT Z LISTY

            PrintWriter writer = new PrintWriter("src/DrinksMachine/MachineDrinks.txt"); //NADPISANIE PLIKU TXT PO USUNIECIU PRODUKTU Z LISTY
            for (MachineDrinks d : drinks) {
                writer.println(d.getdrinkID() + ";" + d.getdrinkName() + ";" + d.getdrinkPrice() + ";" + d.getdrinkQuantity());
            }
            writer.close();

            System.out.println("Product " + "[" + deleteID + "] " + productName + " deleted successfully.");
        }
        else {
            System.out.println("\nDelete product was not deleted.");
        }

    }

    //OPCJA 4 EDYTOWANIE
    static void editProduct() throws IOException {

        List<MachineDrinks> drinks = DrinkLoader.loadDrinks("src/DrinksMachine/MachineDrinks.txt");
        MachineDrinks selectedDrink = null;

        while (selectedDrink == null) {

        System.out.println("\nPodaj ID produktu do edycji: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Invalid format ID");
            return;
        }

            for (MachineDrinks drink : drinks) {
                if (drink.getdrinkID() == id) {
                    selectedDrink = drink;
                    break;
                }
            }

            if (selectedDrink == null) {
                System.out.println("Product with this ID does not exist. Try again!");
            }
        }

                    boolean editing = true;

                    while (editing) {
                        selectedDrink.display();
                        System.out.println("\n[1] Name\n[2] Price\n[3] Quantity\n[0] Quit and save\n[9] Quit without saving");
                        System.out.println("\nWhat do you want to edit?");
                        System.out.print(">> ");
                        String choice = input.nextLine();

                        switch (choice) {

                            case "1":
                                System.out.print("New product name: ");
                                String newProductName = input.nextLine();
                                selectedDrink.setName(newProductName);
                                System.out.println("You changed product name on: " + newProductName);
                                break;

                            case "2":
                                System.out.print("New product price: ");
                                try {
                                    double newProductPrice = Double.parseDouble(input.nextLine());
                                    selectedDrink.setPrice(newProductPrice);
                                    System.out.println("You changed product price on: " + newProductPrice);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid format price");
                                }
                                break;

                            case "3":
                                System.out.print("New product quantity: ");
                                try {
                                    int newProductQuantity = Integer.parseInt(input.nextLine());
                                    selectedDrink.setdrinkQuantity(newProductQuantity);
                                    System.out.println("You changed product quantity on: " + newProductQuantity);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid format quantity");
                                }
                                break;

                            case "0":

                                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/DrinksMachine/MachineDrinks.txt"))) {
                                    for (MachineDrinks drink : drinks) {
                                        writer.write(drink.toFileString());
                                        writer.newLine();
                                    }
                                    System.out.println("Product updated successfully.");
                                } catch (IOException e) {
                                    System.out.println("Error writing to file: " + e.getMessage());
                                }

                                System.out.println("Exiting edit mode.");
                                MachineAdministrator.AdministratorMenu();
                                editing = false;
                                break;

                            case "9":
                                System.out.println("Exiting edit mode without saving.");
                                MachineAdministrator.AdministratorMenu();
                                editing = false;
                                break;

                            default:
                                System.out.println("\nInvalid choice. Try again! ");
                        }

                    }

    }


}


