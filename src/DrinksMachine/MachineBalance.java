package DrinksMachine;

import java.io.*;

public class MachineBalance {

    //SCIEZKA DO PLIKU Z SALDEM
    private static final String BALANCE_FILE = "src/DrinksMachine/MachineBalance.txt";

    static double balance;

    public MachineBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public static double readBalanceFromFile() {
        double currentBalance = 0.0;

        //WCZYTANIE OBECNEGO STANU KONTA Z PLIKU I ZWRACA GO
        try (BufferedReader reader = new BufferedReader(new FileReader(BALANCE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                currentBalance = Double.parseDouble(line);
            }
        } catch (IOException e) {
            System.out.println("Machine balance not found");
        }
        return currentBalance;
    }

    //WYSWIETLA OBECNE SALDO
    public static void displayBalance(){
        double currentBalance = readBalanceFromFile();
        System.out.println("Current Balance: " + currentBalance);
    }

    //DODANIE KWOTY DO KONTA MASZYNY I ZAPISANIE PLIKU
    public static void topUpMachine(double amount) {
        double currentBalance = readBalanceFromFile();

        //DODANIE NOWEJ KWOTY
        currentBalance += amount;

        //ZAPISANIE NOWEGO STANU KONTA DO PLIKU
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCE_FILE))) {
            writer.write(String.valueOf(currentBalance));
        } catch (IOException e) {
            System.out.println("Error writing to file" + e.getMessage());
        }

        System.out.println("Balance updated: + " + amount + "$");
    }



}
