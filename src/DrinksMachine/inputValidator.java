package DrinksMachine;

import java.util.Scanner;

//VALIDATOR WPROWADZANIA CENY PRZEZ ADMINISTRATORA
public class inputValidator {
    public static double readSafeDouble(Scanner scanner, String message) {

        double value;
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().replace(",", "."); //ZAMIANA PRZECINKA NA KROPKE W RAZIE WYPADKU
            try {
                value = Double.parseDouble(input); //ZAMIANA STRINGA NA DOUBLE (JEZELI BYLBY PRZECINEK W WARTOSCI DOUBLE TO WYSKOCZYL BY BLĄ)
                if (value >= 0) break;             //W TEN SPOSÓB PO PRZEPIĘCIU NA STRINGA PROGRAM ZAAKCEPTUJE WARTOSC I PRZESLE TUTAJ
                else System.out.println("Price must be zero or more.");
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format. Try again.");
            }
        }
        return value;
    }

//VALIDATOR WPROWADZANIA ILOSCI PRZEZ ADMINISTRATORA
    public static int readSafeInt(Scanner scanner, String message) {
        int value;
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input); //ANALOGICZNA SYTUACJA JAK WYZEJ
                if (value >= 0) break; //JEZELI ILOSC JEST MNIEJSZA OD 1 TO WYPISZE KWESTIE PONIZSZA
                else System.out.println("Quantity must be zero or more.");
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number format. Try again."); //JEZELI DANE BEDA W ZLYM FORMACIE PROGRAM TO WYLAPIE
            }
        }
        return value;
    }
}
